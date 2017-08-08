package org.btkj.pojo;

import org.rapid.util.common.consts.code.ICode;

/**
 * 所有 btkj 的公共 code 都在改类中定义。0~500 是公共预留 code，1000以上是参数错误码
 * 
 * @author ahab
 */
public enum BtkjCode implements ICode {
	
	/**
	 * action 不存在
	 */
	ACTION_NOT_EXIST(500, "action not exist"),
	
	/**
	 * app 已经下架了
	 */
	APP_NOT_EXIST(501, "app not exist"),
	
	/**
	 * 已经存在申请了
	 * 
	 */
	APPLY_EXIST(502, "apply exist"),
	
	/**
	 * 申请不存在
	 */
	APPLY_NOT_EXIST(503, "apply not exist"),
	
	/**
	 * 已经是雇员了
	 */
	ALREADY_IS_EMPLOYEE(504, "already is employee"),
	
	/**
	 * 手机号已经被占用了
	 */
	MOBILE_EXIST(505, "mobile exist"),
	
	/**
	 * 用户代理商个数最大值
	 */
	USER_TENANT_NUM_MAXIMUM(506, "tenant number maximum"),
	
	/**
	 * app 大力上个数最大值
	 */
	APP_TENANT_NUM_MAXIMUM(507, "tenant number maximum"),
	
	/**
	 * 代理公司不存在
	 */
	TENANT_NOT_EXIST(508, "tenant not exist"),
	
	/**
	 * 雇员不存在
	 */
	EMPLOYEE_NOT_EXIST(509, "employee not exist"),
	
	/**
	 * 用户资料不全
	 */
	USER_DATA_INCOMPLETE(510, "user data is incomplete"),
	
	/**
	 * 非车险类型不存在
	 */
	NON_AUTO_CATEGORY_NOT_EXIST(511, "non auto category not exist!"),
	
	/**
	 * 续保信息获取超时
	 */
	RENEW_INFO_GET_TIMEOUT(512, "renew info get timeout!"),
	
	/**
	 * 续保信息获取失败
	 */
	RENEW_INFO_GET_FAILURE(513, "renew info get failure!"),
	
	/**
	 * 获取车辆信息成功(车架号，发动机号，品牌型号，初登日期可以取到)，获取险种失败
	 */
	RENEW_INFO_VEHICLE_ONLY(514, "renew info vehicle only"),
	
	/**
	 * 线路不存在
	 */
	LANE_NOT_EXIST(514, "lane not exist"),
	
	/**
	 * 没有配置路由
	 */
	LANE_CONFIG_ERROR(515, "lane config error"),
	
	/**
	 * 壁虎线路未开通
	 */
	LANE_BIHU_NOT_OPENED(516, "lane of bihu not opened!"),
	
	/**
	 * 险企不存在
	 */
	INSURER_NOT_EXIST(518, "insurer not exist"),
	
	/**
	 * 报价失败
	 */
	QUOTE_FAILURE(520, "quote failure"),
	
	/**
	 * 核保失败
	 */
	INSURE_FAILURE(521, "insure failure"),
	
	/**
	 * 重复投保
	 */
	INSURE_REPEAT(522, "insure repeat"),
	
	/**
	 * 没有报价
	 */
	NOT_QUOTE(523, "not quote"),
	
	/**
	 * 壁虎请求太频繁
	 */
	BIHU_REQUEST_FREQUENTLY(524, "bihu request frequently"),
	
	/**
	 * 车主名字错误
	 */
	CAR_OWNER_NAME_ERROR(525, "car owner name error"),
	
	/**
	 * 订单不存在
	 */
	ORDER_NOT_EXIST(526, "order not exist!"),
	
	/**
	 * 订单状态错误
	 */
	ORDER_STATE_ERROR(527, "order state error"),
	
	/**
	 * 不在续保期内：起保时间太长
	 */
	NOT_IN_RENEWAL_PERIOD(528, "not in renewal period"),
	
	/**
	 * 路由不存在
	 */
	ROUTE_NOT_EXIST(529, "route not eixst"),
	
	/**
	 * 地区不存在
	 */
	REGION_NOT_EXIST(540, "region not exist"),
	
	/**
	 * 地区
	 */
	AREA_NOT_EXIST(530, "area not exist"),
	
	/**
	 * 车险系数不存在
	 */
	COEFFICIENT_NOT_EXIST(550, "coefficient not exist"),
	
	/**
	 * 车险系数最大值
	 */
	COEFFICIENT_NUM_MAXMIUM(551, "coefficient num maxmium"),
	
	/**
	 * 管理奖励配置项不存在
	 */
	BONUS_MANAGE_CONFIG_NOT_EXIST(552, "bonus manage config not exist"),
	
	/**
	 * 没有设置规模奖励口径
	 */
	BONUS_SCALE_SETTINGS_ERROR(553, "bonus scale settings error"),
	
	/**
	 * 规模奖励配置项不存在
	 */
	BONUS_SCALE_CONFIG_NOT_EXIST(554, "bonus scale config not exist"),
	
	/**
	 * 规模奖励审核记录不存在
	 */
	BONUS_SCALE_NOT_EXIST(555, "bonus scale not exist"),
	
	/**
	 * 规模奖励配置项最大值
	 */
	BONUS_SCALE_CONFIG_MAXMIUM(556, "bonus scale config maxmium"),
	
	/**
	 * 规模奖励已经统计过
	 */
	BONUS_SCALE_REWARDED(557, "bonus scale rewarded"),
	
	/**
	 * 保单不存在
	 */
	POLICY_NOT_EXIST(560, "policy not exist"),
	
	/**
	 * 文章不存在
	 */
	ARTICLE_NOT_EXIST(600, "article not exits"),
	
	/**
	 * 咨询数达到最大值
	 */
	ARTICLE_NUM_MAXIMUM(601, "article num maximum"),
	
	/**
	 * 提问不存在
	 */
	QUIZ_NOT_EXIST(601, "quiz not exits"),
	
	/**
	 * 回复不存在
	 */
	REPLY_NOT_EXIST(602, "reply not exist"),
	
	/**
	 * 评论不存在
	 */
	COMMENT_NOT_EXIST(603, "comment not exist"),
	
	/**
	 * 汽车品牌不存在
	 */
	VEHICLE_BRAND_NOT_EXSIT(650, "vehicle brand not exist"),
	
	/**
	 * 汽车车系不存在
	 */
	VEHICLE_DEPT_NOT_EXIST(651, "vehicle dept not exist"),
	
	/**
	 * 汽车厂牌型号不存在
	 */
	VEHICLE_MODEL_NOT_EXIST(652, "vehicle model not exist"),
	
	/**
	 * 车辆种类不存在
	 */
	VEHICLE_TYPE_NOT_EXIST(653, "vehicle type not exist"),
	
	/**
	 * 客户不存在
	 */
	CUSTOMER_NOT_EXIST(660, "customer not exist"),
	
	/**
	 * 客户身份证冲突
	 */
	CUSTOMER_IDENTITY_DUPLICATE(661, "customer identity duplicate"),
	
	/**
	 * 轮播图不存在
	 */
	BANNER_NOT_EXIST(670, "banner not exist"),
	
	/**
	 * 未设置简捷id
	 */
	JIAN_JIE_ID_NEEDED(680, "jian jie id needed"),
	
	/**
	 * 商户壁虎配置不存在
	 */
	BI_HU_TENANT_CONFIG_NOT_EXIST(690, "bi hu tenant config not exist"),
	
	/**
	 * 模块不存在
	 */
	MODULAR_NOT_EXIST(691, "modular not exist!"),
	
	/**
	 * 模块已经绑定了接口，要先解绑接口才可以删除模块
	 */
	MODULAR_API_BINDED(692, "modular api binded!");
	
	private int code;
	private String desc;
	
	private BtkjCode(int id, String desc) {
		this.code = id;
		this.desc = desc;
	}

	@Override
	public int id() {
		return code;
	}

	@Override
	public String key() {
		throw new UnsupportedOperationException("Code has no key attribute!");
	}

	@Override
	public String value() {
		return desc;
	}
}
