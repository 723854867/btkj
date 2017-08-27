package org.btkj.common;

import org.rapid.util.common.enums.Protocol;

public class Config {

	private static int port;
	private static String host;
	private static Protocol protocol;
	
	public static int getPort() {
		return port;
	}
	
	public static void setPort(int port) {
		Config.port = port;
	}
	
	public static String getHost() {
		return host;
	}
	
	public static void setHost(String host) {
		Config.host = host;
	}
	
	public static Protocol getProtocol() {
		return protocol;
	}
	
	public static void setProtocol(String protocol) {
		Config.protocol = Protocol.match(protocol);
	}
}
