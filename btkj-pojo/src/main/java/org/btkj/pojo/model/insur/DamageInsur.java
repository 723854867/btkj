package org.btkj.pojo.model.insur;

import java.io.Serializable;

/**
 * 车损险
 * 
 * @author ahab
 */
public class DamageInsur implements Serializable {

	private static final long serialVersionUID = 3522931254243550617L;

	private Boolean fullClaim;
	private GlassInsur glassInsur;			// 玻璃破碎险
	private Insur fireInsur;				// 自燃损失险
	private Insur scrathInsur;				// 车身划痕险
	private Insur wadingInsur;				// 涉水险
	private Boolean majorPlant;				// 指定专修厂
	private Boolean unknownThird;			// 车损无法找到第三方

	public Boolean isFullClaim() {
		return fullClaim;
	}

	public void setFullClaim(Boolean fullClaim) {
		this.fullClaim = fullClaim;
	}

	public GlassInsur getGlassInsur() {
		return glassInsur;
	}

	public void setGlassInsur(GlassInsur glassInsur) {
		this.glassInsur = glassInsur;
	}

	public Insur getFireInsur() {
		return fireInsur;
	}

	public void setFireInsur(Insur fireInsur) {
		this.fireInsur = fireInsur;
	}

	public Insur getScrathInsur() {
		return scrathInsur;
	}

	public void setScrathInsur(Insur scrathInsur) {
		this.scrathInsur = scrathInsur;
	}

	public Insur getWadingInsur() {
		return wadingInsur;
	}

	public void setWadingInsur(Insur wadingInsur) {
		this.wadingInsur = wadingInsur;
	}

	public Boolean getMajorPlant() {
		return majorPlant;
	}

	public void setMajorPlant(Boolean majorPlant) {
		this.majorPlant = majorPlant;
	}

	public Boolean getUnknownThird() {
		return unknownThird;
	}

	public void setUnknownThird(Boolean unknownThird) {
		this.unknownThird = unknownThird;
	}

	public Boolean getFullClaim() {
		return fullClaim;
	}

	/**
	 * 玻璃破碎险
	 * 
	 * @author ahab
	 */
	public class GlassInsur implements Serializable {
		private static final long serialVersionUID = -220386459422532066L;
		private boolean imported;

		public boolean isImported() {
			return imported;
		}

		public void setImported(boolean imported) {
			this.imported = imported;
		}
	}
}
