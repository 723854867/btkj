package org.btkj.lebaoba.vehicle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.lebaoba.BaseTest;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.InsuredInfo;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.LogUser;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.UnitInfo;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.VehicleInfo;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.VehicleInsuranceItem;
import org.junit.Test;
import org.rapid.util.common.Consts;
import org.rapid.util.common.serializer.SerializeUtil;

public class LeBaoBaVehicleTest extends BaseTest {

	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		leBaoBaVehicle.vehicleInfos("WBSDX9108BE370935");
	}
	
	public static void main(String[] args) {
		OrderSubmit submit = new OrderSubmit();
		
		UnitInfo owner = new UnitInfo();
		owner.setCustomerType("01");
		owner.setIdNo("01");
		owner.setName("张三");
		owner.setIdNo("330127198");
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setID("ssss");
		vehicleInfo.setLicenseFlag(1);
		vehicleInfo.setLicenseNo("xxx");
		vehicleInfo.setVin("xxxx");
		vehicleInfo.setEngineNo("xxxx");
		vehicleInfo.setModelCode("xxx");
		vehicleInfo.setEnrollDate("xxx");
		vehicleInfo.setLoanFlag(1);
		vehicleInfo.setTransferFlag(1);
		vehicleInfo.setTransferFlagTime("xxx");
		vehicleInfo.setLicenseTypeCode("02");
		vehicleInfo.setCarTypeCode("K33");
		vehicleInfo.setVehicleType(1);
		vehicleInfo.setVehicleTypeCode("A012");
		vehicleInfo.setUseNature(2);
		vehicleInfo.setCountryNature("01");
		vehicleInfo.setIsRenewal(0);
		vehicleInfo.setInsVehicleId("xxx");
		vehicleInfo.setPrice("129091");
		vehicleInfo.setPriceNoTax("123800");
		vehicleInfo.setYear("201201");
		vehicleInfo.setName("大众FV7146FBMGG轿车");
		vehicleInfo.setExhaust("1.39");
		vehicleInfo.setBrandName("一汽大众");
		vehicleInfo.setLoadWeight("0");
		vehicleInfo.setSeat(2);
		vehicleInfo.setTaxType(1);
		vehicleInfo.setCarOwnerInfo(owner);
		
		submit.setVehicleInfo(vehicleInfo);
		InsuredInfo toInsuredInfo = new InsuredInfo();
		toInsuredInfo.setCustomerType("01");
		toInsuredInfo.setIdNo("01");
		toInsuredInfo.setName("张三");
		toInsuredInfo.setIdNo("330127198");
		InsuredInfo beInsuredInfo = new InsuredInfo();
		beInsuredInfo.setCustomerType("01");
		beInsuredInfo.setIdNo("01");
		beInsuredInfo.setName("张三");
		beInsuredInfo.setIdNo("330127198");
		submit.setToInsuredInfo(toInsuredInfo);
		submit.setBeInsuredInfo(beInsuredInfo);
		submit.setCommercePolicyBeginDate("xxx");
		submit.setCommercePolicyEndDate("xxx");
		submit.setCompulsoryPolicyBeginDate("xxx");
		submit.setCompulsoryPolicyEndDate("xxx");
		submit.setProductCode("xxx");
		submit.setCompanyProvince("xxx");
		submit.setProxyCompanyID(1);
		
		List<VehicleInsuranceItem> list = new ArrayList<VehicleInsuranceItem>();
		for (int i = 0; i < 10; i++){
			VehicleInsuranceItem insuranceItem = new VehicleInsuranceItem();
			insuranceItem.setCode("Z" + i);
			insuranceItem.setAmount("97802");
			list.add(insuranceItem);
		}
		submit.setVehicleInsurance(list);
		
		LogUser logUser = new LogUser();
		logUser.setUsername("xxx");
		logUser.setPassword("xxx");
		submit.setLogUser(logUser);
		String xml = SerializeUtil.XmlUtil.beanToXml(submit, Consts.UTF_8.name());
		System.out.println(xml);
	}
}
