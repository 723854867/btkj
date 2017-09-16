package org.btkj.pojo.info.statistics;

import java.io.Serializable;

import org.btkj.pojo.enums.BizType;

public class StatisticScoreInfo implements Serializable {

	private static final long serialVersionUID = -5475705383725429948L;

	private int uid;
	private int tid;
	private int appId;
	private int employeeId;
	private BizType type;
	private int detailType;
	private int quota;
	private int year;
	private int month;
	private int day;
	private int week;
	private int season;
	private int created;
}
