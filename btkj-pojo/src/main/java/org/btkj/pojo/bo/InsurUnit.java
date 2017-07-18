package org.btkj.pojo.bo;

import java.io.Serializable;

import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.UnitType;

/**
 * 保险个体
 * 
 * @author ahab
 */
public class InsurUnit implements Serializable {

	private static final long serialVersionUID = 4769388048627555288L;

	private UnitType type;				// 类型
	private String name;				// 名称
	private String mobile;				// 手机号：如果是单位则一般是联系人的手机号
	private IDType idType;				// 证件类型
	private String idNo;				// 证件号

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
}
