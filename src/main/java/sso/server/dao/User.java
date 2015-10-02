package sso.server.dao;

/*
 * User is for all systems who use the tSSO product
 * token is unique
 */
public class User {
	private Integer userid;
	private String username;
	private String pwd;
	private String token;//unique; as the ticket to identity user,could be as email,phone number etc.

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", pwd="
				+ pwd + ", token=" + token + "]";
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
}
