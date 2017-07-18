package org.btkj.pojo.po;

import org.rapid.util.common.model.UniqueModel;

public class Comment implements UniqueModel<Integer> {

	private static final long serialVersionUID = -7225396113472103909L;

	private int id;
	private int articleId;
	private int uid;
	private String content;
	private int created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
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
