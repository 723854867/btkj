package org.btkj.web.util;

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

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.model.Version;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.SpringContextUtil;
import org.rapid.util.common.consts.Const;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.reflect.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

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
	
	private Map<String, Map<Version, Action>>  container = new HashMap<String, Map<Version, Action>>();
	
	@Autowired(required = false)
	private InitialHook initialHook;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringContextUtil.autowireBean(this);
		_initActions();
		if (null != initialHook)
			initialHook.start();
	}
	
	private void _initActions() {
		String actionPackage = getServletContext().getInitParameter(ACTION_LOCATION);
		List<Class<?>> classes = ClassUtil.scanPackage(actionPackage, false);
		for (Class<?> clazz : classes) {
			int modifiers = clazz.getModifiers();
			if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers) || !Modifier.isPublic(modifiers))
				continue;
			try {
				Action action = SpringContextUtil.autowire(clazz);
				_addAction(action);
			} catch (Exception e) {
				logger.error("Action load failure, system will closed!", e);
				System.exit(1);
			} 
		}
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
		Request req = new Request(request, response);
		try {
			Version version = req.getOptionalParam(Params.VERSION);
			Action action = _getAction(req.getParam(Params.ACTION), version);
			if (null == action) {
				req.write(Result.jsonResult(BtkjCode.ACTION_NOT_EXIST));
				return;
			}
			
			Result<?> result = action.execute(req);
			req.write(SerializeUtil.JsonUtil.GSON.toJson(result));
		} catch (ConstConvertFailureException e) {
			Const<?> constant = e.constant();
			req.write(Result.jsonResult(constant.id(), 
							MessageFormat.format(
									e.isNil() ? Code.PARAM_MISS.value() : Code.PARAM_ERROR.value(), constant.key())));
		} catch (Exception e) {
			logger.error("Server exception!", e);
			req.write(Result.jsonResult(Code.SYSTEM_ERROR));
		}
	}
	
	@Override
	protected final void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader(HttpHeaders.ALLOW, METHOD_ALLOWS);
	}
	
	private void _addAction(Action action) {
		String name = action.getClass().getSimpleName().toLowerCase();
		Map<Version, Action> map = container.get(name);
		if (null == map) {
			map = new HashMap<Version, Action>();
			container.put(name, map);
		}
		map.put(action.version(), action);
	}
	
	private Action _getAction(String name, Version version) {
		Map<Version, Action> map = container.get(name);
		return null == map ? null : map.get(version);
	}
}
