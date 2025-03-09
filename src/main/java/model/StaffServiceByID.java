package model;

public class StaffServiceByID {
	private int staffServiceID;
	private String apartmentNumber;
	private String memberName;
	private String phone;
	private String serviceName;
	private String buildingName;
	private boolean status;
	public StaffServiceByID() {
	}

	public StaffServiceByID(int staffServiceID, String apartmentNumber, String memberName, String phone,
			String serviceName, String buildingName, boolean status) {
		super();
		this.staffServiceID = staffServiceID;
		this.apartmentNumber = apartmentNumber;
		this.memberName = memberName;
		this.phone = phone;
		this.serviceName = serviceName;
		this.buildingName = buildingName;
		this.status = status;
	}

	public int getStaffServiceID() {
		return staffServiceID;
	}

	public void setStaffServiceID(int staffServiceID) {
		this.staffServiceID = staffServiceID;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
