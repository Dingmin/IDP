package sso.server.utils;

public class Register_Login_SQL {

	public static final String  ClientRegister = " insert into client values (null,?,?,?,?) ";
	public static final String  AdminRegister = " insert into client values (null,?,?,?,?) ";
	public static final String ClientLogin = " select id as uid,username,email,phone from client where email = ? and password = ? ";
	public static final String AdminLogin = " select mid,realname,phone,role from manager where phone = ? and password = ? ";
	public static final String UserRegister = " insert into user values (null,?,?,?) ";
	public static final String UserLogin = " select * from user where token = ? and pwd = ? ";
}
