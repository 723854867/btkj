package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.HashMap;

import org.btkj.vehicle.rule.bonus.route.BonusRoute;

public class Truck extends BonusRoute<BonusRoute<?>> {

	public Truck() {
		super("truck", "货车");
		_init();
	}
	
	private void _init() {
		this.children = new HashMap<String, BonusRoute<?>>();
		this.children.put("B012", new BonusRoute<BonusRoute<?>>("D012", "2吨以下货车"));
		this.children.put("B022", new BonusRoute<BonusRoute<?>>("D022", "2及2吨以上5吨以下货车"));
		this.children.put("B032", new BonusRoute<BonusRoute<?>>("D032", "5及5吨以上10吨以下货车"));
		this.children.put("B042", new BonusRoute<BonusRoute<?>>("D012", "10吨以上货车"));
		this.children.put("B052", new BonusRoute<BonusRoute<?>>("D022", "低速载货汽车"));
		this.children.put("B062", new BonusRoute<BonusRoute<?>>("D032", "三轮农用运输车"));
		this.children.put("B101", new BonusRoute<BonusRoute<?>>("D012", "低速载货汽车14.7KW及以下"));
		this.children.put("B102", new BonusRoute<BonusRoute<?>>("D022", "低速载货汽车14.7KW-17.6KW及以下"));
		this.children.put("B103", new BonusRoute<BonusRoute<?>>("D032", "低速载货汽车17.6KW-50KW及以下"));
		this.children.put("B104", new BonusRoute<BonusRoute<?>>("D012", "低速载货汽车50KW-80KW及以下"));
		this.children.put("B105", new BonusRoute<BonusRoute<?>>("D022", "低速载货汽车80KW以上"));
		this.children.put("B201", new BonusRoute<BonusRoute<?>>("D032", "功率小于等于14.7KW的三轮汽车"));
		this.children.put("B202", new BonusRoute<BonusRoute<?>>("D022", "功率大于14.7KW小于等于17.6KW的三轮汽车"));
		this.children.put("B203", new BonusRoute<BonusRoute<?>>("D032", "功率大于17.6KW小于等于50KW的三轮汽车"));
		this.children.put("B204", new BonusRoute<BonusRoute<?>>("D012", "功率大于50KW小于等于80KW的三轮汽车"));
		this.children.put("B205", new BonusRoute<BonusRoute<?>>("D022", "功率大于80KW的三轮汽车"));
		this.children.put("C112", new BonusRoute<BonusRoute<?>>("D032", "挂车(2吨以下)"));
		this.children.put("C122", new BonusRoute<BonusRoute<?>>("D012", "挂车(2-5吨)"));
		this.children.put("C132", new BonusRoute<BonusRoute<?>>("D022", "挂车(5-10吨)"));
		this.children.put("C142", new BonusRoute<BonusRoute<?>>("D032", "挂车(10吨以上)"));
	}
}
