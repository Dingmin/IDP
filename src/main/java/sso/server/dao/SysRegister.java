package sso.server.dao;

import java.util.Date;

/*
 * The information of users' system.
 * the combine of uid and s_link is unique
 */
public class SysRegister {

	private Integer sid;// primary auto_increment key
	private Integer uid;// the id of the system's user
	private String sys_name;//the name of the system
	private String sys_link;// the link of the system
	private Integer sso;//the system if use sso service{0:not use  1:use}
	private Date date;

	public Integer getSso() {
		return sso;
	}

	public void setSso(Integer sso) {
		this.sso = sso;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getDate() {
		return date;
	}

	public String getSys_name() {
		return sys_name;
	}

	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	public String getSys_link() {
		return sys_link;
	}

	public void setSys_link(String sys_link) {
		this.sys_link = sys_link;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SysRegister() {

	}
}
