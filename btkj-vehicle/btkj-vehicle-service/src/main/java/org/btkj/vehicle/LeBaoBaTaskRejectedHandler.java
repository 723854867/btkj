package org.btkj.vehicle;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeBaoBaTaskRejectedHandler implements RejectedExecutionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(LeBaoBaTaskRejectedHandler.class);

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		LeBaoBaOrderTask task = (LeBaoBaOrderTask) r;
		task.onReject();
		logger.warn("乐保吧报价资源耗尽，报价失败！");
	}
}
