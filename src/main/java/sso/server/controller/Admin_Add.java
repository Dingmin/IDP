package sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sso.server.service.CommonService;

@RequestMapping("admin")
@Controller
public class Admin_Add {

	@Autowired
	CommonService service;
	//-------------------------
	//逻辑
	//-------------------------
	
	
	//-------------------------
	//-----界面
	//-------------------------
	/*
	 * 增加系统
	 */
	@RequestMapping("addsys")
	public String addSys(){
		return "Admin/add_sys";
	}
	
	/*
	 * 增加系统用户
	 */
	@RequestMapping("addsys_user")
	public String addSys_user(){
		return "Admin/addsys_user";
	}
	
	/*
	 * 增加普通用户
	 */
	@RequestMapping("adduser")
	public String addUser(){
		return "Admin/adduser";
	}
	/*
	 * 增加管理员
	 */
	@RequestMapping("add_admin")
	public String addAdmin(){
		return "Admin/add_admin";
	}
	
	/*
	 * 增加信任关系
	 */
	@RequestMapping("addconn")
	public String addconn(){
		return "Admin/addconn";
	}
}
