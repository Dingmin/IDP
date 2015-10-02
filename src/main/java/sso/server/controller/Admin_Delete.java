package sso.server.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sso.server.service.AdminService;

@RequestMapping("admin")
@Controller
public class Admin_Delete {

	@Autowired
	AdminService service;
	
	/*
	 * 删除系统
	 */
	@ResponseBody
	@RequestMapping("del")
	public boolean del(HttpSession session,@RequestParam("sid") Integer sid){
		if(session.getAttribute("admin")==null)
			return false;
		boolean flag =service.DelSys(sid);
		return flag;
	}
	
	/*
	 * 删除系统负责人
	 */
	@ResponseBody
	@RequestMapping("delClient")
	public boolean delClient(HttpSession session,@RequestParam("index") Integer index){
		if(session.getAttribute("admin")==null)
			return false;
		boolean flag = service.DelClient(index);
		return flag;
	}
	
	/*
	 * 删除普通用户
	 */
	@ResponseBody
	@RequestMapping("delUser")
	public boolean delUser(HttpSession session,@RequestParam("index") Integer index){
		if(session.getAttribute("admin")==null)
			return false;
		boolean flag = service.delUser(index);
		return flag;
	}
	
	/*
	 * 删除管理员
	 */
	@ResponseBody
	@RequestMapping("delMan")
	public boolean delMan(HttpSession session,@RequestParam("index") Integer index,@RequestParam("role") Integer role){
		if(session.getAttribute("admin")==null)
			return false;
		boolean flag = service.delMan((Integer)session.getAttribute("admin_role"),index,role);
		return flag;
	}
	
	/*
	 *删除信任关系 
	 */
	@ResponseBody
	@RequestMapping("delConn")
	public boolean delConn(HttpSession session,@RequestParam("index") Integer index){
		if(session.getAttribute("admin")==null)
			return false;
		boolean flag = service.DelConn(index);
		return flag;
	}
}
