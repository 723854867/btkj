package org.btkj.config;

import org.btkj.pojo.entity.config.Modular;
import org.btkj.pojo.info.config.ModularDocument;
import org.rapid.util.math.tree.PBTreeFactory;

public class ModularDocumentFactory extends PBTreeFactory<Integer, Modular, ModularDocument> {

	@Override
	public ModularDocument instance(Modular node) {
		return new ModularDocument(node);
	}
}
