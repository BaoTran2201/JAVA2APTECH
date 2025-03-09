package model;

import java.util.Date;

public class Payment {
	private int paymentID;
	private int memberID;
	private double totalAmount;
	private Date paymentDate;
	private boolean paymentStatus;

	public Payment() {

	}

	public Payment(int paymentID, int memberID, double totalAmount, Date paymentDate, boolean paymentStatus) {
		super();
		this.paymentID = paymentID;
		this.memberID = memberID;
		this.totalAmount = totalAmount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
