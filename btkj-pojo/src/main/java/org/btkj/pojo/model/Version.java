package org.btkj.pojo.model;

/**
 * 接口版本
 * 
 * @author ahab
 */
public enum Version {

	V_1_0("1.0");
	
	private String value;
	
	private Version(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public static final Version match(String version) {
		for (Version v : Version.values()) {
			if (v.value.equalsIgnoreCase(version))
				return v;
		}
		return null;
	}
}
