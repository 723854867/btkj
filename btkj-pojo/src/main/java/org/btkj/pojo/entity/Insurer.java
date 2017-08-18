package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Insurer implements UniqueModel<Integer> {

	private static final long serialVersionUID = -4411281170930448555L;

	private int id;
	private String name;
	private String icon;
	private int biHuId;
	private String leBaoBaId;
	private int created;
	private int updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getBiHuId() {
		return biHuId;
	}
	
	public void setBiHuId(int biHuId) {
		this.biHuId = biHuId;
	}
	
	public String getLeBaoBaId() {
		return leBaoBaId;
	}
	
	public void setLeBaoBaId(String leBaoBaId) {
		this.leBaoBaId = leBaoBaId;
	}
	
	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}
	
	@Override
	public Integer key() {
		return id;
	}
}
