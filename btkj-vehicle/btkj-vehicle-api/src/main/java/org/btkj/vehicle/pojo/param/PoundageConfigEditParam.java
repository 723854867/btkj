package org.btkj.vehicle.pojo.param;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorCoefficient;
import org.rapid.util.common.Consts;

public class PoundageConfigEditParam extends EmployeeParam {

	private static final long serialVersionUID = -6538523637011056257L;

	private int tid;
	@NotNull
	private Type type;
	private Node node;
	@Min(1)
	private int insurerId;
	@NotNull
	@Size(min = 1)
	private LinkedList<Integer> nodePath;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public LinkedList<Integer> getNodePath() {
		return nodePath;
	}
	
	public void setNodePath(LinkedList<Integer> nodePath) {
		this.nodePath = nodePath;
	}
	
	public String key() {
		return tid + Consts.SYMBOL_UNDERLINE + insurerId;
	}
	
	public enum Type {
		EDIT,					// 编辑节点配置：新增和修改
		DELETE,					// 删除配置
		EFFECTIVE,				// 使节点生效
		INEFFECTIVE;			// 使节点失效
	}

	public class Node implements Serializable {
		private static final long serialVersionUID = -5220776473827685585L;
		private int commercialRate;
		private int compulsoryRate;
		private int commercialRetainRate;
		private int compulsoryRetainRate;
		private Map<Integer, MirrorCoefficient> coefficients;

		public int getCommercialRate() {
			return commercialRate;
		}

		public void setCommercialRate(int commercialRate) {
			this.commercialRate = commercialRate;
		}

		public int getCompulsoryRate() {
			return compulsoryRate;
		}

		public void setCompulsoryRate(int compulsoryRate) {
			this.compulsoryRate = compulsoryRate;
		}

		public int getCommercialRetainRate() {
			return commercialRetainRate;
		}

		public void setCommercialRetainRate(int commercialRetainRate) {
			this.commercialRetainRate = commercialRetainRate;
		}

		public int getCompulsoryRetainRate() {
			return compulsoryRetainRate;
		}

		public void setCompulsoryRetainRate(int compulsoryRetainRate) {
			this.compulsoryRetainRate = compulsoryRetainRate;
		}

		public Map<Integer, MirrorCoefficient> getCoefficients() {
			return coefficients;
		}
		
		public void setCoefficients(Map<Integer, MirrorCoefficient> coefficients) {
			this.coefficients = coefficients;
		}
	}
}
