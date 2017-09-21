package org.btkj.courier.deploy.push;

import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

public class PushTest {

	private static final String appId = "";
	private static final String appKey = "";
	private static final String masterSecret = "";
	
	public static void main(String[] args) {
		IGtPush push = new IGtPush(appKey, masterSecret);
		LinkTemplate template = new LinkTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTitle("欢迎使用个推！");
		template.setText("这是一条推送");
		template.setUrl("http://getui.com");
	}
}
