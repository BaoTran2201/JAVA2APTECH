package model;

public class Login {
	private int memberID;
	private String username;
	private String pass;
	private String email;
	private String jobRole;
	private boolean loginStatus;

	public Login() {

	}

	public Login(int memberID, String username, String pass, String email, String jobRole, boolean loginStatus) {
		this.memberID = memberID;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.jobRole = jobRole;
		this.loginStatus = loginStatus;
	}
	public Login(String username, String pass, String email, String jobRole, boolean loginStatus) {
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.jobRole = jobRole;
		this.loginStatus = loginStatus;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}




}
