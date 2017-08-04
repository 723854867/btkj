package org.btkj.web.util.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.btkj.pojo.bo.Version;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.Validator;

/**
 * 执行业务逻辑
 * 
 * @author ahab
 */
public abstract class Action<PARAM extends Param> {
	
	private static final ThreadLocal<Request> REQUEST_HOLDER	= new ThreadLocal<Request>();
	
	protected Class<PARAM> clazz;
	
	public Action() {
		Type superType = getClass().getGenericSuperclass(); 
		if (superType instanceof ParameterizedType) {
			Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
			clazz = (Class<PARAM>) generics[0];
		}
	}
	
	public Result<?> execute(Request request) {
		if (null != clazz) {
			String payload = request.getParam(Params.PAYLOAD);
			PARAM param = null;
			try {
				param = SerializeUtil.JsonUtil.GSON.fromJson(payload, clazz);
			} catch (Exception e) {
				throw ConstConvertFailureException.errorConstException(Params.PAYLOAD);
			}
			REQUEST_HOLDER.set(request);
			try {
				Set<ConstraintViolation<PARAM>> constraintViolations = validate(param);
				if (!constraintViolations.isEmpty()) {
					ConstConvertFailureException exception = ConstConvertFailureException.errorConstException(Params.PAYLOAD);
					StringBuilder builder = new StringBuilder();
					builder.append("payload param error for detail : [");
					int idx = 0;
					for (ConstraintViolation<PARAM> constraintViolation : constraintViolations) {
							builder.append(++idx).append("、").append(constraintViolation.getPropertyPath())
									.append(":").append(constraintViolation.getMessage()).append("; ");
					}
					builder.delete(builder.length() -2, builder.length());
					builder.append("]");
					exception.setDesc(builder.toString());
					throw exception;
				}
				return execute(param);
			} finally {
				REQUEST_HOLDER.remove();
			}
		}
		return Consts.RESULT.OK;
	}
	
	protected Result<?> execute(PARAM param) {
		return Consts.RESULT.OK;
	}
	
	/**
	 * 默认不启用组
	 * 
	 * @param param
	 * @return
	 */
	protected Set<ConstraintViolation<PARAM>> validate(PARAM param) {
		return Validator.JSR_VALIDATOR.validate(param);
	}
	
	protected Request request() {
		return REQUEST_HOLDER.get();
	}
	
	protected Client client() {
		return request().getParam(Params.CLIENT);
	}
	
	/**
	 * 默认从
	 * 
	 * @param request
	 * @return
	 */
	protected Client client(Request request) {
		return request.getParam(Params.CLIENT);
	}
	
	public Version version() {
		return Version.V_1_0;
	}
}
