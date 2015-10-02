package sso.server.utils;

public class AdminSQL {

	// 系统信息列表
	public final static String SEARCH_SYS = " SELECT r.sid AS sid,r.sys_name AS SysName,r.sys_link AS Sys_link, "
			+ " c.username AS username,c.id AS userid FROM sys_register AS r ,client AS c WHERE r.uid = c.id "
			+ " and r.sid > ? limit 10 ";

	// 删除系统
	public final static String DEL_SYS = " delete from sys_register where sid = ? ";

	// 系统负责人信息列表
	public static final String SEARCH_CLIENT = " SELECT c.id AS uid, c.username,c.email,c.phone FROM client AS c LIMIT ?,20 ";

	// 删除系统负责人
	public static final String DEL_CLIENT = " delete from client where id = ? ";

	// 普通用户列表
	public static final String SEARCH_USER = " SELECT u.userid,u.username,u.token FROM `user` AS u LIMIT ?,20 ";

	// 删除普通用户
	public static final String DEL_USER = " delete from user where userid = ? ";

	// 管理员列表
	public static final String SEARCH_MANAGER = " SELECT m.mid,m.realname as realName,m.phone,m.role FROM manager AS m LIMIT ?,20 ";

	// 删除管理员
	public static final String DEL_MANAGER = " delete from manager where mid = ? ";

	//查找信任关系
	public static final String SEARCH_CONNECTION = " SELECT DISTINCT s.link_id,s.sid1,s.sid2 "
				+ " ,r1.sys_link as link1,r1.sys_name as name1, "
				+ " r2.sys_link as link2,r2.sys_name as name2,s.flag "
				+ " from sys_connected s "
				+ " LEFT JOIN sys_register r1 on r1.sid = s.sid1 "
				+ " LEFT JOIN sys_register r2 on r2.sid = s.sid2 "
				+ " LEFT JOIN client c1 on c1.id = r1.uid "
				+ " LEFT JOIN client c2 on c2.id = r2.uid "
				+ " limit ?,10 ";

	public static final String DEL_CONNECTION = " DELETE FROM sys_connected WHERE sys_connected.link_id = ? ";
}
