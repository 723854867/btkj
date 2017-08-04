package org.btkj.config.mybatis;

import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.pojo.po.Insurer;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.tree.Node;
import org.rapid.util.math.tree.mptt.MPTTNode;

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
	
	public static final Area newArea(int code, int renewalPeriod, int biHuId, boolean priceNoTax) {
		Area area = new Area();
		area.setCode(code);
		area.setRenewalPeriod(renewalPeriod);
		area.setVehiclePriceNoTax(priceNoTax);
		area.setBiHuId(biHuId);
		
		int time = DateUtil.currentTime();
		area.setCreated(time);
		area.setUpdated(time);
		return area;
	}
	
	public static final Modular newModular(ModularEditParam param, Modular parent) {
		Modular modular = new Modular();
		modular.setId(param.getId());
		modular.setName(param.getName());
		modular.setParentId(param.getParentId());
		modular.setLeft(null == parent ? MPTTNode.INITIAL_ROOT_LEFT : parent.getRight());
		modular.setRight(modular.getLeft() + 1);
		modular.setLayer(null == parent ? Node.ROOT_LAYER : parent.getLayer() + 1);
		
		int time = DateUtil.currentTime();
		modular.setCreated(time);
		modular.setUpdated(time);
		return modular;
	}
}
