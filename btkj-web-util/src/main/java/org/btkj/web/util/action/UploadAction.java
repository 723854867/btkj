package org.btkj.web.util.action;

import org.btkj.pojo.param.Param;

public abstract class UploadAction<PARAM extends Param> extends Action<PARAM> {
	
	public UploadAction() {
		super(true);
	}
}
