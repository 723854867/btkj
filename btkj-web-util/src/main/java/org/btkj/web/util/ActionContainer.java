package org.btkj.web.util;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.model.Version;
import org.btkj.web.util.request.NetworkRequest;

/**
 * 单例模式就可以了：注意同一个 name 和  version 只有一个唯一的 IAction， IAction 是非线程安全的。
 * 
 * @author ahab
 */
public enum ActionContainer {
	
	INSTANCE;

	private Map<String, Map<Version, IAction<?>>>  container = new HashMap<String, Map<Version, IAction<?>>>();
	
	public void addAction(IAction<?> action) {
		String name = action.getClass().getSimpleName().toLowerCase();
		Map<Version, IAction<?>> map = container.get(name);
		if (null == map) {
			map = new HashMap<Version, IAction<?>>();
			container.put(name, map);
		}
		map.put(action.version(), action);
	}
	
	@SuppressWarnings("unchecked")
	public <REQUEST extends NetworkRequest, ACTION extends IAction<REQUEST>> ACTION getAction(String name, Version version) {
		Map<Version, IAction<?>> map = container.get(name);
		return null == map ? null : (ACTION) map.get(version);
	}
}
