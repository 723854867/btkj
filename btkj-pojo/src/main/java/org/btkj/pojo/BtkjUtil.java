package org.btkj.pojo;

import org.btkj.pojo.entity.Region;
import org.btkj.pojo.enums.SortField;
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
}
