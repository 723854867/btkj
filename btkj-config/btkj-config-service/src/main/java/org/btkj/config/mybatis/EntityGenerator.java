package org.btkj.config.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.btkj.pojo.entity.config.Api;
import org.btkj.pojo.entity.config.Area;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.config.Modular;
import org.btkj.pojo.entity.config.Privilege;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.config.ApiEditParam;
import org.btkj.pojo.param.config.AreaEditParam;
import org.btkj.pojo.param.config.InsurerEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.tree.Node;
import org.rapid.util.math.tree.mptt.MPTTNode;

public class EntityGenerator {

	public static final Insurer newInsurer(InsurerEditParam param) { 
		Insurer insurer = new Insurer();
		insurer.setName(param.getName());
		insurer.setBiHuId(null == param.getBiHuId() ? 0 : param.getBiHuId());
		insurer.setLeBaoBaId(param.getLeBaoBaId());
		insurer.setMinor(param.isMinor());
		insurer.setParentId(null == param.getParentId() ? 0 : param.getParentId());
		
		int time = DateUtil.currentTime();
		insurer.setCreated(time);
		insurer.setUpdated(time);
		return insurer;
	}
	
	public static final Area newArea(AreaEditParam param) {
		Area area = new Area();
		area.setCode(param.getCode());
		area.setRenewalPeriod(param.getRenewalPeriod());
		area.setVehiclePriceNoTax(param.getPriceNoTax());
		area.setBiHuId(param.getBiHuId());
		
		int time = DateUtil.currentTime();
		area.setCreated(time);
		area.setUpdated(time);
		return area;
	}
	
	public static final Modular newModular(String name, String cid, Modular parent, ModularType type) {
		Modular modular = new Modular();
		modular.setName(name);
		modular.setParentId(null == parent ? null : parent.getId());
		modular.setLeft(null == parent ? MPTTNode.INITIAL_ROOT_LEFT : parent.getRight());
		modular.setRight(modular.getLeft() + 1);
		modular.setLayer(null == parent ? Node.ROOT_LAYER : parent.getLayer() + 1);
		modular.setType(type.mark());
		modular.setCid(cid);
		
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
	
	public static final Map<String, Privilege> newPrivileges(ModularType type, int tarId, Set<Integer> modulars) {
		Map<String, Privilege> privileges = new HashMap<String, Privilege>();
		for (int modularId : modulars) {
			Privilege privilege = newPrivilege(type, tarId, modularId);
			privileges.put(privilege.getId(), privilege);
		}
		return privileges;
	}
	
	public static final Privilege newPrivilege(ModularType type, int tarId, int modularId) {
		Privilege privilege = new Privilege();
		privilege.setId(type.mark() + Consts.SYMBOL_UNDERLINE + tarId + Consts.SYMBOL_UNDERLINE + modularId);
		privilege.setType(type.mark());
		privilege.setTarId(tarId);
		privilege.setModularId(modularId);
		privilege.setCreated(DateUtil.currentTime());
		return privilege;
	}
}
