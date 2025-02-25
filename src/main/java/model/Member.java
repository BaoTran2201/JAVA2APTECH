package model;

import java.time.LocalDate;

public class Member {
	private int memberID;
	private String memberName;
	private String avatar;
	private String country;
	private LocalDate dob;
	private LocalDate startDate;
	private LocalDate endDate;
	private int quantity;
	private String phone;
	private String cccd;
	private int verifyCode;
	private boolean gender;
	private Integer apartmentID;
	private boolean memberStatus;
	private String identityImage;

	public Member() {
	}

	public Member(int memberID, String memberName, String avatar, String country, LocalDate dob, LocalDate startDate,
			LocalDate endDate, int quantity, String phone, String cccd, int verifyCode, boolean gender,
			Integer apartmentID, boolean memberStatus, String identityImage) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
		this.avatar = avatar;
		this.country = country;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.phone = phone;
		this.cccd = cccd;
		this.verifyCode = verifyCode;
		this.gender = gender;
		this.apartmentID = apartmentID;
		this.memberStatus = memberStatus;
		this.identityImage = identityImage;
	}

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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
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

	public String getIdentityImage() {
		return identityImage;
	}

	public void setIdentityImage(String identityImage) {
		this.identityImage = identityImage;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", memberName=" + memberName + ", avatar=" + avatar + ", country="
				+ country + ", dob=" + dob + ", startDate=" + startDate + ", endDate=" + endDate + ", quantity="
				+ quantity + ", phone=" + phone + ", cccd=" + cccd + ", verifyCode=" + verifyCode + ", gender=" + gender
				+ ", apartmentID=" + apartmentID + ", memberStatus=" + memberStatus + ", identityImage=" + identityImage
				+ "]";
	}

}

