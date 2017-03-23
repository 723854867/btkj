package org.btkj.pojo.config;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.rapid.util.common.Constants;
import org.rapid.util.common.ZkUtil;
import org.rapid.util.common.serializer.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalConfigContainer {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalConfigContainer.class);

	private ZkClient zkClient;
	private String configPath;
	private GlobalConfig globalConfig;
	
	public GlobalConfigContainer(ZkClient zkClient, String configPath) {
		this.zkClient = zkClient;
		this.configPath = configPath;
		_init();
	}
	
	private void _init() {
		if (!zkClient.exists(configPath))
			zkClient.create(configPath, null, CreateMode.PERSISTENT);
		GlobalConfig temp = ZkUtil.readJson(zkClient, configPath, GlobalConfig.class);
		globalConfig = null == temp ? GlobalConfig.DEFAULT_CONFIG : temp;
		zkClient.subscribeDataChanges(configPath, new IZkDataListener() {
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				logger.info("Global config deleted!");
				globalConfig = GlobalConfig.DEFAULT_CONFIG;
			}
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				logger.info("Global config changed!");
				if (null == data)
					return;
				if (data.getClass() != byte[].class) {
					logger.error("Config data type error!");
					return;
				}
				byte[] buffer = (byte[]) data;
				try {
					GlobalConfig temp = SerializeUtil.JsonUtil.GSON.fromJson(new String(buffer, Constants.UTF_8), GlobalConfig.class);
					globalConfig = temp;
				} catch (Exception e) {
					logger.error("Config change failure!");
				}
			}
		});
	}
	
	public GlobalConfig getGlobalConfig() {
		return globalConfig;
	}
}
