package sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sso.server.dao.User;
import sso.server.service.UserService;

@Controller
public class UserRegister {

	@Autowired
	UserService service;
	
	@ResponseBody
	@RequestMapping("checkToken")
	public boolean checkToken(@RequestParam("name")String name){
		boolean flag = service.checkToken(name);
		return flag;
	}
	
	@ResponseBody
	@RequestMapping("sign")
	public boolean sign(User u){
		boolean flag = service.userRegister(u);
		return flag;
	}
}
