package org.btkj.pojo.enums;

import org.rapid.util.common.serializer.json.GsonEnum;

/**
 * 排序字段
 * 
 * @author ahab
 *
 */
public enum SortField implements GsonEnum<SortField> {
	
	CREATED("创建时间"),
	
	UPDATED("修改时间"),
	
	SALES("销量"),
	
	REBATE("返利"),
	
	PRICE("价格");
	
	private String field;
	
	private SortField(String field) {
		this.field = field;
	}
	
	public String field() {
		return field;
	}
	
	public String statisticField() {
		return name().toLowerCase();
	}
	
	public static final SortField match(String field) {
		for (SortField temp : SortField.values()) {
			if (temp.field.equals(field))
				return temp;
		}
		throw new IllegalArgumentException("There is not enum names with [" + field + "] of type SortField exists! ");
	}
	
	@Override
	public String serialize() {
		return this.field;
	}
	
	@Override
	public SortField deserialize(String jsonEnum) {
		return SortField.match(jsonEnum);
	}
}
