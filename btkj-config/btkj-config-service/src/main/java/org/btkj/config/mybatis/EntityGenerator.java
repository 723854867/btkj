package org.btkj.config.mybatis;

import java.math.BigDecimal;

import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.entity.Area;
import org.btkj.pojo.po.Insurer;
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
	
	public static final Api newApi(String key, String name, int pow) {
		Api api = new Api();
		api.setKey(key);
		api.setName(name);
		api.setGroupMod(new BigDecimal(2).pow(pow).toString());
		
		int time = DateUtil.currentTime();
		api.setCreated(time);
		api.setUpdated(time);
		return api;
	}
}
