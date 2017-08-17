package org.btkj.bihu.vehicle.domain;

import java.util.List;

import org.btkj.bihu.vehicle.RespHandler;

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

	private class Item {
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
