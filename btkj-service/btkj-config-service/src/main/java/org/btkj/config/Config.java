package org.btkj.config;

import org.rapid.util.common.Env;

public class Config {

	private static Env env;
	
	public static Env getEnv() {
		return env;
	}
	
	public static void setEnv(String env) {
		Config.env = Env.match(env);
	}
}
