package model;

public class StaffLogin {
	private int staffID;
	private String username;
	private String password;
	private boolean loginStatus;

	public StaffLogin(int staffID, String username, String password, boolean loginStatus) {
		this.staffID = staffID;
		this.username = username;
		this.password = password;
		this.loginStatus = loginStatus;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
}
