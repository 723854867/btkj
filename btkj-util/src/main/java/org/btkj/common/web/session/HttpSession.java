package org.btkj.common.web.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapid.util.common.Constants;
import org.rapid.util.common.consts.Const;
import org.rapid.util.common.consts.conveter.StrConstConverter;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.StringUtils;
import org.springframework.http.HttpHeaders;

/**
 * Http Session
 * 
 * @author ahab
 */
public class HttpSession implements ISession {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 默认采用 json/utf-8 编码
	 * 
	 * @param request
	 * @param response
	 */
	public HttpSession(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		response.setHeader(HttpHeaders.CONTENT_TYPE, Constants.MimeType.TEXT_JSON_UTF_8);
		response.setCharacterEncoding(Constants.UTF_8.name());
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
	public <T> T getParam(StrConstConverter<T> constant) throws ConstConvertFailureException {
		String val = request.getParameter(constant.key());
		if (!StringUtils.hasText(val))
			throw ConstConvertFailureException.nullConstException(constant);
		try {
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
	public <T> T getOptionalParam(StrConstConverter<T> constant) {
		try {
			return getParam(constant);
		} catch (ConstConvertFailureException e) {
			return constant.value();
		}
	}
	
	public HttpSession addHeader(String name, String value) {
		response.addHeader(name, value);
		return this;
	}

	public HttpSession setHeader(String name, String value) {
		response.setHeader(name, value);
		return this;
	}
	
	public <T> T getHeader(Const<T> constant) throws ConstConvertFailureException {
		String val = request.getHeader(constant.key());
		if (!StringUtils.hasText(val))
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
	
	/**
	 * <pre>
	 * 字符串默认用 PrintWriter 写出；PrintWriter 以字符为单位，好处是不需要自己来完成从字符串到字节数组的转换，
	 * 转换字符集编码是通过设置 setContentType 或 setCharacterEncoding 或 setLocale 等方法来实现的；
	 * </pre>
	 * 
	 * @param reply
	 */
	@Override
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
	@Override
	public void write(byte[] reply) {
		try {
			response.getOutputStream().write(reply);
		} catch (IOException e) {
			throw new RuntimeException("Servlet Response writer failure!", e);
		}
	}
}
