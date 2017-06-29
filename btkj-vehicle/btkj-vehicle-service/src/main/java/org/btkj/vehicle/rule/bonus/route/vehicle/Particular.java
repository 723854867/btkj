package org.btkj.vehicle.rule.bonus.route.vehicle;

import java.util.HashMap;

import org.btkj.vehicle.rule.bonus.route.BonusRoute;

public class Particular extends BonusRoute<BonusRoute<?>> {

	public Particular() {
		super("particular", "特种车");
		_init();
	}
	
	private void _init() {
		this.children = new HashMap<String, BonusRoute<?>>();
		this.children.put("C112", new BonusRoute<BonusRoute<?>>("C112", "挂车(2吨以下)"));
		this.children.put("C122", new BonusRoute<BonusRoute<?>>("C122", "挂车(2-5吨)"));
		this.children.put("C132", new BonusRoute<BonusRoute<?>>("C132", "挂车(5-10吨)"));
		this.children.put("C142", new BonusRoute<BonusRoute<?>>("C142", "挂车(10吨以上)"));
		this.children.put("C152", new BonusRoute<BonusRoute<?>>("C152", "挂车(特种车一)"));
		this.children.put("C162", new BonusRoute<BonusRoute<?>>("C162", "挂车(特种车二，罐体)"));
		this.children.put("C172", new BonusRoute<BonusRoute<?>>("C172", "挂车(特种车二，冷藏、保温)"));
		this.children.put("C182", new BonusRoute<BonusRoute<?>>("C182", "挂车(特种车三)"));
		
		BonusRoute<BonusRoute<?>> route = new BonusRoute<BonusRoute<?>>("B142", "特种车四：集装箱拖头");
		route.addChiled(new BonusRoute<BonusRoute<?>>("3401", "集装箱车头"));
		this.children.put("B142", route);
		
		route = new BonusRoute<BonusRoute<?>>("C022", "特种车一：油罐车、汽罐车、液罐车、冷藏车");
		route.addChiled(new BonusRoute<BonusRoute<?>>("3101", "油罐车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3102", "汽罐车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3103", "液罐车"));
		this.children.put("C022", route);
		
		route = new BonusRoute<BonusRoute<?>>("C032", "特种车二：专用净水车、特种车一以外的罐式货车,用于清障、清扫、清洁、起重、装卸（不含自卸车）、升降、搅拌、挖掘、推土、冷藏、保温车等的各种专用机动车");
		route.addChiled(new BonusRoute<BonusRoute<?>>("3201", "清障车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3202", "清扫车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3203", "清洁车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3204", "起重机"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3205", "装卸车(不含自卸车)"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3206", "升降机"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3207", "搅拌车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3208", "挖掘机"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3209", "推土机"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3212", "其他工程类轮式车辆"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3213", "保温车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3215", "冷藏、保温挂车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3216", "压路车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3217", "矿山车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3218", "油气田操作用车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3219", "罐式挂车(除油罐、汽罐、液罐之外的)"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3220", "净水车"));
		this.children.put("C032", route);
		
		route = new BonusRoute<BonusRoute<?>>("C042", "特种车三：装有固定专用仪器设备从事专业工作的监测、消防、运钞、医疗、电视转播的各种专用机动车");
		route.addChiled(new BonusRoute<BonusRoute<?>>("3301", "其它"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3302", "电视转播车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3303", "消防车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3304", "医疗车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3305", "救护车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3306", "监测车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3307", "雷达车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3308", "X光检查车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3309", "电信工程车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3310", "电力工程车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3311", "专业拖车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3312", "邮电车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3313", "运钞车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3314", "警用车"));
		route.addChiled(new BonusRoute<BonusRoute<?>>("3315", "仪器设备挂车"));
		this.children.put("C042", route);
	}
}
