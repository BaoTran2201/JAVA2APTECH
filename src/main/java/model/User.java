package model;

import java.sql.Date;

public class User {
	private int memberID;
	private String memberName;
	private String identityImage; // 🆔 Ảnh giấy tờ tùy thân
	private String country;
	private Date dob;
	private Date startDate;
	private Date endDate;
	private int quantity;
	private String phone;
	private String email;
	private int verifyCode;
	private boolean gender;
	private int apartmentID;
	private boolean memberStatus;
	private String apartmentNumber;

	// 🔹 Constructor đầy đủ (Dùng khi truy vấn từ DB)
	public User(int memberID, String memberName, String identityImage, String country, Date dob, Date startDate,
			Date endDate, int quantity, String phone, String email, int verifyCode, boolean gender, int apartmentID,
			boolean memberStatus, String apartmentNumber) {
		this.memberID = memberID;
		this.memberName = memberName;
		this.identityImage = identityImage;
		this.country = country;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.phone = phone;
		this.email = email;
		this.verifyCode = verifyCode;
		this.gender = gender;
		this.apartmentID = apartmentID;
		this.memberStatus = memberStatus;
		this.apartmentNumber = apartmentNumber;
	}

	// 🔹 Constructor rút gọn (Dùng khi tạo user mới)
	public User(String memberName, String identityImage, String country, Date dob, Date startDate, int quantity,
			String phone, String email, boolean gender, int apartmentID) {
		this.memberName = memberName;
		this.identityImage = identityImage;
		this.country = country;
		this.dob = dob;
		this.startDate = startDate;
		this.quantity = quantity;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.apartmentID = apartmentID;
		this.memberStatus = true; // Mặc định là đang hoạt động
	}

	// 🔹 Getters và Setters
	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getIdentityImage() {
		return identityImage;
	}

	public void setIdentityImage(String identityImage) {
		this.identityImage = identityImage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getApartmentID() {
		return apartmentID;
	}

	public void setApartmentID(int apartmentID) {
		this.apartmentID = apartmentID;
	}

	public boolean isMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(boolean memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
}
