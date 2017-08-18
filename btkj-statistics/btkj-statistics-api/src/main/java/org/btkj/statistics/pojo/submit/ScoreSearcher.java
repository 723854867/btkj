package org.btkj.statistics.pojo.submit;

import org.btkj.pojo.info.StatisticSearcher;

public class ScoreSearcher extends StatisticSearcher {

	private static final long serialVersionUID = 7911034040418114258L;

	private Integer scoreTypeMod;
	private Integer employeeId;
	private Integer uid;
	private Integer tid;
	private Integer appId;

	public Integer getScoreTypeMod() {
		return scoreTypeMod;
	}

	public void setScoreTypeMod(Integer scoreTypeMod) {
		this.scoreTypeMod = scoreTypeMod;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}
}
