package org.btkj.payment.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.enums.BizType;

public class ScoreTips implements Serializable {

	private static final long serialVersionUID = 3345968518203099275L;

	private int score;
	private BizType type;
	
	public ScoreTips() {}
	
	public ScoreTips(BizType type, int score) {
		this.type = type;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public BizType getType() {
		return type;
	}
	
	public void setType(BizType type) {
		this.type = type;
	}
}
