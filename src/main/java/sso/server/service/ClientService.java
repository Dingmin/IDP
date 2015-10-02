package sso.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import sso.server.dao.PerformConnReq;
import sso.server.dao.PerformSys;
import sso.server.dao.PerformSysConn;
import sso.server.dao.SysConnected;
import sso.server.dao.SysRegister;
import sso.server.imply.DB_Action;
import sso.server.utils.ClientSQL;

@Service
public class ClientService {

	@Autowired
	DB_Action action;

	public List<SysRegister> findSys(Integer userid, Integer index) {
		// TODO Auto-generated method stub
		RowMapper<SysRegister> mapper = new BeanPropertyRowMapper<SysRegister>(
				SysRegister.class);
		@SuppressWarnings("unchecked")
		List<SysRegister> list = (List<SysRegister>) action.searchList(
				ClientSQL.SEARCH_SYS, new Object[] { userid, index }, mapper);
		return list;
	}

	public boolean delSys(Integer userid, Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(ClientSQL.DEL_SYS, new Object[] { index,
				userid });
		return flag;
	}

	public boolean delConn( Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(ClientSQL.DEL_CONN, new Object[] { index});
		return flag;
	}

	public boolean addSystem(SysRegister sys) {
		// TODO Auto-generated method stub
		boolean flag = action.update(
				ClientSQL.ADD_SYS,
				new Object[] { sys.getUid(), sys.getSys_name(),
						sys.getSys_link(), new Date(), sys.getSso() });
		return flag;
	}

	public boolean addConnection(SysConnected conn) {
		// TODO Auto-generated method stub
		boolean flag = action
				.update(ClientSQL.ADD_CONNECT, new Object[] { conn.getSid_1(),
						conn.getSid_2(), conn.getFlag() });
		return flag;
	}

	public List<PerformConnReq> findConneReq(Integer userid, Integer index) {
		// TODO Auto-generated method stub
		RowMapper<PerformConnReq> mapper = new BeanPropertyRowMapper<PerformConnReq>(
				PerformConnReq.class);
		@SuppressWarnings("unchecked")
		List<PerformConnReq> list = (List<PerformConnReq>) action.searchList(
				ClientSQL.SEARCH_CONN_REQ, new Object[] { userid, index },
				mapper);
		return list;
	}

	public boolean rev(Integer index) {
		// TODO Auto-generated method stub
		boolean flag = action.update(ClientSQL.REV_REQUEST,
				new Object[] { index });
		return flag;
	}

	public boolean reject(Integer index) {
		// TODO Auto-generated method stub

		boolean flag = action.update(ClientSQL.REJECT_REQUEST,
				new Object[] { index });
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<PerformSys> findUserSys(Integer userid, String name) {
		// TODO Auto-generated method stub
		RowMapper<PerformSys> mapper = new BeanPropertyRowMapper<PerformSys>(
				PerformSys.class);

		List<PerformSys> list = null;
		if (name == null || name == "")
			list = (List<PerformSys>) action.searchList(
					ClientSQL.SEARCH_USER_SYS, new Object[] { userid }, mapper);
		else {
			list = (List<PerformSys>) action.searchList(
					ClientSQL.SEARCH_BELIEVE_SYS, new Object[] {
							'%' + name + '%', '%' + name + '%',
							'%' + name + '%' }, mapper);
		}
		return list;
	}

	public List<PerformSysConn> findSysConne(Integer userid, Integer index) {
		// TODO Auto-generated method stub

		RowMapper<PerformSysConn> mapper = new BeanPropertyRowMapper<PerformSysConn>(
				PerformSysConn.class);
		@SuppressWarnings("unchecked")
		List<PerformSysConn> list = (List<PerformSysConn>) action.searchList(
				ClientSQL.SEARCH_CONNECTION, new Object[] { userid, userid,
						index }, mapper);
		return list;
	}

}
