package org.btkj.vehicle.pojo.entity;

import java.io.Serializable;
import java.util.Map;

import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.enums.BonusScaleType;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.vehicle.pojo.VehiclePolicyType;
import org.rapid.util.common.model.UniqueModel;

public class VehiclePolicy implements UniqueModel<String> {

	private static final long serialVersionUID = -7788126900244400122L;

	private String _id;
	private int tid;									// 商户ID
	private int appId;
	private int insurerId;								// 险企ID
	private String owner;								// 车主姓名
	private String idNo;								// 车主证件号
	private String issueDate;							// 发证日期
	private String enrollDate;							// 初登日期
	private String license;								// 车牌号
	private String engine;								// 发动机号
	private String vin;									// 车架号
	private String name;								// 厂牌型号
	private String seat;								// 座位数
	private PolicyNature nature;						// 转续保类型
	private boolean transfer;							// 是否过户车
	private VehiclePolicyType type;						// 保单类型
	private BonusScaleType scaleType;					// 规模奖励类型
	private String vehiclePrice;						// 购置价
	private PolicyMark mark;							// 保单标记类型
	private int salesmanId;								// 业务员ID
	private String salesman;							// 业务员姓名
	private String salesmanMobile;						// 业务员手机号
	private OrderDetail orderDetail;					// 对应的订单详情(只有在保途平台生成的保单才有对应的订单)
	private CommercialPolicyDetail commercialDetail;
	private CompulsoryPolicyDetail compulsoryDetail;
	private Map<CommercialInsuranceType, Insurance> insurances;
	
	public VehiclePolicy() {}
	
	public VehiclePolicy(EmployeePO employee, int insurerId, String id) {
		this._id = id;
		this.insurerId = insurerId;
		this.tid = employee.getTid();
		this.appId = employee.getAppId();
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public PolicyNature getNature() {
		return nature;
	}

	public void setNature(PolicyNature nature) {
		this.nature = nature;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	
	public VehiclePolicyType getType() {
		return type;
	}
	
	public void setType(VehiclePolicyType type) {
		this.type = type;
	}

	public BonusScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(BonusScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public String getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(String vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}
	
	public PolicyMark getMark() {
		return mark;
	}
	
	public void setMark(PolicyMark mark) {
		this.mark = mark;
	}
	
	public int getSalesmanId() {
		return salesmanId;
	}
	
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	
	public String getSalesman() {
		return salesman;
	}
	
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanMobile() {
		return salesmanMobile;
	}
	
	public void setDetail(BaseInfo info) {
		if (null == info)
			return;
		if (info.getBdType().equals(InsuranceType.COMMERCIAL.title())) 
			this.commercialDetail = new CommercialPolicyDetail(info);
		else 
			this.compulsoryDetail = new CompulsoryPolicyDetail(info);
	}

	public void setSalesmanMobile(String salesmanMobile) {
		this.salesmanMobile = salesmanMobile;
	}
	
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Map<CommercialInsuranceType, Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(Map<CommercialInsuranceType, Insurance> insurances) {
		this.insurances = insurances;
	}
	
	public void bindVehicleOrder(VehicleOrder order) {
		this.orderDetail = new OrderDetail();
		this.orderDetail.setOrderId(order.get_id());
	}
	
	private class CommercialPolicyDetail implements Serializable {
		private static final long serialVersionUID = -9084610822609798686L;
		private double price;							// 保费	
		private String no;								// 保单号
		private String deliverNo;						// 投保单号
		private String startDate;						// 起保时间
		private String issueDate;						// 出单时间
		public CommercialPolicyDetail() {}
		public CommercialPolicyDetail(BaseInfo info) {
			this.price = info.getBf();
			this.no = info.getBDH();
			this.deliverNo = info.getTBDH();
			this.startDate = info.getQbrq();
			this.issueDate = info.getSkrq();
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getNo() {
			return no;
		}
		public void setNo(String no) {
			this.no = no;
		}
		public String getDeliverNo() {
			return deliverNo;
		}
		public void setDeliverNo(String deliverNo) {
			this.deliverNo = deliverNo;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(String issueDate) {
			this.issueDate = issueDate;
		}
	}
	
	private class CompulsoryPolicyDetail extends CommercialPolicyDetail {
		private static final long serialVersionUID = 861842819371203409L;
		private double vesselPrice;						// 车船税
		public CompulsoryPolicyDetail() {}
		public CompulsoryPolicyDetail(BaseInfo info) {
			super(info);
			this.vesselPrice = info.getCCS();
		}
		public double getVesselPrice() {
			return vesselPrice;
		}
		public void setVesselPrice(double vesselPrice) {
			this.vesselPrice = vesselPrice;
		}
	}
	
	public class OrderDetail implements Serializable {
		private static final long serialVersionUID = -4809816366810831775L;
		private String orderId;								
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
	}
	
	public enum PolicyMark {
		NORMAL(1),
		NO_EMPLOYEE(2),
		EMPLOYEE_UNSUITABLE(4);
		private int mark;
		private PolicyMark(int mark) {
			this.mark = mark;
		}
		public int mrk() {
			return mark;
		}
	}

	@Override
	public String key() {
		return _id;
	}
}
