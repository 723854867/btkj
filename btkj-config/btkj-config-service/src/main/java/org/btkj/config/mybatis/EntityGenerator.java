package org.btkj.config.mybatis;

import org.btkj.pojo.entity.Insurer;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Insurer newInsurer(int id, String name, String icon, boolean bindBiHu, String leBaoBaId) { 
		Insurer insurer = new Insurer();
		insurer.setId(id);
		insurer.setName(name);
		insurer.setIcon(icon);
		insurer.setBiHuId(bindBiHu ? id : 0);
		insurer.setLeBaoBaId(leBaoBaId);
		
		int time = DateUtil.currentTime();
		insurer.setCreated(time);
		insurer.setUpdated(time);
		return insurer;
	}
}
