package sso.server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sso.server.dao.PerformSys;
import sso.server.dao.SysConnected;
import sso.server.dao.SysRegister;
import sso.server.service.ClientService;

@RequestMapping("client")
@Controller
public class Client_Add {
 
	@Autowired
	ClientService service;
	
	//------------------------
	//逻辑处理
	//------------------------
	/*
	 * 查询当前用户已注册的系统
	 */
	@ResponseBody
	@RequestMapping(value="findUserSys",method=RequestMethod.POST)
	public List<PerformSys> findUserSys(HttpSession session,String name){
		if(session.getAttribute("user")==null)
			return null;
		List<PerformSys> list = service.findUserSys((Integer)session.getAttribute("user"),name);
		return list;
	}
	
	/*
	 * 接受信任请求
	 */
	@ResponseBody
	@RequestMapping(value="rev",method=RequestMethod.POST)
	public boolean rev(@RequestParam("index") Integer index){
		boolean flag = service.rev(index);
		return flag;
	}
	
	/*
	 * 拒绝信任请求
	 */
	@ResponseBody
	@RequestMapping(value="reject",method=RequestMethod.POST)
	public boolean reject(@RequestParam("index") Integer index){
		boolean flag = service.reject(index);
		return flag;
	}
	
	/*
	 * 增加系统操作
	 */
	@ResponseBody
	@RequestMapping("addSystem")
	public boolean addSystem(HttpSession session,SysRegister sys){
		if(session.getAttribute("user")==null)
			return false;
		sys.setUid((Integer)session.getAttribute("user"));
		boolean flag = service.addSystem(sys);
		return flag;
	}
	
	/*
	 * 增加信任关系
	 */
	@ResponseBody
	@RequestMapping("addConn")
	public boolean addConn(HttpSession session,SysConnected conn){
		if(session.getAttribute("user")==null)
			return false;
		boolean flag = service.addConnection(conn);
		return flag;
	}
	
	
	//------------------------
	//页面控制
	//------------------------
	
	
	/*
	 * 增加系统
	 */
	@RequestMapping("addsys")
	public String addSys(HttpSession session){
		if (session.getAttribute("user") == null)
			return "redirect:index";
		return "Client/add_sys";
	}
	
	/*
	 * 增加信任关系
	 */
	@RequestMapping("addconn")
	public String addconn(HttpSession session){
		if (session.getAttribute("user") == null)
			return "redirect:index";
		return "Client/addconn";
	}
}
