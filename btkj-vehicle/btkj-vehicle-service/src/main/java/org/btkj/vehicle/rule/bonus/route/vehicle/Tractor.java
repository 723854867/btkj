package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.HashMap;

import org.btkj.vehicle.rule.bonus.route.BonusRoute;

public class Tractor extends BonusRoute<BonusRoute<?>> {

	public Tractor() {
		super("tractor", "拖拉机");
		_init();
	}
	
	private void _init() {
		this.children = new HashMap<String, BonusRoute<?>>();
		this.children.put("E012", new BonusRoute<BonusRoute<?>>("E012", "兼用型拖拉机14.7KW以上"));
		this.children.put("E022", new BonusRoute<BonusRoute<?>>("E022", "运输型拖拉机14.7KW以上"));
		this.children.put("E112", new BonusRoute<BonusRoute<?>>("E112", "兼用型拖拉机14.7KW及以下"));
		this.children.put("E122", new BonusRoute<BonusRoute<?>>("E122", "运输型拖拉机14.7KW及以下"));
		this.children.put("E201", new BonusRoute<BonusRoute<?>>("E201", "变形拖拉机14.7KW及以下"));
		this.children.put("E202", new BonusRoute<BonusRoute<?>>("E202", "变形拖拉机14.7KW-17.6KW及以下"));
		this.children.put("E203", new BonusRoute<BonusRoute<?>>("E203", "变形拖拉机17.6KW-50KW及以下"));
		this.children.put("E204", new BonusRoute<BonusRoute<?>>("E204", "变形拖拉机50KW-80KW及以下"));
		this.children.put("E205", new BonusRoute<BonusRoute<?>>("E205", "变形拖拉机80KW以上"));
		this.children.put("E301", new BonusRoute<BonusRoute<?>>("E301", "超标拖拉机14.7KW及以下"));
		this.children.put("E302", new BonusRoute<BonusRoute<?>>("E302", "超标拖拉机14.7KW-17.6KW及以下"));
		this.children.put("E303", new BonusRoute<BonusRoute<?>>("E303", "超标拖拉机17.6KW-50KW及以下"));
		this.children.put("E304", new BonusRoute<BonusRoute<?>>("E304", "超标拖拉机50KW-80KW及以下"));
		this.children.put("E305", new BonusRoute<BonusRoute<?>>("E305", "超标拖拉机80KW以上"));
	}
}
