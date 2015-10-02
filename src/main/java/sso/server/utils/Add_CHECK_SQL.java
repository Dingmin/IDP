package sso.server.utils;

public class Add_CHECK_SQL {

	// 检查用户凭证是否有效
	public static final String CHECK_USER_TOKEN = "SELECT u.userid FROM `user` AS u WHERE u.token = ? ";
	public static final String ADD_USER = " insert into user values ( null , ? , ? , ? ) ";
	public static final String CHECK_ADMIN_PHONE = " select m.mid from manager m where m.phone = ? ";
	public static final String ADD_ADMIN = " insert into manager values (null,?,?,?,?) ";
	public static final String CHECK_CLIENT_PHONE = " select id from client where phone = ? ";
	public static final String ADD_CLIENT = " insert into client values (null,?,?,?,?) ";
	public static final String CHECK_CLIENT_EMAIL = " select id from client where email = ? ";
	public static final String SEARCH_CLIENT = " SELECT DISTINCT c.id as uid, c.username,c.email,c.phone FROM client AS c "
			+ " where c.username like ? or c.email like ? or c.phone like ? ";
	public static final String ADD_SYS = " insert into sys_register values (null,?,?,?,?,?) ";
	public static final String SEARCH_SYS = "SELECT s.sid,s.sys_link as Sys_link,s.sys_name as SysName, "
			+ " s.uid as userid,c.username FROM sys_register s "
			+ " LEFT JOIN client c "
			+ " on c.id = s.uid "
			+ " where c.username like ? "
			+ " or s.sys_name like ? "
			+ " or s.sys_link like ? ";
	public static final String ADD_SYS_CONNECTION =  " insert into sys_connected values ( null,?,?,? ) ";
}
