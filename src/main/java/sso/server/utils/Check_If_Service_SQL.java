package sso.server.utils;

public class Check_If_Service_SQL {

	//查看访问的系统是否使用单点服务
	public final static String CHECK_URL = " select DISTINCT sid,sso from sys_register where instr( ? ,sys_link) > 0 ";
		//	+ " and sso = 1 ";

	//检查两个系统是否相互信任
	public final static String CHECK_IF_RELATIVE = " select link_id as sid from sys_connected where flag =1 "
			+ " and ( sid1 = ? and sid2 = ? ) or ( sid1 2 and sid2 = ? ) ";

	public final static String CHECK_EMAIL = " select id from client where email = ? ";

	public static final String CHECK_PHONE = " select id from client where phone = ? ";

	public static final String CHECK_TOKEN = " select userid from user where token = ? ";
}
