package sso.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import sso.server.dao.Manager;
import sso.server.dao.PerformSys;
import sso.server.dao.PerformSysConn;
import sso.server.dao.SsoClient;
import sso.server.dao.User;
import sso.server.imply.DB_Action;
import sso.server.utils.AdminSQL;

@Service
public class AdminService {

	@Autowired
	DB_Action action;
	
	public List<PerformSys> getSys(Integer index){
		RowMapper<PerformSys> mapper = new BeanPropertyRowMapper<PerformSys>(PerformSys.class);
		@SuppressWarnings("unchecked")
		List<PerformSys> list = (List<PerformSys>) action.searchList(AdminSQL.SEARCH_SYS, new Object[]{index}, mapper);
		return list;
		
	}
	
	public boolean DelSys(Integer sid){
		boolean flag = action.update(AdminSQL.DEL_SYS, new Object[]{sid});
		return flag;
	}

	public List<SsoClient> findClient(Integer index) {
		// TODO Auto-generated method stub
		RowMapper<SsoClient> mapper = new BeanPropertyRowMapper<SsoClient>(SsoClient.class);
		@SuppressWarnings("unchecked")
		List<SsoClient> list = (List<SsoClient>) action.searchList(AdminSQL.SEARCH_CLIENT, new Object[]{index}, mapper);
		return list;
	}

	public boolean DelClient(Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(AdminSQL.DEL_CLIENT, new Object[]{index});
		return flag;
	}

	public List<User> findUser(Integer index) {
		// TODO Auto-generated method stub
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) action.searchList(AdminSQL.SEARCH_USER, new Object[]{index}, mapper);
		return list;
	}

	public boolean delUser(Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(AdminSQL.DEL_USER, new Object[]{index});
		return flag;
	}

	public List<Manager> findMan(Integer index) {
		// TODO Auto-generated method stub
		RowMapper<Manager> mapper = new BeanPropertyRowMapper<Manager>(Manager.class);
		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>) action.searchList(AdminSQL.SEARCH_MANAGER, new Object[]{index}, mapper);
		return list;
	}

	public boolean delMan(Integer userRole, Integer index, Integer role) {
		// TODO Auto-generated method stub
		if(userRole<=role) 
			return false;
		boolean flag = action.update(AdminSQL.DEL_MANAGER, new Object[]{index});
		return flag;
	}

	public List<PerformSysConn> findConn(Integer index) {
		// TODO Auto-generated method stub
		RowMapper<PerformSysConn> mapper = new BeanPropertyRowMapper<PerformSysConn>(PerformSysConn.class);
		@SuppressWarnings("unchecked")
		List<PerformSysConn> list = (List<PerformSysConn>) action.searchList(AdminSQL.SEARCH_CONNECTION, new Object[]{index}, mapper);
		return list;
	}

	public boolean DelConn(Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(AdminSQL.DEL_CONNECTION, new Object[]{index});
		return flag;
	}
}
