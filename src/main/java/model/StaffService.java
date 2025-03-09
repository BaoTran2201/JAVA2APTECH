package model;

import java.util.Date;

public class StaffService {
	private int staffServiceID;
	private int staffID;
	private int userServiceID;
	private Date assignmentDate;
	private boolean statusDone;

	public StaffService() {

	}

	public StaffService(int staffServiceID, int staffID, int userServiceID, Date assignmentDate, boolean statusDone) {
		this.staffServiceID = staffServiceID;
		this.staffID = staffID;
		this.userServiceID = userServiceID;
		this.assignmentDate = assignmentDate;
		this.statusDone = statusDone;
	}

	public int getStaffServiceID() {
		return staffServiceID;
	}

	public void setStaffServiceID(int staffServiceID) {
		this.staffServiceID = staffServiceID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public int getUserServiceID() {
		return userServiceID;
	}

	public void setUserServiceID(int userServiceID) {
		this.userServiceID = userServiceID;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public boolean isStatusDone() {
		return statusDone;
	}

	public void setStatusDone(boolean statusDone) {
		this.statusDone = statusDone;
	}

	@Override
	public String toString() {
		return "StaffService [staffServiceID=" + staffServiceID + ", staffID=" + staffID + ", userServiceID="
				+ userServiceID + ", assignmentDate=" + assignmentDate + ", statusDone=" + statusDone + "]";
	}

}
