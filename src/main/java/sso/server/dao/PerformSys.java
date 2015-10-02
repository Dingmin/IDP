package sso.server.dao;

public class PerformSys {

	private Integer sid;
	private String SysName;
	private String Sys_link;
	private String username;
	private Integer userid;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSysName() {
		return SysName;
	}
	public void setSysName(String sysName) {
		SysName = sysName;
	}
	public String getSys_link() {
		return Sys_link;
	}
	public void setSys_link(String sys_link) {
		Sys_link = sys_link;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "PerformSys [sid=" + sid + ", SysName=" + SysName
				+ ", Sys_link=" + Sys_link + ", username=" + username
				+ ", userid=" + userid + "]";
	}
	public PerformSys() {
		// TODO Auto-generated constructor stub
	}
}
