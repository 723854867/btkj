package org.btkj.pojo.entity.community;

import org.rapid.util.common.model.UniqueModel;

public class Reply implements UniqueModel<Integer> {

	private static final long serialVersionUID = -3510882237441432959L;

	private int id;
	private int quizId;
	private int uid;
	private String content;
	private int created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
