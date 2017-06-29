package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.HashMap;

import org.btkj.vehicle.rule.bonus.route.BonusRoute;

public class Motor extends BonusRoute<BonusRoute<?>> {

	public Motor() {
		super("motor", "摩托车");
		_init();
	}
	
	private void _init() {
		this.children = new HashMap<String, BonusRoute<?>>();
		this.children.put("D012", new BonusRoute<BonusRoute<?>>("D012", "摩托车50CC-250CC（含）"));
		this.children.put("D022", new BonusRoute<BonusRoute<?>>("D022", "摩托车250CC以上及侧三轮"));
		this.children.put("D032", new BonusRoute<BonusRoute<?>>("D032", "摩托车50CC及以下"));
	}
}
