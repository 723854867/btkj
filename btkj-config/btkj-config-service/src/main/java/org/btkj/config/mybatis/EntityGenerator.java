package org.btkj.config.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.entity.Modular;
import org.btkj.config.pojo.entity.Privilege;
import org.btkj.config.pojo.param.ApiEditParam;
import org.btkj.config.pojo.param.AreaEditParam;
import org.btkj.config.pojo.param.InsurerEditParam;
import org.btkj.pojo.po.Insurer;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.tree.Node;
import org.rapid.util.math.tree.mptt.MPTTNode;

public class EntityGenerator {

	public static final Insurer newInsurer(InsurerEditParam param) { 
		Insurer insurer = new Insurer();
		insurer.setId(param.getId());
		insurer.setName(param.getName());
		insurer.setIcon(param.getIcon());
		insurer.setBiHuId(param.getBiHuId());
		insurer.setLeBaoBaId(param.getLeBaoBaId());
		
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
	
	public static final Modular newModular(String name, Modular parent) {
		Modular modular = new Modular();
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
	
	public static final Map<String, Privilege> newPrivileges(TarType tarType, int tarId, Set<Integer> modulars) {
		Map<String, Privilege> privileges = new HashMap<String, Privilege>();
		for (int modularId : modulars) {
			Privilege privilege = newPrivilege(tarType, tarId, modularId);
			privileges.put(privilege.getId(), privilege);
		}
		return privileges;
	}
	
	public static final Privilege newPrivilege(TarType tarType, int tarId, int modularId) {
		Privilege privilege = new Privilege();
		privilege.setId(tarType.mark() + Consts.SYMBOL_UNDERLINE + tarId + Consts.SYMBOL_UNDERLINE + modularId);
		privilege.setTarType(tarType.mark());
		privilege.setTarId(tarId);
		privilege.setModularId(modularId);
		privilege.setCreated(DateUtil.currentTime());
		return privilege;
	}
}
