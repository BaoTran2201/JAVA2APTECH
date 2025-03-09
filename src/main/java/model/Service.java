package model;

public class Service {
	private int serviceID;
	private String serviceName;
	private String description;
	private double price;
	private int durationDays;
	private boolean serviceStatus;

	public Service() {
	}

	public Service(int serviceID, String serviceName, String description, double price, int durationDays,
			boolean serviceStatus) {
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.description = description;
		this.price = price;
		this.durationDays = durationDays;
		this.serviceStatus = serviceStatus;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	public boolean isServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}


}
