package sso.server.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import sso.server.dao.Manager;
import sso.server.dao.SsoClient;
import sso.server.dao.SysRegister;
import sso.server.dao.User;
import sso.server.interfaces.Login_out;
import sso.server.utils.Check_If_Service_SQL;
import sso.server.utils.EncodeUrl;
import sso.server.utils.Register_Login_SQL;

@Component
public class UserImply implements Login_out {

	@Autowired
	DB_Action action;

	@Override
	public boolean ClientRegister(SsoClient client) {
		// TODO Auto-generated method stub
		boolean result = action.update(Register_Login_SQL.ClientRegister,
				new Object[] { client.getUsername(), EncodeUrl.encrypt(client.getPassword()),
						client.getEmail(), client.getPhone() });
		return result;
	}

	@Override
	public boolean ManagerRegister(Manager manager) {
		// TODO Auto-generated method stub
		boolean result = action.update(Register_Login_SQL.AdminRegister,
				new Object[] { manager.getRealName(), EncodeUrl.encrypt(manager.getPassword()),
						manager.getPhone(), manager.getRole() });
		return result;
	}

	@Override
	public SsoClient ClientLogin(String email, String password) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);
		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Register_Login_SQL.ClientLogin,
				new Object[] { email, EncodeUrl.encrypt(password) }, mapper);
		System.out.println("查询到的登录结果：" + list);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Manager ManagerLogin(String phone, String password) {
		// TODO Auto-generated method stub
		RowMapper<Manager> mapper = new BeanPropertyRowMapper<Manager>(
				Manager.class);
		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>) action.searchList(
				Register_Login_SQL.AdminLogin,
				new Object[] { phone, EncodeUrl.encrypt(password) }, mapper);
		System.out.println("manager login result:" + list);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public boolean userRegister(User u) {
		// TODO Auto-generated method stub
		boolean result = action.update(Register_Login_SQL.UserRegister,
				new Object[] { u.getUsername(), EncodeUrl.encrypt(u.getPwd()), u.getToken() });
		return result;
	}

	@Override
	public User userLogin(String token, String password) {
		// TODO Auto-generated method stub
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) action.searchList(
				Register_Login_SQL.UserLogin, new Object[] { token, EncodeUrl.encrypt(password) },
				mapper);
		System.out.println("user login result:" + list);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/*
	 * 确定访问的系统是否使用单点登录服务
	 * ：若使用服务，则返回该系统id,否则，返回null
	 */
	@SuppressWarnings("unchecked")
	public Integer match(String[] urls) {
		// TODO Auto-generated method stub
		
		RowMapper<SysRegister> mapper = new BeanPropertyRowMapper<SysRegister>(
				SysRegister.class);

		List<SysRegister> list = (List<SysRegister>) action.searchList(
				Check_If_Service_SQL.CHECK_URL,
				new Object[] {urls[1] }, mapper);
		System.out.println("check the service if use sso:" + list);
		if (list != null && list.size() >0) {
				return list.get(0).getSid();
//			int sid1 = list.get(0).getSid();
//			int sid2 = list.get(1).getSid();
//			List<SysRegister> list2 = (List<SysRegister>) action.searchList(
//					Check_If_Service_SQL.CHECK_IF_RELATIVE, new Object[] {
//							sid1, sid2, sid2, sid1 }, mapper);
//			if (list2 != null && list2.size() > 0)
//				return sid1 + "*" + sid2;
//			else
//				return null;
		}

		return null;
	}

	@Override
	public boolean CheckEmail(String email) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);

		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Check_If_Service_SQL.CHECK_EMAIL, new Object[] { email },
				mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;

	}

	@Override
	public boolean checkPhone(String phone) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);

		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Check_If_Service_SQL.CHECK_PHONE, new Object[] { phone },
				mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	public boolean checkToken(String token) {
		// TODO Auto-generated method stub
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(
				User.class);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) action.searchList(
				Check_If_Service_SQL.CHECK_TOKEN, new Object[] { token },
				mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	
}
