package org.btkj.lebaoba.vehicle;

import javax.annotation.Resource;

import org.btkj.lebaoba.BaseTest;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.junit.Test;
import org.rapid.util.common.serializer.SerializeUtil;

public class LeBaoBaVehicleTest extends BaseTest {

	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		leBaoBaVehicle.vehicleInfos("WBSDX9108BE370935");
	}
	
	@Test
	public void testOrder() {
		String json = "{"
						+ "\"vehicleUsedType\":\"HOME_USE\","
						+ "\"name\":\"五菱LZW6407BF客车\","
						+ "\"license\":\"浙A229TF\","
						+ "\"vin\":\"LZWACAGA5C7161890\","
						+ "\"engine\":\"UC82020095\","
						+ "\"enrollDate\":\"2012-08-29\","
						+ "\"seat\":7,"
						+ "\"transfer\":false,"
						+ "\"owner\":{"
							+ "\"type\":\"PERSONAL\","
							+ "\"mobile\":\"18811112222\","
							+ "\"name\":\"汪乃平\","
							+ "\"idType\":\"IDENTITY\","
							+ "\"idNo\":\"34282219690707151X\""
						+ "},"
							+ "\"insurer\":{"
							+ "\"type\":\"PERSONAL\","
							+ "\"mobile\":\"18811112222\","
							+ "\"name\":\"汪乃平\","
							+ "\"idType\":\"IDENTITY\","
							+ "\"idNo\":\"34282219690707151X\""
						+ "},"
						+ "\"insured\":{"
							+ "\"type\":\"PERSONAL\","
							+ "\"mobile\":\"18811112222\","
							+ "\"name\":\"汪乃平\","
							+ "\"idType\":\"IDENTITY\","
							+ "\"idNo\":\"34282219690707151X\""
						+ "},"
						+ "\"schema\":{"
							+ "\"compulsiveStart\":\"2017-08-30 00:00:00\","
							+ "\"commercialStart\":\"2017-08-30 00:00:00\","
							+ "\"insurances\":{"
								+ "\"DAMAGE\":{"
									+ "\"price\":0,"
									+ "\"quota\":1"
								+ "},"
								+ "\"DAMAGE_DEDUCTIBLE\":{"
									+ "\"price\":0,"
									+ "\"quota\":1"
								+ "},"
								+ "\"THIRD\":{"
									+ "\"price\":0,"
									+ "\"quota\":500000"
								+ "},"
								+ "\"THIRD_DEDUCTIBLE\":{"
									+ "\"price\":0,"
									+ "\"quota\":1}"
								+ ",\"DRIVER\":{"
									+ "\"price\":0,"
									+ "\"quota\":10000"
								+ "},"
								+ "\"DRIVER_DEDUCTIBLE\":{"
									+ "\"price\":0,"
									+ "\"quota\":1"
								+ "},"
								+ "\"PASSENGER\":{"
									+ "\"price\":0,"
									+ "\"quota\":10000"
								+ "},"
								+ "\"PASSENGER_DEDUCTIBLE\":{"
									+ "\"price\":0,"
									+ "\"quota\":1"
								+ "}"
							+ "}"
						+ "}"
					+ "}";
		VehiclePolicyTips tips = SerializeUtil.JsonUtil.GSON.fromJson(json, VehiclePolicyTips.class);
		leBaoBaVehicle.order(null, null, null, tips);
	}
}
