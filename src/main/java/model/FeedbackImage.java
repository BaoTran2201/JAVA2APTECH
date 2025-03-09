package model;

public class FeedbackImage {
	private int imageID;
	private int feedbackID;
	private String imagePath;

	public FeedbackImage() {
	}

	public FeedbackImage(int imageID, int feedbackID, String imagePath) {
		this.imageID = imageID;
		this.feedbackID = feedbackID;
		this.imagePath = imagePath;
	}


	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "FeedbackImage{" + "imageID=" + imageID + ", feedbackID=" + feedbackID + ", imagePath='" + imagePath
				+ '\'' + '}';
	}
}