package org.btkj.web.util.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.btkj.web.util.Params;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.Const;
import org.rapid.util.common.consts.conveter.str.StrConstConverter;
import org.rapid.util.common.converter.ConstConverter;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.net.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Http 请求
 * 
 * @author ahab
 */
public class Request {
	
	private static final Logger logger = LoggerFactory.getLogger(Request.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> params;
	
	/**
	 * 默认采用 json/utf-8 编码
	 * 
	 * @param request
	 * @param response
	 */
	public Request(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.params = new HashMap<String, Object>();
		Map<String, String[]> temp = request.getParameterMap();
		if (null != temp) {
			for (Entry<String, String[]> entry : temp.entrySet()) {
				if (null == entry.getValue())
					continue;
				String[] arr = entry.getValue();
				if (null != arr[0])
					this.params.put(entry.getKey(), arr[0]);
			}
		}
		if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.name()))
			_parseParam();
		response.setHeader(HttpHeaders.CONTENT_TYPE, Consts.MimeType.TEXT_JSON_UTF_8);
		response.setCharacterEncoding(Consts.UTF_8.name());
	}
	
	private void _parseParam() {
		MediaType mediaType = MediaType.parseMediaType(request.getContentType());
		if (mediaType.includes(MediaType.MULTIPART_FORM_DATA)) {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter;
			try {
				iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
				    FileItemStream item = iter.next();
				    InputStream input = item.openStream();
				    if (item.isFormField()) 
				        params.put(item.getFieldName(), Streams.asString(input, Consts.UTF_8.name()));
				    else 
				        params.put(item.getFieldName(), new ByteArrayInputStream(IOUtils.toByteArray(input)));
				}
			} catch (FileUploadException | IOException e) {
				logger.warn("multipart request init failure！", e);
			}
		}
	}
	
	/**
	 * 获取一个全新的分布式 session
	 * 
	 * @return
	 */
	public DistributeSession distributeSession(Redis redis) {
		DistributeSession session = new DistributeSession(redis);
		response.setHeader(Params.SESSION_ID.key(), session.sessionId());
		return session;
	}
	
	/**
	 * 获取一个已经存在的分布式 session
	 * 
	 * @param redis
	 * @param sessionId
	 * @return
	 */
	public DistributeSession distributeSession(Redis redis, String sessionId) {
		 return new DistributeSession(sessionId, redis);
	}
	
	<T> T getParam(String name) {
		try {
			return null == this.params ? null : (T) this.params.get(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * <pre>
	 * 获取 key - value 形式的键值对，URL 之后的参数，以及 x-www-form-urlencoded 编码的参数可以使用此种方式来获取
	 * null 和 "" 默认都是不合法的
	 * </pre>
	 * 
	 * @param constant
	 * @return
	 * @throws IllegalConstException
	 */
	public <S, T> T getParam(ConstConverter<S, T> constant) throws ConstConvertFailureException {
		try {
			S val = (S) this.params.get(constant.key());
			if (null == val)
				throw new Exception();
			return constant.convert(val);
		} catch (Exception e) {
			throw new ConstConvertFailureException(constant, e);
		}
	}
	
	/**
	 * 和 {@link #getParam(StrConstConverter)} 的不同之处在于如果参数不存在或者错误，不会抛出异常，而是返回 {@link StrConstConverter} 的默认值
	 * 
	 * @param constant
	 * @return
	 */
	public <S, T> T getOptionalParam(ConstConverter<S, T> constant) {
		try {
			return getParam(constant);
		} catch (ConstConvertFailureException e) {
			return constant.value();
		}
	}
	
	public Request addHeader(String name, String value) {
		response.addHeader(name, value);
		return this;
	}

	public Request setHeader(String name, String value) {
		response.setHeader(name, value);
		return this;
	}
	
	public <T> T getHeader(Const<T> constant) throws ConstConvertFailureException {
		String val = request.getHeader(constant.key());
		if (!StringUtil.hasText(val))
			throw ConstConvertFailureException.nullConstException(constant);
		try {
			return constant instanceof StrConstConverter ? ((StrConstConverter<T>) constant).convert(val) : constant.value();
		} catch (Exception e) {
			throw new ConstConvertFailureException(constant, e);
		}
	}
	
	public <T> T getOptionalHeader(Const<T> constant) {
		try {
			return getHeader(constant);
		} catch (ConstConvertFailureException e) {
			return constant.value();
		}
	}
	
	public HttpServletRequest servletRequest() {
		return this.request;
	}
	
	/**
	 * <pre>
	 * 字符串默认用 PrintWriter 写出；PrintWriter 以字符为单位，好处是不需要自己来完成从字符串到字节数组的转换，
	 * 转换字符集编码是通过设置 setContentType 或 setCharacterEncoding 或 setLocale 等方法来实现的；
	 * </pre>
	 * 
	 * @param reply
	 */
	public void write(String reply) {
		try {
			response.getWriter().write(reply);
		} catch (IOException e) {
			throw new RuntimeException("Servlet Response writer failure!", e);
		}
	}
	
	/**
	 * <pre>
	 * 字节数组默认用 ServletOutputStream 写出; 直接从一个字节输入流中读取出来，然后再原封不动的输出到客户端。
	 * 使用 PrintWriter 会占用一些系统开销，因为会处理字符流，因此应该使用在确保有字符集转换的环境中。如果确认 servlet
	 * 仅仅是二进制数据交换，则使用 ServletOutputStream，这样可以消除字符转换开销。
	 * </pre>
	 * 
	 * @param reply
	 */
	public void write(byte[] reply) {
		try {
			response.getOutputStream().write(reply);
		} catch (IOException e) {
			throw new RuntimeException("Servlet Response writer failure!", e);
		}
	}
}
