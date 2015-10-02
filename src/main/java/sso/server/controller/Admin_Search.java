package sso.server.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sso.server.dao.Manager;
import sso.server.dao.PerformSys;
import sso.server.dao.PerformSysConn;
import sso.server.dao.SsoClient;
import sso.server.dao.User;
import sso.server.service.AdminService;

@RequestMapping("admin")
@Controller
public class Admin_Search {
	
	@Autowired
	AdminService service;

	/*
	 * 系统列表
	 */
	@ResponseBody
@RequestMapping("findSys")
public List<PerformSys> findSys(@RequestParam("index")Integer index){
	List<PerformSys> list = service.getSys(index);
	return list;
}
	/*
	 * 系统负责人列表
	 */
	@ResponseBody
	@RequestMapping("findClient")
	public List<SsoClient> findClient(@RequestParam("index")Integer index){
		List<SsoClient> list = service.findClient(index);
		return list;
	}
	
	/*
	 * 系统负责人列表
	 */
	@ResponseBody
	@RequestMapping("findUser")
	public List<User> findUser(@RequestParam("index")Integer index){
		List<User> list = service.findUser(index);
		return list;
	}
	
	/*
	 * 管理员列表
	 */
	@ResponseBody
	@RequestMapping("findMan")
	public List<Manager> findMan(@RequestParam("index")Integer index){
		List<Manager> man =service.findMan(index);
		return man;
	}
	
	@ResponseBody
	@RequestMapping("findConn")
	public List<PerformSysConn> findConn(@RequestParam("index")Integer index){
		List<PerformSysConn> man =service.findConn(index);
		return man;
	}
}

