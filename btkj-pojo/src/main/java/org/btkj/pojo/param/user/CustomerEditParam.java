package org.btkj.pojo.param.user;

import java.util.LinkedList;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.custom.CarLicense;
import org.rapid.util.validator.custom.Identity;
import org.rapid.util.validator.custom.Mobile;

public class CustomerEditParam extends Param {

	private static final long serialVersionUID = 424902363125819779L;
	
	private int uid;

	@NotNull(groups = { ValidateGroups.DELETE.class, ValidateGroups.UPDATE.class })
	private Long id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String name;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Identity(groups = { ValidateGroups.CRUD.class })
	private String identity;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Mobile(groups = { ValidateGroups.CRUD.class })
	private String mobile;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@CarLicense(groups = { ValidateGroups.CRUD.class })
	private String license;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private Integer region;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String address;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String memo;
	private LinkedList<Region> regions;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LinkedList<Region> getRegions() {
		return regions;
	}

	public void setRegions(LinkedList<Region> regions) {
		this.regions = regions;
	}
}
