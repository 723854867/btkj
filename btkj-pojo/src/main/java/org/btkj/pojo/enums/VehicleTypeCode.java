package org.btkj.pojo.enums;

/**
 * 车辆种类类型
 * 
 * @author ahab
 */
public enum VehicleTypeCode {

	/**
	 * 六座以下客车
	 */
	A012,
	
	/**
	 * 六座至十座以下客车车
	 */
	A022,
	
	/**
	 * 十座至二十座以下客车
	 */
	A032,
	
	/**
	 * 二十座至三十六座以下客车
	 */
	A042,
	
	/**
	 * 三十六座至三十六座以上客车
	 */
	A052,
	
	/**
	 * 二吨以下货车
	 */
	B012,
	
	/**
	 * 二吨至五吨以下货车
	 */
	B022,
	
	/**
	 * 五吨至十吨以下货车
	 */
	B032,
	
	/**
	 * 十吨以上货车
	 */
	B042,
	
	/**
	 * 低速载货汽车
	 */
	B052,
	
	/**
	 * 三轮农用运输车
	 */
	B062,
	
	/**
	 * 低速载货汽车14.7KW及以下
	 */
	B101,
	
	/**
	 * 低速载货汽车14.7KW-17.6KW及以下
	 */
	B102,
	
	/**
	 * 低速载货汽车17.6KW-50KW及以下
	 */
	B103,
	
	/**
	 * 低速载货汽车50KW-80KW及以下
	 */
	B104,
	
	/**
	 * 低速载货汽车80KW以上
	 */
	B105,
	
	/**
	 * 功率小于等于14.7KW的三轮汽车
	 */
	B201,
	
	/**
	 * 功率大于14.7KW小于等于17.6KW的三轮汽车
	 */
	B202,
	
	/**
	 * 功率大于17.6KW小于等于50KW的三轮汽车
	 */
	B203,
	
	/**
	 * 功率大于50KW小于等于80KW的三轮汽车
	 */
	B204,
	
	/**
	 * 功率大于80KW的三轮汽车
	 */
	B205,
	
	/**
	 * 挂车(2吨以下)
	 */
	C112,
	
	/**
	 * 挂车(2-5吨)
	 */
	C122,
	
	/**
	 * 挂车(5-10吨)
	 */
	C132,
	
	/**
	 * 挂车(10吨以上)
	 */
	C142,
	
	/**
	 * 特种车四：集装箱拖头
	 */
	B142 {
		@Override
		public VehicleTypeDetailCode[] typeDetailCode() {
			return new VehicleTypeDetailCode[]{VehicleTypeDetailCode.V3401};
		}
	},
	
	/**
	 * 特种车一：油罐车、汽罐车、液罐车、冷藏车
	 */
	C022 {
		@Override
		public VehicleTypeDetailCode[] typeDetailCode() {
			return new VehicleTypeDetailCode[]{VehicleTypeDetailCode.V3101, VehicleTypeDetailCode.V3102, 
					VehicleTypeDetailCode.V3103};
		}
	},
	
	/**
	 * 特种车二：专用净水车、特种车一以外的罐式货车,用于清障、清扫、清洁、起重、装卸（不含自卸车）、升降、搅拌、挖掘、推土、冷藏、保温车等的各种专用机动车
	 */
	C032 {
		@Override
		public VehicleTypeDetailCode[] typeDetailCode() {
			return new VehicleTypeDetailCode[]{VehicleTypeDetailCode.V3201, VehicleTypeDetailCode.V3202, VehicleTypeDetailCode.V3203,
					VehicleTypeDetailCode.V3205, VehicleTypeDetailCode.V3206, VehicleTypeDetailCode.V3207, VehicleTypeDetailCode.V3208,
					VehicleTypeDetailCode.V3209, VehicleTypeDetailCode.V3210, VehicleTypeDetailCode.V3212, VehicleTypeDetailCode.V3213,
					VehicleTypeDetailCode.V3214, VehicleTypeDetailCode.V3215, VehicleTypeDetailCode.V3216, VehicleTypeDetailCode.V3217,
					VehicleTypeDetailCode.V3218, VehicleTypeDetailCode.V3219, VehicleTypeDetailCode.V3220};
		}
	},
	
	/**
	 * 特种车三：装有固定专用仪器设备从事专业工作的监测、消防、运钞、医疗、电视转播的各种专用机动车
	 */
	C042 {
		@Override
		public VehicleTypeDetailCode[] typeDetailCode() {
			return new VehicleTypeDetailCode[]{VehicleTypeDetailCode.V3301, VehicleTypeDetailCode.V3302, VehicleTypeDetailCode.V3303,
					VehicleTypeDetailCode.V3304, VehicleTypeDetailCode.V3305, VehicleTypeDetailCode.V3306, VehicleTypeDetailCode.V3307,
					VehicleTypeDetailCode.V3308, VehicleTypeDetailCode.V3309, VehicleTypeDetailCode.V3310, VehicleTypeDetailCode.V3311,
					VehicleTypeDetailCode.V3312, VehicleTypeDetailCode.V3313, VehicleTypeDetailCode.V3314, VehicleTypeDetailCode.V3315};
		}
	},
	
	/**
	 * 挂车(特种车一)
	 */
	C152,
	
	/**
	 * 挂车(特种车二，罐体)
	 */
	C162,
	
	/**
	 * 挂车(特种车二，冷藏、保温)
	 */
	C172,
	
	/**
	 * 挂车(特种车三)
	 */
	C182,
	
	/**
	 * 摩托车50CC-250CC（含）
	 */
	D012,
	
	/**
	 * 摩托车250CC以上及侧三轮
	 */
	D022,
	
	/**
	 * 摩托车50CC及以下
	 */
	D032,
	
	/**
	 * 兼用型拖拉机14.7KW以上
	 */
	E012,
	
	/**
	 * 运输型拖拉机14.7KW以上
	 */
	E022,
	
	/**
	 * 兼用型拖拉机14.7KW及以下
	 */
	E112,
	
	/**
	 * 运输型拖拉机14.7KW及以下
	 */
	E122,
	
	/**
	 * 变形拖拉机14.7KW及以下
	 */
	E201,
	
	/**
	 * 变形拖拉机14.7KW-17.6KW及以下
	 */
	E202,
	
	/**
	 * 变形拖拉机17.6KW-50KW及以下
	 */
	E203,
	
	/**
	 * 变形拖拉机50KW-80KW及以下
	 */
	E204,
	
	/**
	 * 变形拖拉机80KW以上
	 */
	E205,
	
	/**
	 * 超标拖拉机14.7KW及以下
	 */
	E301,
	
	/**
	 * 超标拖拉机14.7KW-17.6KW及以下
	 */
	E302,
	
	/**
	 * 超标拖拉机17.6KW-50KW及以下
	 */
	E303,
	
	/**
	 * 超标拖拉机50KW-80KW及以下
	 */
	E304,
	
	/**
	 * 超标拖拉机80KW以上
	 */
	E305;
	
	public VehicleTypeDetailCode[] typeDetailCode() {
		return null;
	};
}
