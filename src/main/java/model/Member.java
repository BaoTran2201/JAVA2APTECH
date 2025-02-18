package model;

import java.sql.Date;

public class Member {
	private int memberID;
	private String memberName;
	private String avatar;
	private String country;
	private Date dob;
	private Date startDate;
	private Date endDate;
	private int quantity;
	private String phone;
	private String email;
	private int verifyCode;
	private boolean gender; // true = Nam, false = Nữ
	private Integer apartmentID; // Có thể NULL
	private boolean memberStatus; // Trạng thái thành viên

	// Constructor đầy đủ
	public Member(int memberID, String memberName, String avatar, String country, Date dob, Date startDate,
			Date endDate, int quantity, String phone, String email, int verifyCode, boolean gender, Integer apartmentID,
			boolean memberStatus) {
		this.memberID = memberID;
		this.memberName = memberName;
		this.avatar = avatar;
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
	}

	// Constructor rút gọn (không cần tất cả thuộc tính)
	public Member(int memberID, String memberName, String avatar, String country, Date dob, Date startDate,
			Date endDate, int quantity, String email, boolean gender) {
		this.memberID = memberID;
		this.memberName = memberName;
		this.avatar = avatar;
		this.country = country;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.email = email;
		this.gender = gender;
	}

	// Getters & Setters
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Integer getApartmentID() {
		return apartmentID;
	}

	public void setApartmentID(Integer apartmentID) {
		this.apartmentID = apartmentID;
	}

	public boolean isMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(boolean memberStatus) {
		this.memberStatus = memberStatus;
	}

	// Phương thức toString() để hiển thị thông tin dễ dàng
	@Override
	public String toString() {
		return "Member{" + "memberID=" + memberID + ", memberName='" + memberName + '\'' + ", avatar='" + avatar + '\''
				+ ", country='" + country + '\'' + ", dob=" + dob + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", quantity=" + quantity + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", verifyCode="
				+ verifyCode + ", gender=" + (gender ? "Nam" : "Nữ") + ", apartmentID=" + apartmentID
				+ ", memberStatus=" + (memberStatus ? "Hoạt động" : "Không hoạt động") + '}';
	}
}
