package model;

public class Staff {

	private int staffID;
	private String staffName;
	private String phone;
	private boolean staffStatus; // true = hoạt động, false = đã xóa

	public Staff() {
	}

	public Staff(int staffID, String staffName, String phone, boolean staffStatus) {
		this.staffID = staffID;
		this.staffName = staffName;
		this.phone = phone;
		this.staffStatus = staffStatus;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(boolean staffStatus) {
		this.staffStatus = staffStatus;
	}

	@Override
	public String toString() {
		return "Staff{" + "staffID=" + staffID + ", staffName='" + staffName + '\'' + ", phone='" + phone + '\''
				+ ", staffStatus=" + staffStatus + '}';
	}
}