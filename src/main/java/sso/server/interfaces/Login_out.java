package sso.server.interfaces;

import sso.server.dao.Manager;
import sso.server.dao.SsoClient;
import sso.server.dao.User;

public interface Login_out {

	//boolean Login(String username,String password,String targetUrl);
	boolean ClientRegister(SsoClient client);
	boolean ManagerRegister(Manager manager);
	SsoClient ClientLogin(String email,String password);
	Manager ManagerLogin(String phone,String password);
	boolean userRegister(User u);
	User userLogin(String token,String password);
	boolean CheckEmail(String email);
	boolean checkPhone(String phone);
}
