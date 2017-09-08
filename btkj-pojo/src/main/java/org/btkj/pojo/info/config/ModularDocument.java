package org.btkj.pojo.info.config;

import org.btkj.pojo.entity.config.Modular;
import org.rapid.util.math.tree.mptt.MPTTNodeDocument;

public class ModularDocument extends MPTTNodeDocument<Integer, Modular, ModularDocument> {
	
	private static final long serialVersionUID = 6873428521308246472L;
	
	private boolean own;

	public ModularDocument(Modular node) {
		super(node);
	}
	
	public boolean isOwn() {
		return own;
	}
	
	public void setOwn(boolean own) {
		this.own = own;
	}
}
