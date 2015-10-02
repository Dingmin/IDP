package sso.server.dao;

/*
 * The information of users who use this system service
 * email is unique
 * phone is unique
 */
public class SsoClient {

	private Integer uid; // primary auto_increment key
	private String username;
	private String password;
	private String email;// unique
	private String phone;// unique

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "sys_capital [uid=" + uid + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", phone="
				+ phone + "]";
	}

	public SsoClient() {

	}
}
