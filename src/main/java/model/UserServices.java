package model;

import java.util.Date;

public class UserServices {
	private int userServiceID;
	private int memberID;
	private int serviceID;
	private String serviceName;
	private Date dayStart;
	private Date dayEnd;
	private boolean statusSer;

	public UserServices() {
	}

	public UserServices(int userServiceID, int memberID, int serviceID, String serviceName, Date dayStart, Date dayEnd,
			boolean statusSer) {
		this.userServiceID = userServiceID;
		this.memberID = memberID;
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.statusSer = statusSer;
	}

	public int getUserServiceID() {
		return userServiceID;
	}

	public void setUserServiceID(int userServiceID) {
		this.userServiceID = userServiceID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getDayStart() {
		return dayStart;
	}

	public void setDayStart(Date dayStart) {
		this.dayStart = dayStart;
	}

	public Date getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(Date dayEnd) {
		this.dayEnd = dayEnd;
	}

	public boolean isStatusSer() {
		return statusSer;
	}

	public void setStatusSer(boolean statusSer) {
		this.statusSer = statusSer;
	}


	@Override
	public String toString() {
		return "UserServices [userServiceID=" + userServiceID + ", memberID=" + memberID + ", serviceID=" + serviceID
				+ ", serviceName=" + serviceName + ", dayStart=" + dayStart + ", dayEnd=" + dayEnd + ", statusSer="
				+ statusSer + "]";
	}


}
