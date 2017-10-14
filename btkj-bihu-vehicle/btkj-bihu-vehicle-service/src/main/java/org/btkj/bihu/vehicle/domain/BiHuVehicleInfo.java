package org.btkj.bihu.vehicle.domain;

import java.math.BigDecimal;
import java.util.List;

import org.btkj.bihu.vehicle.RespHandler;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

public class BiHuVehicleInfo {
	
	public static final RespHandler<BiHuVehicleInfo> JSON_HANDLER			= RespHandler.build(BiHuVehicleInfo.class);

	private int BusinessStatus;
	private String StatusMessage;
	private List<Item> Items;
	
	public int getBusinessStatus() {
		return BusinessStatus;
	}

	public void setBusinessStatus(int businessStatus) {
		BusinessStatus = businessStatus;
	}

	public String getStatusMessage() {
		return StatusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}

	public List<Item> getItems() {
		return Items;
	}

	public void setItems(List<Item> items) {
		Items = items;
	}
	
	public Item match(String biHuJYId) { 
		if (CollectionUtil.isEmpty(Items))
			return null;
		if (StringUtil.hasText(biHuJYId)) {
			for (Item item : Items) {
				if (!StringUtil.hasText(item.VehicleNo))
					continue;
				if (item.VehicleNo.equals(biHuJYId))
					return item;
			}
		} 
		BigDecimal price = null;
		Item result = null;
		for (Item item : Items) {
			if (!StringUtil.hasText(item.PurchasePrice) && StringUtil.hasText(item.VehicleAlias)) {
				price = new BigDecimal("0");
				result = item;
			}
			if (null == price) {
				price = new BigDecimal(item.PurchasePrice);
				result = item;
			} else {
				BigDecimal temp = new BigDecimal(item.PurchasePrice);
				if (price.compareTo(temp) > 0) {
					price = temp;
					result = item;
				}
			}
		}
		return result;
	}

	public static class Item {
		private String VehicleNo;
		private String PurchasePrice;
		private String VehicleAlias;
		private String VehicleName;
		private String VehicleSeat;
		private String vehicleExhaust;
		private String VehicleYear;
		public String getVehicleNo() {
			return VehicleNo;
		}
		public void setVehicleNo(String vehicleNo) {
			VehicleNo = vehicleNo;
		}
		public String getPurchasePrice() {
			return PurchasePrice;
		}
		public void setPurchasePrice(String purchasePrice) {
			PurchasePrice = purchasePrice;
		}
		public String getVehicleAlias() {
			return VehicleAlias;
		}
		public void setVehicleAlias(String vehicleAlias) {
			VehicleAlias = vehicleAlias;
		}
		public String getVehicleName() {
			return VehicleName;
		}
		public void setVehicleName(String vehicleName) {
			VehicleName = vehicleName;
		}
		public String getVehicleSeat() {
			return VehicleSeat;
		}
		public void setVehicleSeat(String vehicleSeat) {
			VehicleSeat = vehicleSeat;
		}
		public String getVehicleExhaust() {
			return vehicleExhaust;
		}
		public void setVehicleExhaust(String vehicleExhaust) {
			this.vehicleExhaust = vehicleExhaust;
		}
		public String getVehicleYear() {
			return VehicleYear;
		}
		public void setVehicleYear(String vehicleYear) {
			VehicleYear = vehicleYear;
		}
	}
}
