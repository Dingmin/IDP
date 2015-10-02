package sso.server.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sso.server.dao.Manager;
import sso.server.dao.PerformSys;
import sso.server.dao.SsoClient;
import sso.server.dao.SysConnected;
import sso.server.dao.SysRegister;
import sso.server.dao.User;
import sso.server.service.CommonService;

@RequestMapping("common")
@Controller
public class Common {

	@Autowired
	CommonService service;
	
	//--------------------
	//增加普通用户
	//--------------------
	@ResponseBody
	@RequestMapping("addUser")
	public boolean addUser(User u){
		boolean flag = service.addUser(u);
		return flag;
	}
	
	/*
	 * 检查唯一凭证是否可用
	 * 唯一凭证可为邮箱，学号等
	 */
	@ResponseBody
	@RequestMapping("checkUserToken")
	public boolean checkUserToken(@RequestParam("name") String name){
		boolean flag =service.checkUserToken(name);
		return flag;
	}
	
	
	
	/*
	 * 检查管理员手机号是否被占用
	 */
	@ResponseBody
	@RequestMapping("checkAdminPhone")
	public boolean checkAdminPhone(@RequestParam("name") String phone){
		boolean flag =service.checkAdminPhone(phone);
		return flag;
	}
	
	/*
	 * 增加管理员
	 */
	@ResponseBody
	@RequestMapping("addAdmin")
	public boolean addAdmin(Manager m){
		m.setRole(1);
		boolean flag =service.addAdmin(m);
		return flag;
	}
	
	/*
	 * 检查系统负责人手机号是否被占用
	 */
	@ResponseBody
	@RequestMapping("checkClientPhone")
	public boolean checkClientPhone(@RequestParam("name") String phone){
		boolean flag =service.checkClientPhone(phone);
		return flag;
	}
	
	/*
	 * 检查系统负责人邮箱是否被占用
	 */
	@ResponseBody
	@RequestMapping("checkClientEmail")
	public boolean checkClientEmail(@RequestParam("name") String email){
		boolean flag =service.checkClientEmail(email);
		return flag;
	}
	
	
	/*
	 * 增加管理员
	 */
	@ResponseBody
	@RequestMapping("addClient")
	public boolean addClient(SsoClient m){
		boolean flag =service.addClient(m);
		return flag;
	}
	
	/*
	 * 根据关键字查找相关负责人
	 */
	@ResponseBody
	@RequestMapping("findClient")
	public List<SsoClient> findClient(@RequestParam("name") String name){
		List<SsoClient> list = service.findClient(name);
		return list;
	}
	
	/*
	 * 增加系统
	 */
	@ResponseBody
	@RequestMapping("addSys")
	public boolean addSys(SysRegister s){
		s.setDate(new Date());
		boolean flag = service.addSys(s);
		return flag;
	}
	
	/*
	 * 根据关键字查找系统
	 */
	@ResponseBody
	@RequestMapping("findSys")
	public List<PerformSys> findSys(@RequestParam("name") String name){
		List<PerformSys> list  = service.findSys(name);
		return list;
	}
	
	/*
	 * 增加信任关系
	 */
	@ResponseBody
	@RequestMapping("addSysConne")
	public boolean addSysConne(SysConnected s){
		boolean flag = service.addSysConne(s);
		return flag;
	}
	
}
