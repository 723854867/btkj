package org.btkj.user.pojo.entity;

/**
 * <pre>
 * 租户的  statsMod 的所有状态值参考 {@link org.btkj.user.pojo.TenantStatus}
 * 
 * ● ISOLATE : 表示是否独立的 app 的租户，如果是则该租户的 tid 就是该租户所对应的 app 的 id；否则 app_id 就是 0，属于保途的租户
 *   
 * </pre>
 * 
 * @author ahab
 */
public class Tenant {

	private int tid;
	private String name;
	private int statsMod;
	private int created;
	private int updated;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatsMod() {
		return statsMod;
	}

	public void setStatsMod(int statsMod) {
		this.statsMod = statsMod;
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
}
