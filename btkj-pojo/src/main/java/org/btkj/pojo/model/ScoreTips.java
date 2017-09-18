package org.btkj.pojo.model;

import java.io.Serializable;

import org.btkj.pojo.enums.ScoreType;

public class ScoreTips implements Serializable {

	private static final long serialVersionUID = 3345968518203099275L;

	private int score;
	private ScoreType type;
	
	public ScoreTips() {}
	
	public ScoreTips(ScoreType type, int score) {
		this.type = type;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public ScoreType getType() {
		return type;
	}
	
	public void setType(ScoreType type) {
		this.type = type;
	}
}
