package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.model.insur.DamageInsur;
import org.btkj.pojo.model.insur.Insur;

/**
 * 续保信息
 * 
 * @author ahab
 */
public class Renew implements Serializable {

	private static final long serialVersionUID = -5539153078697021869L;
	
	private UserInfo owner;						// 车主信息
	private UserInfo insurer;					// 投保人信息
	private UserInfo insured;					// 被保人信息
	private VehicleInsur vehicleInsur;

	public class UserInfo implements Serializable {

		private static final long serialVersionUID = -7014564388146612297L;

		private String name;
		private IDType idType;
		private String idNo;
		private String mobile;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public IDType getIdType() {
			return idType;
		}

		public void setIdType(IDType idType) {
			this.idType = idType;
		}

		public String getIdNo() {
			return idNo;
		}

		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
	}
	
	public class VehicleInfo implements Serializable {
		
	}
	
	/**
	 * 险种信息
	 * @author ahab
	 */
	public class VehicleInsur implements Serializable {
		private static final long serialVersionUID = 9173178875694654678L;
		private String compulsoryInsurEnd;		// 交强险截止日期
		private String commercialInsurEnd;		// 商业险截止日期
		private String compulsoryInsurBegin;	// 交强险起保日期
		private String commercialInsurBegin;	// 商业险起保日期
		private DamageInsur damageInsur;			// 车损险
		private Insur thirdLiabilityInsur;			// 第三者责任险
		private Insur driverSeatInsur;				// 司机座位险
		private Insur passengerSeatInsur;			// 乘客座位险
		private Insur robberyInsur;					// 盗抢险
		public String getCompulsoryInsurEnd() {
			return compulsoryInsurEnd;
		}
		public void setCompulsoryInsurEnd(String compulsoryInsurEnd) {
			this.compulsoryInsurEnd = compulsoryInsurEnd;
		}
		public String getCommercialInsurEnd() {
			return commercialInsurEnd;
		}
		public void setCommercialInsurEnd(String commercialInsurEnd) {
			this.commercialInsurEnd = commercialInsurEnd;
		}
		public String getCompulsoryInsurBegin() {
			return compulsoryInsurBegin;
		}
		public void setCompulsoryInsurBegin(String compulsoryInsurBegin) {
			this.compulsoryInsurBegin = compulsoryInsurBegin;
		}
		public String getCommercialInsurBegin() {
			return commercialInsurBegin;
		}
		public void setCommercialInsurBegin(String commercialInsurBegin) {
			this.commercialInsurBegin = commercialInsurBegin;
		}
		public DamageInsur getDamageInsur() {
			return damageInsur;
		}
		public void setDamageInsur(DamageInsur damageInsur) {
			this.damageInsur = damageInsur;
		}
		public Insur getThirdLiabilityInsur() {
			return thirdLiabilityInsur;
		}
		public void setThirdLiabilityInsur(Insur thirdLiabilityInsur) {
			this.thirdLiabilityInsur = thirdLiabilityInsur;
		}
		public Insur getDriverSeatInsur() {
			return driverSeatInsur;
		}
		public void setDriverSeatInsur(Insur driverSeatInsur) {
			this.driverSeatInsur = driverSeatInsur;
		}
		public Insur getPassengerSeatInsur() {
			return passengerSeatInsur;
		}
		public void setPassengerSeatInsur(Insur passengerSeatInsur) {
			this.passengerSeatInsur = passengerSeatInsur;
		}
		public Insur getRobberyInsur() {
			return robberyInsur;
		}
		public void setRobberyInsur(Insur robberyInsur) {
			this.robberyInsur = robberyInsur;
		}
	}
}
