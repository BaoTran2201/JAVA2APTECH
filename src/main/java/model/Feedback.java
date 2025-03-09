package model;

import java.sql.Timestamp;

public class Feedback {
	private int feedbackID;
	private int memberID; // Sử dụng Integer cho phép giá trị NULL
	private String namefb;
	private String feedbackTittle;
	private String note;
	private Timestamp feedbackDate;
	private boolean statusfb;

	public Feedback() {
	}

	public Feedback(int feedbackID, int memberID, String namefb, String feedbackTittle, String note,
			Timestamp feedbackDate, boolean statusfb) {
		this.feedbackID = feedbackID;
		this.memberID = memberID;
		this.namefb = namefb;
		this.feedbackTittle = feedbackTittle;
		this.note = note;
		this.feedbackDate = feedbackDate;
		this.statusfb = statusfb;
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getNamefb() {
		return namefb;
	}

	public void setNamefb(String namefb) {
		this.namefb = namefb;
	}

	public String getFeedbackTittle() {
		return feedbackTittle;
	}

	public void setFeedbackTittle(String feedbackTittle) {
		this.feedbackTittle = feedbackTittle;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Timestamp feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public boolean isStatusfb() {
		return statusfb;
	}

	public void setStatusfb(boolean statusfb) {
		this.statusfb = statusfb;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackID=" + feedbackID + ", memberID=" + memberID + ", namefb=" + namefb
				+ ", feedbackTittle=" + feedbackTittle + ", note=" + note + ", feedbackDate=" + feedbackDate
				+ ", statusfb=" + statusfb + "]";
	}


}
