package model;

public class Building {
	private int buildingID;
	private String buildingName;
	private int totalFloors;
	private String buildingStatus;

	// Constructor
	public Building(int buildingID, String buildingName, int totalFloors, String buildingStatus) {
		this.buildingID = buildingID;
		this.buildingName = buildingName;
		this.totalFloors = totalFloors;
		this.buildingStatus = buildingStatus;
	}

	// Getters và Setters
	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public String getBuildingStatus() {
		return buildingStatus;
	}

	public void setBuildingStatus(String buildingStatus) {
		this.buildingStatus = buildingStatus;
	}

	// Phương thức hiển thị thông tin tòa nhà (tùy chọn)
	@Override
	public String toString() {
		return "Building{" + "buildingID=" + buildingID + ", buildingName='" + buildingName + '\'' + ", totalFloors="
				+ totalFloors + ", buildingStatus='" + buildingStatus + '\'' + '}';
	}
}
