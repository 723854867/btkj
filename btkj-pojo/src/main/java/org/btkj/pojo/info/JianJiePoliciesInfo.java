package org.btkj.pojo.info;

import java.io.Serializable;
import java.util.List;

public class JianJiePoliciesInfo implements Serializable {

	private static final long serialVersionUID = -2099449433980822376L;

	private boolean SuccessStatus;
	private String ErrorMessage;
	private List<BaseInfo> Result;

	public boolean isSuccessStatus() {
		return SuccessStatus;
	}

	public void setSuccessStatus(boolean successStatus) {
		SuccessStatus = successStatus;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public List<BaseInfo> getResult() {
		return Result;
	}

	public void setResult(List<BaseInfo> result) {
		Result = result;
	}

	public class BaseInfo implements Serializable {
		private static final long serialVersionUID = -1429463769083089882L;
		private String BdType;
		private String Cxcs;
		private String Cz;
		private String CzZjhm;
		private String Bbr;
		private String BbrZjhm;
		private double Bf;
		private double CCS;
		private String Qdrq;
		private String Qbrq;
		private String Skrq;
		private String LshBd;
		private String LShBzBk;
		private String LshFp;
		private int CompanyId;
		private String Company;
		private String Dept;
		private String ChannelSource;
		private VehicleInfomation vehicleInfomation;
		private List<Insurance> insurances;
		private String ChannelType;
		private String CdUser;
		private String CdPhone;
		private String CdDept;
		private String GsUser;
		private String GsDept;
		private String GsPhone;
		private String AccountName;
		private boolean isCouple;
		private String RelationPolicyNo;			// 关联保单号
		private String BaseStatus;
		private String BDH;
		private String TBDH;

		public String getBdType() {
			return BdType;
		}

		public void setBdType(String bdType) {
			BdType = bdType;
		}

		public String getCxcs() {
			return Cxcs;
		}

		public void setCxcs(String cxcs) {
			Cxcs = cxcs;
		}

		public String getCz() {
			return Cz;
		}

		public void setCz(String cz) {
			Cz = cz;
		}
		
		public String getCzZjhm() {
			return CzZjhm;
		}
		
		public void setCzZjhm(String czZjhm) {
			CzZjhm = czZjhm;
		}

		public String getBbr() {
			return Bbr;
		}

		public void setBbr(String bbr) {
			Bbr = bbr;
		}

		public String getBbrZjhm() {
			return BbrZjhm;
		}

		public void setBbrZjhm(String bbrZjhm) {
			BbrZjhm = bbrZjhm;
		}

		public double getBf() {
			return Bf;
		}

		public void setBf(double bf) {
			Bf = bf;
		}

		public double getCCS() {
			return CCS;
		}

		public void setCCS(double cCS) {
			CCS = cCS;
		}

		public String getQdrq() {
			return Qdrq;
		}

		public void setQdrq(String qdrq) {
			Qdrq = qdrq;
		}

		public String getQbrq() {
			return Qbrq;
		}

		public void setQbrq(String qbrq) {
			Qbrq = qbrq;
		}
		
		public String getSkrq() {
			return Skrq;
		}
		
		public void setSkrq(String skrq) {
			Skrq = skrq;
		}

		public String getLshBd() {
			return LshBd;
		}

		public void setLshBd(String lshBd) {
			LshBd = lshBd;
		}

		public String getLShBzBk() {
			return LShBzBk;
		}

		public void setLShBzBk(String lShBzBk) {
			LShBzBk = lShBzBk;
		}

		public String getLshFp() {
			return LshFp;
		}

		public void setLshFp(String lshFp) {
			LshFp = lshFp;
		}

		public int getCompanyId() {
			return CompanyId;
		}

		public void setCompanyId(int companyId) {
			CompanyId = companyId;
		}

		public String getCompany() {
			return Company;
		}

		public void setCompany(String company) {
			Company = company;
		}

		public String getDept() {
			return Dept;
		}

		public void setDept(String dept) {
			Dept = dept;
		}

		public String getChannelSource() {
			return ChannelSource;
		}

		public void setChannelSource(String channelSource) {
			ChannelSource = channelSource;
		}

		public VehicleInfomation getVehicleInfomation() {
			return vehicleInfomation;
		}

		public void setVehicleInfomation(VehicleInfomation vehicleInfomation) {
			this.vehicleInfomation = vehicleInfomation;
		}

		public List<Insurance> getInsurances() {
			return insurances;
		}

		public void setInsurances(List<Insurance> insurances) {
			this.insurances = insurances;
		}

		public String getChannelType() {
			return ChannelType;
		}

		public void setChannelType(String channelType) {
			ChannelType = channelType;
		}

		public String getCdUser() {
			return CdUser;
		}

		public void setCdUser(String cdUser) {
			CdUser = cdUser;
		}

		public String getCdPhone() {
			return CdPhone;
		}

		public void setCdPhone(String cdPhone) {
			CdPhone = cdPhone;
		}

		public String getCdDept() {
			return CdDept;
		}

		public void setCdDept(String cdDept) {
			CdDept = cdDept;
		}

		public String getGsUser() {
			return GsUser;
		}

		public void setGsUser(String gsUser) {
			GsUser = gsUser;
		}

		public String getGsDept() {
			return GsDept;
		}

		public void setGsDept(String gsDept) {
			GsDept = gsDept;
		}

		public String getGsPhone() {
			return GsPhone;
		}

		public void setGsPhone(String gsPhone) {
			GsPhone = gsPhone;
		}

		public String getAccountName() {
			return AccountName;
		}

		public void setAccountName(String accountName) {
			AccountName = accountName;
		}

		public boolean isCouple() {
			return isCouple;
		}

		public void setCouple(boolean isCouple) {
			this.isCouple = isCouple;
		}
		
		public String getRelationPolicyNo() {
			return RelationPolicyNo;
		}
		
		public void setRelationPolicyNo(String relationPolicyNo) {
			RelationPolicyNo = relationPolicyNo;
		}

		public String getBaseStatus() {
			return BaseStatus;
		}

		public void setBaseStatus(String baseStatus) {
			BaseStatus = baseStatus;
		}

		public String getBDH() {
			return BDH;
		}

		public void setBDH(String bDH) {
			BDH = bDH;
		}

		public String getTBDH() {
			return TBDH;
		}

		public void setTBDH(String tBDH) {
			TBDH = tBDH;
		}
	}

	public class VehicleInfomation implements Serializable {
		private static final long serialVersionUID = -1972036034439582993L;
		private String Cphm;
		private String Cllx;
		private String Cdrq;
		private String NewCarCost;
		private String Ppxh;
		private String Ssxz;
		private String Fdjh;
		private String Cjh;
		private String Zws;
		private String Dw;
		private String Fzrq;
		private boolean GH;
		private String PI;

		public String getCphm() {
			return Cphm;
		}

		public void setCphm(String cphm) {
			Cphm = cphm;
		}

		public String getCllx() {
			return Cllx;
		}

		public void setCllx(String cllx) {
			Cllx = cllx;
		}

		public String getCdrq() {
			return Cdrq;
		}

		public void setCdrq(String cdrq) {
			Cdrq = cdrq;
		}

		public String getNewCarCost() {
			return NewCarCost;
		}

		public void setNewCarCost(String newCarCost) {
			NewCarCost = newCarCost;
		}

		public String getPpxh() {
			return Ppxh;
		}

		public void setPpxh(String ppxh) {
			Ppxh = ppxh;
		}

		public String getSsxz() {
			return Ssxz;
		}

		public void setSsxz(String ssxz) {
			Ssxz = ssxz;
		}

		public String getFdjh() {
			return Fdjh;
		}

		public void setFdjh(String fdjh) {
			Fdjh = fdjh;
		}

		public String getCjh() {
			return Cjh;
		}

		public void setCjh(String cjh) {
			Cjh = cjh;
		}

		public String getZws() {
			return Zws;
		}

		public void setZws(String zws) {
			Zws = zws;
		}

		public String getDw() {
			return Dw;
		}

		public void setDw(String dw) {
			Dw = dw;
		}

		public String getFzrq() {
			return Fzrq;
		}

		public void setFzrq(String fzrq) {
			Fzrq = fzrq;
		}

		public boolean isGH() {
			return GH;
		}

		public void setGH(boolean gH) {
			GH = gH;
		}

		public String getPI() {
			return PI;
		}

		public void setPI(String pI) {
			PI = pI;
		}
	}

	public class Insurance implements Serializable {
		private static final long serialVersionUID = -7126480569300059496L;
		private String Name;
		private String Bf;
		private String Be;
		private String MpBf;

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getBf() {
			return Bf;
		}

		public void setBf(String bf) {
			Bf = bf;
		}

		public String getBe() {
			return Be;
		}

		public void setBe(String be) {
			Be = be;
		}
		
		public String getMpBf() {
			return MpBf;
		}
		
		public void setMpBf(String mpBf) {
			MpBf = mpBf;
		}
	}
}
