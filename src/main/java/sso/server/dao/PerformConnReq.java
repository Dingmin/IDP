package sso.server.dao;

public class PerformConnReq {

	private Integer link_id;
	private Integer sid1;
	private Integer sid2;
	private String sys1;
	private String  link1;
	private String sys2;
	private String link2;
	private String username;
	private String email;
	public Integer getLink_id() {
		return link_id;
	}
	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}
	public Integer getSid1() {
		return sid1;
	}
	public void setSid1(Integer sid1) {
		this.sid1 = sid1;
	}
	public Integer getSid2() {
		return sid2;
	}
	public void setSid2(Integer sid2) {
		this.sid2 = sid2;
	}
	public String getSys1() {
		return sys1;
	}
	public void setSys1(String sys1) {
		this.sys1 = sys1;
	}
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public String getSys2() {
		return sys2;
	}
	public void setSys2(String sys2) {
		this.sys2 = sys2;
	}
	public String getLink2() {
		return link2;
	}
	public void setLink2(String link2) {
		this.link2 = link2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PerformConnReq() {
		// TODO Auto-generated constructor stub
	}
	
}
