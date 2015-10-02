package sso.server.dao;

/*
 * The information of managers who manage the SSO system
 * phone is unique
 * role is to design the work and power of this manager
 */
public class Manager {

	private Integer mid;// primary auto_increment key
	private String realName;
	private String password;
	private String phone;//unique
	private Integer role;// use to set the role of this manager{'0':super manager, '1':common manager}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Manager [mid=" + mid + ", realName=" + realName + ", password="
				+ password + ", phone=" + phone + ", role=" + role + "]";
	}

	public Manager() {
		// TODO Auto-generated constructor stub
	}

}
