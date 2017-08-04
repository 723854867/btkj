package org.btkj.config.pojo;

import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.info.ModularDocument;
import org.rapid.util.math.tree.PBTreeFactory;

public class ModularDocumentFactory extends PBTreeFactory<String, Modular, ModularDocument> {

	@Override
	public ModularDocument instance(Modular node) {
		return new ModularDocument(node);
	}
}
