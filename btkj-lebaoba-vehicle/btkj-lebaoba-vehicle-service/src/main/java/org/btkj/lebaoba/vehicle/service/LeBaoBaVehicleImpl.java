package org.btkj.lebaoba.vehicle.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.lebaoba.vehicle.domain.BasicResult;
import org.btkj.lebaoba.vehicle.domain.VehicleInfos;
import org.btkj.lebaoba.vehicle.domain.VehicleSubmit;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.crypto.DesUtil;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.handler.SyncStrRespHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("leBaoBaVehicle")
public class LeBaoBaVehicleImpl implements LeBaoBaVehicle {
	
	private static final Logger logger = LoggerFactory.getLogger(LeBaoBaVehicleImpl.class);
	
	@Resource
	private HttpProxy httpProxy;
	@Value("${lebaoba.username}")
	private String username;
	@Value("${lebaoba.password}")
	private String  password;
	@Value("${lebaoba.des.key}")
	private String desKey;
	@Value("${vehicle.info.url}")
	private String vehicleInfoUrl;
	
	@Override
	public List<VehicleInfo> vehicleInfos(String vin) {
		VehicleSubmit submit = new VehicleSubmit();
		submit.setUsername(username);
		submit.setPassword(password);
		submit.setVin(vin);
		String xml = SerializeUtil.XmlUtil.beanToXml(submit, Consts.UTF_8.name());
		xml = DesUtil.EncryptDES(xml, desKey);
		xml = new String(Base64.encodeBase64(xml.getBytes()));
		
		HttpPost post = new HttpPost(vehicleInfoUrl);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("xmlData", xml));
		post.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
		String result;
		try {
			result = httpProxy.syncRequest(post, SyncStrRespHandler.INSTANCE);
		} catch (IOException e) {
			logger.error("乐保吧车辆信息获取失败!", e);
			return null;
		}
		BasicResult br = SerializeUtil.XmlUtil.xmlToBean(result, BasicResult.class);
		VehicleInfos infos = SerializeUtil.XmlUtil.xmlToBean(new String(Base64.decodeBase64(br.getContent())), VehicleInfos.class);
		return infos.infos();
	}

	@Override
	public Result<Void> order(EmployeeForm employeeForm, Set<Insurer> quote, Set<Insurer> insure, VehiclePolicyTips tips) {
		return Result.result(Code.SYSTEM_ERROR);
	}
}
