package model;

public class Apartment {
	private int apartmentID;
	private int buildingID;
	private int floorID;
	private String apartmentNumber;
	private String apartmentType;
	private double area;
	private int apartmentsStatus;

	// Constructor có ID (khi lấy từ DB)
	public Apartment(int apartmentID, int buildingID, int floorID, String apartmentNumber, String apartmentType,
			double area, int apartmentsStatus) {
		this.apartmentID = apartmentID;
		this.buildingID = buildingID;
		this.floorID = floorID;
		this.apartmentNumber = apartmentNumber;
		this.apartmentType = apartmentType;
		this.area = area;
		this.apartmentsStatus = apartmentsStatus;
	}

	// Constructor không có ID (khi thêm mới)
	public Apartment(int buildingID, int floorID, String apartmentNumber, String apartmentType, double area,
			int apartmentsStatus) {
		this.buildingID = buildingID;
		this.floorID = floorID;
		this.apartmentNumber = apartmentNumber;
		this.apartmentType = apartmentType;
		this.area = area;
		this.apartmentsStatus = apartmentsStatus;
	}

	// Getters và Setters
	public int getApartmentID() {
		return apartmentID;
	}

	public int getBuildingID() {
		return buildingID;
	}

	public int getFloorID() {
		return floorID;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public String getApartmentType() {
		return apartmentType;
	}

	public double getArea() {
		return area;
	}

	public int getApartmentsStatus() {
		return apartmentsStatus;
	}

	public void setApartmentsStatus(int apartmentsStatus) {
		this.apartmentsStatus = apartmentsStatus;
	}
}
