package sso.server.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sso.server.service.ClientService;

@RequestMapping("client")
@Controller
public class Client_Delete {

	@Autowired
	ClientService service;
	
	/*
	 * 删除系统
	 */
	@ResponseBody
	@RequestMapping("delSys")
	public boolean delSys(HttpSession session,@RequestParam("index") Integer index){
		if(session.getAttribute("user")==null)
			return false;
		boolean flag = service.delSys((Integer)session.getAttribute("user"),index);
		return flag;
	}
	
	/*
	 * 删除信任关系 
	 */
	@ResponseBody
	@RequestMapping("delConn")
	public boolean delConn(HttpSession session,@RequestParam("index") Integer index){
		if(session.getAttribute("user")==null)
			return false;
		boolean flag =service.delConn(index);
		return flag;
	}
}
