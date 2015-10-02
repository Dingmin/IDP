package sso.server.dao;

/*
 * The link information between two systems who believe each other 
 * The combine of sid_1 and sid_2 is unique
 */
public class SysConnected {

	private Integer link_id;// primary auto_increment key
	private Integer sid_1;// the first system
	private Integer sid_2;// the second system
	private Integer flag;// user to set if two systems use the sso sevice {'1': use , '0': not use }

	public Integer getLink_id() {
		return link_id;
	}

	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}

	public Integer getSid_1() {
		return sid_1;
	}

	public void setSid_1(Integer sid_1) {
		this.sid_1 = sid_1;
	}

	public Integer getSid_2() {
		return sid_2;
	}

	public void setSid_2(Integer sid_2) {
		this.sid_2 = sid_2;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SysConnected [link_id=" + link_id + ", sid_1=" + sid_1
				+ ", sid_2=" + sid_2 + ", flag=" + flag + "]";
	}

	public SysConnected() {

	}

}
