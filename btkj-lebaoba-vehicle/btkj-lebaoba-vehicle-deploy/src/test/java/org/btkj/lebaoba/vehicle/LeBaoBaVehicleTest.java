package org.btkj.lebaoba.vehicle;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.lebaoba.BaseTest;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.junit.Test;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;

public class LeBaoBaVehicleTest extends BaseTest {

	private String username = "cxdlzjcx";
	private String password = "111111";
	@Resource
	private LeBaoBaVehicle leBaoBaVehicle;
	
	@Test
	public void testVehicleInfos() {
		List<VehicleInfo> list = leBaoBaVehicle.vehicleInfos(null, null, "WBSDX9108BE370935");
		System.out.println(SerializeUtil.JsonUtil.GSON.toJson(list));
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
						+ "\"price\":40000.0,"
						+"\"exhaust\":1.20600,"
						+"\"vehicleId\":\"6240188\","
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
					+ "}";
		VehicleOrderParam param = SerializeUtil.JsonUtil.GSON.fromJson(json, VehicleOrderParam.class);
		Result<PolicySchema> result = leBaoBaVehicle.order(username, password, "PAZYCX", true, param);
		System.out.println(result.getCode() + " " + result.getDesc());
	}
}
