package org.btkj.web.util.action;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.model.Version;

/**
 * 单例模式就可以了：注意同一个 name 和  version 只有一个唯一的 IAction， IAction 是非线程安全的。
 * 
 * @author ahab
 */
public enum ActionContainer {
	
	INSTANCE;

	private Map<String, Map<Version, Action>>  container = new HashMap<String, Map<Version, Action>>();
	
	public void addAction(Action action) {
		String name = action.getClass().getSimpleName().toLowerCase();
		Map<Version, Action> map = container.get(name);
		if (null == map) {
			map = new HashMap<Version, Action>();
			container.put(name, map);
		}
		map.put(action.version(), action);
	}
	
	@SuppressWarnings("unchecked")
	public <ACTION extends Action> ACTION getAction(String name, Version version) {
		Map<Version, Action> map = container.get(name);
		return null == map ? null : (ACTION) map.get(version);
	}
}
