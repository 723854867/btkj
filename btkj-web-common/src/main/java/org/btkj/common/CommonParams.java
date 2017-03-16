package org.btkj.common;

import org.btkj.common.cache.impl.TenantCache;
import org.btkj.common.web.Beans;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.InviterModel;
import org.btkj.web.util.BtkjConsts;
import org.btkj.web.util.BtkjUtil;
import org.rapid.util.common.consts.conveter.Str2ObjConstConverter;
import org.rapid.util.exception.ConstConvertFailureException;

public interface CommonParams {

	final Str2ObjConstConverter<InviterModel> INVITER		= new Str2ObjConstConverter<InviterModel>(1500, "inviter") {
		@Override
		public InviterModel convert(String inviter) throws ConstConvertFailureException {
			try {
				App app = _getApp(inviter);
				Tenant tenant = null;
				int index = BtkjConsts.APP_ID_BIT_NUM;
				if (BtkjUtil.isBaoTuApp(app.getId())) {
					tenant = _getBaoTuTenant(inviter);
					index += BtkjConsts.TENANT_ID_BIT_NUM;
				} else 
					tenant = _getIsolateTenant(app.getId());
				
				if (inviter.length() == index)
					return new InviterModel(app, tenant, null);
				
				User user = _getUser(inviter, index);
				return new InviterModel(app, tenant, user);
			} catch (NumberFormatException e) {
				throw new ConstConvertFailureException(this, e);
			}
		}
		
		private App _getApp(String inviter) throws ConstConvertFailureException { 
			if (inviter.length() < BtkjConsts.APP_ID_BIT_NUM)
				throw new ConstConvertFailureException(this);
			int appId = Integer.valueOf(inviter.substring(0, BtkjConsts.APP_ID_BIT_NUM));
			App app = Beans.cacheService.getById(BtkjTables.APP.name(), appId);
			if (null == app)
				throw new ConstConvertFailureException(this);
			return app;
		}
		
		private Tenant _getBaoTuTenant(String inviter) throws ConstConvertFailureException {
			int index = BtkjConsts.APP_ID_BIT_NUM + BtkjConsts.TENANT_ID_BIT_NUM;
			if (inviter.length() < index)
				throw new ConstConvertFailureException(this);
			int tid = Integer.valueOf(inviter.substring(BtkjConsts.APP_ID_BIT_NUM, index));
			Tenant tenant = Beans.cacheService.getById(BtkjTables.TENANT.name(), tid);
			if (null == tenant)
				throw new ConstConvertFailureException(this);
			return tenant;
		}
		
		private Tenant _getIsolateTenant(int appId) { 
			TenantCache cache = (TenantCache) Beans.cacheService.getCache(BtkjTables.TENANT.name());
			Tenant tenant = cache.getIsolateTenant(appId);
			if (null == tenant)
				throw new ConstConvertFailureException(this);
			return tenant;
		}
		
		private User _getUser(String inviter, int index) throws ConstConvertFailureException {
			int uid = Integer.valueOf(inviter.substring(index));
			User user = Beans.userService.getUserByUid(uid);
			if (null == user)
				throw new ConstConvertFailureException(this);
			return user;
		}
	};
}
