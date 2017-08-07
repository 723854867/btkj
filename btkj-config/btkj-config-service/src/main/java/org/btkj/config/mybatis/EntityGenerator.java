package org.btkj.config.mybatis;

import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.param.ApiEditParam;
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
	
	public static final Modular newModular(String id, String name, Modular parent) {
		Modular modular = new Modular();
		modular.setId(id);
		modular.setName(name);
		modular.setParentId(null == parent ? null : parent.getId());
		modular.setLeft(null == parent ? MPTTNode.INITIAL_ROOT_LEFT : parent.getRight());
		modular.setRight(modular.getLeft() + 1);
		modular.setLayer(null == parent ? Node.ROOT_LAYER : parent.getLayer() + 1);
		
		int time = DateUtil.currentTime();
		modular.setCreated(time);
		modular.setUpdated(time);
		return modular;
	}
	
	public static final Api newApi(ApiEditParam param) {
		Api api = new Api();
		api.setPkg(param.getPkg());
		api.setName(param.getName());
		api.setModularId(param.getModularId());
		
		int time = DateUtil.currentTime();
		api.setCreated(time);
		api.setUpdated(time);
		return api;
	}
}
