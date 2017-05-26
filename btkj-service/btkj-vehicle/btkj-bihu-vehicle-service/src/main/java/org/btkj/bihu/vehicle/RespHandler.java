package org.btkj.bihu.vehicle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.btkj.bihu.vehicle.exception.RequestFrequently;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.net.http.handler.SyncRespHandler;

public class RespHandler<T> extends SyncRespHandler<T> {
	
	private Class<T> clazz;
	
	private RespHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		try {
			if (statusLine.getStatusCode() >= 300) {
				if (statusLine.getStatusCode() == 429) 
					throw new RequestFrequently(EntityUtils.toString(entity));
				else
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase() + " --- " + EntityUtils.toString(entity));
			}
			if (null == entity)
				throw new ClientProtocolException("Response contains no content");
			return parseResult(entity);
		} finally {
			EntityUtils.consume(entity);
		}
	}
	
	@Override
	protected T parseResult(HttpEntity entity) throws UnsupportedOperationException, IOException {
		ContentType contentType = ContentType.getOrDefault(entity);
		Charset charset = contentType.getCharset();
		if (null == charset)
			charset = Charset.forName("utf-8");
		Reader reader = new InputStreamReader(entity.getContent(), charset);
		T instance = SerializeUtil.JsonUtil.GSON.fromJson(reader, clazz);
		return instance;
	}
	
	public static <T> RespHandler<T> build(Class<T> clazz) { 
		return new RespHandler<T>(clazz);
	}
}
