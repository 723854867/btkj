package org.btkj.pojo.enums;

import java.math.BigDecimal;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.entity.vehicle.PoundageNode;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.math.compare.BigDecimalComparable;
import org.rapid.util.math.compare.IntComparable;

/**
 * 节点类型
 * 
 * @author ahab
 */
public enum PoundageType {
	BIZ(1) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			switch (usedType) {
			case LEASE:
			case CITY_BUS:
			case BIZ_TRUCK:
			case HIGHWAY_TRANSPORT:
				return true;
			default:
				return false;
			}
		}
	},						
	NO_BIZ(2) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			switch (usedType) {
			case ORGAN:
			case HOME_USE:
			case ENTERPRISE:
			case NO_BIZ_TRUCK:
				return true;
			default:
				return false;
			}
		}
	},
	IGNORE_BIZ(2 << 1) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			switch (usedType) {
			case MOTOR:
			case TRACTOR:
			case PARTICULAR:
				return true;
			default:
				return false;
			}
		}
	},
	
	COACH(2 << 2) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			switch (usedType) {
			case ORGAN:
			case LEASE:
			case CITY_BUS:
			case HOME_USE:
			case ENTERPRISE:
			case HIGHWAY_TRANSPORT:
				return true;
			default:
				return false;
			}
		}
	},
	TRUCK(2 << 3) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			switch (usedType) {
			case BIZ_TRUCK:
			case NO_BIZ_TRUCK:
				return true;
			default:
				return false;
			}
		}
	},
	MOTOR(2 << 4) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.MOTOR;
		}
	},
	TRACTOR(2 << 5) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.TRACTOR;
		}
	},
	PARTICULAR(2 << 6) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.PARTICULAR;
		}
	},
	
	NO_BIZ_COACH_DEPT(2 << 7),
	NO_BIZ_COACH_BRAND(2 << 8),
	NO_BIZ_COACH_MODEL(2 << 9),
	NO_BIZ_COACH_SPECIAL(2 << 10) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			return true;
		}
	},
	NO_BIZ_COACH_TRANSFER(2 << 11) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			return order.getTips().isTransfer();
		}
	},
	
	LEASE(2 << 12) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.LEASE;
		}
	},
	ORGAN(2 << 13) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.ORGAN;
		}
	},
	HIGHWAY(2 << 14) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.HIGHWAY_TRANSPORT;
		}
	},
	HOME_USE(2 << 15) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.HOME_USE;
		}
	},
	CITY_BUS(2 << 16) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.CITY_BUS;
		}
	},
	ENTERPRISE(2 << 17) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			VehicleUsedType usedType = order.getTips().getUsedType();
			return usedType == VehicleUsedType.ENTERPRISE;
		}
	},
	
	LOAD(2 << 18) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			BigDecimal load = new BigDecimal(order.getTips().getLoad());
			return BigDecimalComparable.SINGLETON.compare(node.getComparison(), load, CollectionUtil.toBigDecimalArray(node.getCval()));
		}
	},
	SEAT_COUNT(2 << 19) {
		@Override
		public boolean matches(VehicleOrder order, PoundageNode node) {
			int seat = Integer.valueOf(order.getTips().getSeat());
			return IntComparable.SINGLETON.compare(node.getComparison(), seat, CollectionUtil.toIntegerArray(node.getCval()));
		}
	};
	
	public boolean matches(VehicleOrder order, PoundageNode node) {
		return false;
	}
	
	private int mod;
	
	private PoundageType(int mod) {
		this.mod = mod;
	}
	
	public int mod() {
		return mod;
	}
}
