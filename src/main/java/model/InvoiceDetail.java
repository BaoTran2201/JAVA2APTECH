package model;

public class InvoiceDetail {
	private int serviceID;
	private String serviceName;
	private double price;
	private int quantity;

	public InvoiceDetail() {

	}

	public InvoiceDetail(int serviceID, String serviceName, double price, int quantity) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.price = price;
		this.quantity = quantity;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
