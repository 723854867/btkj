package org.btkj.pojo.enums;

import org.rapid.util.common.Consts;

public enum FileType {

	PNG;
	
	public String suffix() {
		return Consts.SYMBOL_DOC + name().toLowerCase();
	}
}
