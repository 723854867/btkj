package org.btkj.pojo;

import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.master.Admin.Mod;
import org.btkj.pojo.enums.SortField;
import org.rapid.util.common.serializer.json.GsonEnumTypeAdapter;
import org.rapid.util.math.tree.Node;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BtkjUtil {
	
	public static final Gson GSON = new GsonBuilder()
			.serializeNulls()
			.registerTypeAdapter(SortField.class, new GsonEnumTypeAdapter<SortField>(SortField.CREATED))
			.create();
	
	/**
	 * 判断 tar 是否是 src 的子行政区划
	 * 
	 * @param src
	 * @param tar
	 * @return
	 */
	public static final boolean isSubRegion(Region src, Region tar) { 
		if (src.getLevel() == tar.getLevel())				// 同级别的肯定不是父子关系
			return false;
		return false;
	}
	
	public static final boolean isTopRole(Admin admin) {
		return (admin.getMod() & Mod.TOP_ROLE.mark()) == Mod.TOP_ROLE.mark();
	}
	
	public static final boolean isTopRole(UserPO user) {
		return (user.getMod() & org.btkj.pojo.entity.UserPO.Mod.TOP_ROLE.mark()) == org.btkj.pojo.entity.UserPO.Mod.TOP_ROLE.mark();
	}
	
	public static final boolean isTopRole(EmployeePO employee) {
		return employee.getLayer() == Node.ROOT_LAYER;
	}
}
