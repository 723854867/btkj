package org.btkj.web.util.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.param.NilParam;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;

import com.google.gson.JsonSyntaxException;

public abstract class Action<PARAM extends Param> implements IAction {
	
	private static final ThreadLocal<Request> REQUEST_HOLDER	= new ThreadLocal<Request>();
	
	protected Integer crudMod;
	protected Class<PARAM> clazz;
	
	public Action() {
		Type superType = getClass().getGenericSuperclass(); 
		if (superType instanceof ParameterizedType) {
			Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
			clazz = (Class<PARAM>) generics[0];
		}
	}
	
	public Action(CrudType... crudTypes) {
		this();
		crudMod = crudTypes.length == 0 ? null : 0;
		for (CrudType type : crudTypes)
			crudMod |= type.mark();
	}

	@Override
	public final Result<?> execute(Request request) {
		REQUEST_HOLDER.set(request);
		try {
			if (clazz == NilParam.class)					// 没有参数体
				return execute((PARAM) NilParam.INSTANCE);
			else {
				String payload = request.getParam(Params.PAYLOAD);
				PARAM param = SerializeUtil.JsonUtil.GSON.fromJson(payload, clazz);
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
			}
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		} catch (JsonSyntaxException e) {
			throw ConstConvertFailureException.errorConstException(Params.PAYLOAD);
		}finally {
			REQUEST_HOLDER.remove();
		}
	}
	
	protected abstract Result<?> execute(PARAM param);
	
	/**
	 * 默认需要客户端传递 client 参数
	 * 
	 * @return
	 */
	public Client client() {
		return request().getParam(Params.CLIENT);
	}
	
	protected Request request() {
		return REQUEST_HOLDER.get();
	}
	
	/**
	 * 默认不启用组
	 * 
	 * @param param
	 * @return
	 */
	protected Set<ConstraintViolation<PARAM>> validate(PARAM param) {
		if (null == crudMod)
			return Params.JSR_VALIDATOR.validate(param);
		else {
			CrudType crudType = request().getParam(Params.CRUD_TYPE);
			if ((crudType.mark() & crudMod) != crudType.mark())
				throw new BusinessException(Code.UNSUPPORTED_CRUD_TYPE);
			param.setCrudType(crudType);
			switch (crudType) {
			case CREATE:
				return Params.JSR_VALIDATOR.validate(param, ValidateGroups.CREATE.class);
			case UPDATE:
				return Params.JSR_VALIDATOR.validate(param, ValidateGroups.UPDATE.class);
			case RETRIEVE:
				return Params.JSR_VALIDATOR.validate(param, ValidateGroups.RETRIEVE.class);
			case DELETE:
				return Params.JSR_VALIDATOR.validate(param, ValidateGroups.DELETE.class);
			default:
				throw new BusinessException(Code.SYSTEM_ERROR);
			}
		}
	}
}
