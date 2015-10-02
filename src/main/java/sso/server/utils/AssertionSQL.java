package sso.server.utils;

public class AssertionSQL {

	public static final String ADD_ASSERTION = " insert into assertion values (null,?,?,?,?) ";
	public static final String DEL_ASSERTION = " DELETE FROM assertion  where sys = ? and jsessionid = ? ";
	public static final String SEARCH_ASSERTION = " SELECT a.assertion FROM assertion AS a WHERE "
			+ " a.jsessionid = ? and a.sys = ? ";
	public static final String SEARCH_BeBelievedSys = " SELECT c.link_id, c.flag,c.sid2 as sid_2,c.sid1 as sid_1 FROM sys_connected AS c "
			+ " WHERE (c.flag=1 and c.sid1 = ? or c.sid2 = ? ) or (c.flag = ? and c.sid2 = ?) ";
	public static final String UPDATE_SYS = " UPDATE assertion set sys = concat(sys,? ) where sys = ? and jsessionid = ? ";
	public static final String UPDATE_SYS_LOGOUT = " UPDATE assertion set sys = ? where sys = ? and jsessionid = ? ";
}
