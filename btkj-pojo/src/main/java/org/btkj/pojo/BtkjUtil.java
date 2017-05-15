package org.btkj.pojo;

import org.btkj.pojo.entity.Region;
import org.btkj.pojo.enums.SortField;
import org.btkj.pojo.model.Credential;
import org.rapid.util.common.serializer.json.GsonEnumTypeAdapter;

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

	/**
	 * 判断两个客户端标识是否是同一个 app
	 * 
	 * @param credential1
	 * @param credential2
	 * @return
	 */
	public static final boolean isSameApp(Credential credential1, Credential credential2) {
		return credential1.getApp().getId() == credential2.getApp().getId();
	}
}
