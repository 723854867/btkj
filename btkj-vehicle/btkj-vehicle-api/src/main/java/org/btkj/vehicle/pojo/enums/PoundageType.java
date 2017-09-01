package org.btkj.vehicle.pojo.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * 节点类型
 * 
 * @author ahab
 */
public enum PoundageType {

	BIZ,				
	NO_BIZ,	
	IGNORE_BIZ,		
		
	COACH,			
	TRUCK,			
	MOTOR,			
	TRACTOR,			
	PARTICULAR,		
	
	TRANSFER,		
	
	SPECIAL,
	VEHICLE_BRAND {
		@Override
		public Set<Integer> coefficients() {
			Set<Integer> set = new HashSet<Integer>();
			set.add(1);
			set.add(2);
			set.add(3);
			set.add(4);
			set.add(5);
			set.add(6);
			set.add(40);
			set.add(41);
			set.add(42);
			return set;
		}
	},
	VEHICLE_DEPT {
		@Override
		public Set<Integer> coefficients() {
			Set<Integer> set = new HashSet<Integer>();
			set.add(1);
			set.add(2);
			set.add(3);
			set.add(4);
			set.add(5);
			set.add(6);
			set.add(40);
			set.add(41);
			set.add(42);
			return set;
		}
	},
	VEHICLE_NAME {
		@Override
		public Set<Integer> coefficients() {
			Set<Integer> set = new HashSet<Integer>();
			set.add(1);
			set.add(2);
			set.add(3);
			set.add(4);
			set.add(5);
			set.add(6);
			set.add(40);
			set.add(41);
			set.add(42);
			return set;
		}
	},
	
	HOME_USE,		
	ORGAN,			
	ENTERPRISE,		
	LEASE,			
	CITY_BUS,	
	HIGHWAY,		
	
	SEAT_COUNT,				
	LOAD;				
	
	public Set<Integer> coefficients() {
		return null;
	}
}
