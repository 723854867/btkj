package org.btkj.lebaoba.vehicle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.lebaoba.BaseTest;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.VehicleType;
import org.btkj.pojo.enums.VehicleTypeCode;
import org.btkj.pojo.enums.VehicleUnitType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.junit.Test;

public class LeBaoBaVehicleTest extends BaseTest {

	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		leBaoBaVehicle.vehicleInfos("WBSDX9108BE370935");
	}
	
	public void testOrder() {
		VehiclePolicyTips tips = new VehiclePolicyTips();
		tips.setLicense("浙AXG123");
		tips.setVin("LFV3A23C4A3042594");
		tips.setEngine("191318");
		tips.setEnrollDate("2010-08-01");
		tips.setTransfer(false);
		tips.setVehicleUsedType(VehicleUsedType.HOME_USE);
		tips.setSeat(5);
		tips.setYear(2009);
		tips.setName("迈腾FV7187TDQG轿车");
		tips.setPrice(244000);
		tips.setPriceNoTax(224800);
		tips.setLoad("0");
		tips.setExhaust("1.798");
		tips.setTransmissionName("DSG 豪华型 涡轮增压 国Ⅳ");
		tips.setVehicleId("6227521");
		tips.setVehicleType(VehicleType.COACH);
		tips.setVehicleTypeCode(VehicleTypeCode.A012);
		
		InsurUnit owner = new InsurUnit();
		owner.setType(VehicleUnitType.PERSONAL);
		owner.setIdType(IDType.IDENTITY);
		owner.setName("郭海滨");
		owner.setMobile("13295815927");
		owner.setIdNo("330106198112040434");
		tips.setOwner(owner);
		tips.setInsurer(owner);
		tips.setInsured(owner);
		
		PolicySchema schema = new PolicySchema();
		schema.setCommercialStart("2017-08-19 00:00:00");
		schema.setCompulsiveStart("2017-08-19 00:00:00");
		Map<CommercialInsuranceType, Insurance> insurances = new HashMap<CommercialInsuranceType, Insurance>();
		insurances.put(CommercialInsuranceType.DAMAGE, new Insurance(1));
		insurances.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new Insurance(1));
		schema.setInsurances(insurances);
		tips.setSchema(schema);
	}
}
