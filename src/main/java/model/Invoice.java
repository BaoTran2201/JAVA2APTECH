package model;

import java.sql.Timestamp;

public class Invoice {
	private int invoiceID;
	private int memberID;
	private double totalAmount;
	private Timestamp invoiceDate;
	private boolean paymentStatus;
	public Invoice() {
	}

	public Invoice(int memberID, double totalAmount) {
		this.memberID = memberID;
		this.totalAmount = totalAmount;
	}

	public Invoice(int invoiceID, int memberID, double totalAmount) {
		this.invoiceID = invoiceID;
		this.memberID = memberID;
		this.totalAmount = totalAmount;
	}

	public Invoice(int invoiceID, int memberID, Timestamp invoiceDate, double totalAmount, boolean paymentStatus) {
		this.invoiceID = invoiceID;
		this.memberID = memberID;
		this.invoiceDate = invoiceDate;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}

	public Timestamp getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
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

}
