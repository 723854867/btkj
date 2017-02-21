package org.btkj.common.web;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.SerializeUtil;
import org.rapid.util.common.consts.Const;
import org.rapid.util.common.consts.code.ICode.CommonCode;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.reflect.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 只允许 POST, OPTIONS, TRACE
 * 
 * @author ahab
 */
public class Dispatcher extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	private static final long serialVersionUID = -7815032356377001691L;
	
	/**
	 * 如果要允许其他方法那么需要修改该字段，并且重写该方法；
	 * 如果要屏蔽某个方法也需要修改该字段(去掉该方法)，并且删除该方法；
	 */
	private static final String METHOD_ALLOWS					= "TRACE, OPTIONS, POST, GET";
	
	private static final String ACTION_LOCATION					= "actionLocation";
	
	protected Map<String, IAction> actions = new HashMap<String, IAction>();
	
	@Override
	public void init() throws ServletException {
		super.init();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
		factory.autowireBean(this);
		_initActions();
	}
	
	private void _initActions() {
		String actionPackage = getServletContext().getInitParameter(ACTION_LOCATION);
		List<Class<?>> classes = ClassUtil.scanPackage(actionPackage, false);
		for (Class<?> clazz : classes) {
			int modifiers = clazz.getModifiers();
			if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers) || !Modifier.isPublic(modifiers))
				continue;
			try {
				actions.put(clazz.getSimpleName().toLowerCase(), (IAction) clazz.newInstance());
			} catch (Exception e) {
				logger.error("Action load failure, system will closed!", e);
				System.exit(1);
			} 
		}
		logger.info("Total {} actions loaded!", actions.size());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this._receive(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this._receive(req, resp);
	}
	
	private void _receive(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = new HttpSession(request, response);
		try {
			IAction action = actions.get(session.getParam(Params.ACTION));
			if (null == action) {
				session.write(Result.jsonError(Code.ACTION_NOT_EXIST));
				return;
			}
			
			Result<?> result = action.execute(session);
			session.write(SerializeUtil.JsonUtil.GSON.toJson(result));
		} catch (ConstConvertFailureException e) {
			Const<?> constant = e.constant();
			session.write(Result.jsonResult(constant.id(), 
							MessageFormat.format(
									e.isNil() ? CommonCode.PARAM_MISS.value() : CommonCode.PARAM_ERROR.value(), constant.key())));
		} catch (Exception e) {
			logger.error("Server exception!", e);
			session.write(Result.jsonError(CommonCode.SYSTEM_ERROR));
		}
	}
	
	@Override
	protected final void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader(HttpHeaders.ALLOW, METHOD_ALLOWS);
	}
}
