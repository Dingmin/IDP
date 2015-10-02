package sso.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import sso.server.dao.Manager;
import sso.server.dao.PerformSys;
import sso.server.dao.SsoClient;
import sso.server.dao.SysConnected;
import sso.server.dao.SysRegister;
import sso.server.dao.User;
import sso.server.imply.DB_Action;
import sso.server.utils.Add_CHECK_SQL;

@Service
public class CommonService {

	@Autowired
	DB_Action action;

	public boolean checkUserToken(String name) {
		// TODO Auto-generated method stub
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) action.searchList(
				Add_CHECK_SQL.CHECK_USER_TOKEN, new Object[] { name }, mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		boolean flag = action.update(Add_CHECK_SQL.ADD_USER,
				new Object[] { u.getUsername(), u.getPwd(), u.getToken() });
		return flag;
	}

	public boolean checkAdminPhone(String phone) {
		// TODO Auto-generated method stub
		RowMapper<Manager> mapper = new BeanPropertyRowMapper<Manager>(
				Manager.class);
		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>) action
				.searchList(Add_CHECK_SQL.CHECK_ADMIN_PHONE,
						new Object[] { phone }, mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	public boolean addAdmin(Manager m) {
		// TODO Auto-generated method stub
		boolean flag = action.update(Add_CHECK_SQL.ADD_ADMIN,
				new Object[] { m.getRealName(), m.getPassword(), m.getPhone(),
						m.getRole() });
		return flag;
	}

	public boolean checkClientPhone(String phone) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);
		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Add_CHECK_SQL.CHECK_CLIENT_PHONE, new Object[] { phone },
				mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	public boolean addClient(SsoClient m) {
		// TODO Auto-generated method stub
		boolean flag = action.update(Add_CHECK_SQL.ADD_CLIENT,
				new Object[] { m.getUsername(), m.getPassword(), m.getEmail(),
						m.getPhone() });
		return flag;
	}

	public boolean checkClientEmail(String email) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);
		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Add_CHECK_SQL.CHECK_CLIENT_EMAIL, new Object[] { email },
				mapper);
		if (list != null && list.size() > 0)
			return false;
		return true;
	}

	public List<SsoClient> findClient(String name) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(
				SsoClient.class);
		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(
				Add_CHECK_SQL.SEARCH_CLIENT, new Object[] { '%' + name + '%',
						'%' + name + '%', '%' + name + '%' }, mapper);
		return list;
	}

	public boolean addSys(SysRegister s) {
		// TODO Auto-generated method stub
		boolean flag = action.update(
				Add_CHECK_SQL.ADD_SYS,
				new Object[] { s.getUid(), s.getSys_name(), s.getSys_link(),
						s.getDate(), s.getSso() });
		return flag;
	}

	public List<PerformSys> findSys(String name) {
		// TODO Auto-generated method stub
		RowMapper<PerformSys> mapper = new BeanPropertyRowMapper<PerformSys>(
				PerformSys.class);
		@SuppressWarnings("unchecked")
		List<PerformSys> list = (List<PerformSys>) action.searchList(
				Add_CHECK_SQL.SEARCH_SYS, new Object[] { '%' + name + '%',
						'%' + name + '%', '%' + name + '%' }, mapper);
		return list;
	}

	public boolean addSysConne(SysConnected s) {
		// TODO Auto-generated method stub
		boolean flag = action.update(Add_CHECK_SQL.ADD_SYS_CONNECTION,
				new Object[] { s.getSid_1(), s.getSid_2(), s.getFlag() });
		return flag;
	}

}
