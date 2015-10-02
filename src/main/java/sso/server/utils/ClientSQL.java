package sso.server.utils;

public class ClientSQL {

	// 增加系统
	public static final String ADD_SYS = " insert into sys_register values ( null,?,?,?,?,? ) ";

	// 增加信任关系
	public static final String ADD_CONNECT = " insert into sys_connected values ( null,?,?,? ) ";

	// 删除系统
	public static final String DEL_SYS = " DELETE FROM sys_register where  sid= ? and uid = ?  ";

	// 删除信任关系
	public static final String DEL_CONN = " DELETE FROM sys_connected WHERE link_id = ? ";

	// 查找系统列表
	public static final String SEARCH_SYS = " select s.sid,s.sys_link,s.sys_name,s.date,s.sso "
			+ " from sys_register s where  s.uid = ? LIMIT ?,20 ";

	// 查找信任请求
	public static final String SEARCH_CONN_REQ = " select s.link_id,s.sid1,s.sid2,r1.sys_name as sys1,r1.sys_link as link1, "
			+ "r2.sys_name as sys2,r2.sys_link as link2,c1.username,c1.email "
			+ " from sys_connected s "
			+ " LEFT JOIN sys_register r1 on r1.sid = s.sid1 "
			+ " LEFT JOIN sys_register r2 on r2.sid = s.sid2 "
			+ " LEFT JOIN client c1 on c1.id=r1.uid "
			+ " where s.flag = 0 and r2.uid = ? LIMIT ?,10 ";

	// 查找信任关系
	public static final String SEARCH_CONNECTION = " SELECT DISTINCT s.link_id,s.sid1,s.sid2 "
			+ " ,r1.sys_link as link1,r1.sys_name as name1, "
			+ " r2.sys_link as link2,r2.sys_name as name2,s.flag "
			+ " from sys_connected s "
			+ " LEFT JOIN sys_register r1 on r1.sid = s.sid1 "
			+ " LEFT JOIN sys_register r2 on r2.sid = s.sid2 "
			+ " LEFT JOIN client c1 on c1.id = r1.uid "
			+ " LEFT JOIN client c2 on c2.id = r2.uid "
			+ " where c1.id =? or c2.id=? limit ?,10 ";

	// 接受信任请求
	public static final String REV_REQUEST = " UPDATE sys_connected set flag = 1 where link_id = ? ";

	// 拒绝信任请求
	public static final String REJECT_REQUEST = " UPDATE sys_connected set flag = -1 where link_id = ? ";

	// 添加信任关系时，显示用户已注册的系统
	public static final String SEARCH_USER_SYS = " SELECT s.sid,s.sys_link as Sys_link,s.sys_name as SysName, "
			+ " s.uid as userid,c.username FROM sys_register s "
			+ " LEFT JOIN client c "
			+ " on c.id = s.uid "
			+ " where c.id = ?  ";

	public static final String SEARCH_BELIEVE_SYS = " SELECT s.sid,s.sys_link as Sys_link,s.sys_name as SysName, "
			+ " s.uid as userid,c.username FROM sys_register s "
			+ " LEFT JOIN client c "
			+ " on c.id = s.uid "
			+ " where c.username like ? "
			+ " or s.sys_name like ? "
			+ " or s.sys_link like ? ";
}
