package model;

import java.util.Date;

public class Notification {
	private int id;
	private String title;
	private String content;
	private Date sentDate;

	public Notification(int id, String title, String content, Date sentDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.sentDate = sentDate;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getSentDate() {
		return sentDate;
	}

	@Override
	public String toString() {
		return title + " (" + sentDate + "): " + content;
	}
}
