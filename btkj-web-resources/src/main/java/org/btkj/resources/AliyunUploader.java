package org.btkj.resources;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;

@Service("aliyunUploader")
public class AliyunUploader {
	
	private static final Logger logger = LoggerFactory.getLogger(AliyunUploader.class);

	@Value("${aliyun.oss.endpoint}")
	private String endpoint;
	@Value("${aliyun.oss.key}")
	private String accessKeyId;
	@Value("${aliyun.oss.secret}")
	private String accessKeySecret;
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	
	private OSSClient client;
	
	@PostConstruct
	private void init() {
		this.client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}
	
	public void upload(String key, InputStream input) {
		PutObjectResult result;
		try {
			result = this.client.putObject(bucket, key, input);
			System.out.println(result.getETag());
		} catch (OSSException | ClientException e) {
			logger.error("阿里云资源上传失败!", e);
		}
	}
	
	@PreDestroy
	private void dispose() {
		if (null != this.client) {
			this.client.shutdown();
			this.client = null;
		}
	}
	
	public static void main(String[] args) {
		OSSClient client = new OSSClient("http://oss-cn-hangzhou.aliyuncs.com", "LTAIB0QaLZExkFcW", "IdwGMJHAPWtVP0e5e2lK2FcNKS8tRS");
		ByteArrayInputStream inputStream = new ByteArrayInputStream("hello".getBytes());
		client.putObject("btkj-test", "test", inputStream);
	}
}
