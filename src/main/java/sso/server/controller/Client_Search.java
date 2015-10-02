package sso.server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sso.server.dao.PerformConnReq;
import sso.server.dao.PerformSysConn;
import sso.server.dao.SysRegister;
import sso.server.service.ClientService;

@RequestMapping("client")
@Controller
public class Client_Search {

	@Autowired
	ClientService service;

	/*
	 * 系统列表
	 */
	@ResponseBody
	@RequestMapping("findSys")
	public List<SysRegister> findSys(HttpSession session,
			@RequestParam("index") Integer index) {

		if (session.getAttribute("user") == null)
			return null;
		List<SysRegister> list = service.findSys(
				(Integer) session.getAttribute("user"), index);
		return list;
	}
	
	/*
	 * 信任请求列表
	 */
	@ResponseBody
	@RequestMapping("findConneReq")
	public List<PerformConnReq> findConn(HttpSession session,
			@RequestParam("index") Integer index) {

		if (session.getAttribute("user") == null)
			return null;
		List<PerformConnReq> list = service.findConneReq(
				(Integer) session.getAttribute("user"), index);
		return list;
	}
	/*
	 * 信任关系列表
	 */
	@ResponseBody
	@RequestMapping("findSysConne")
	public List<PerformSysConn> findSysConne(HttpSession session,
			@RequestParam("index") Integer index) {

		if (session.getAttribute("user") == null)
			return null;
		List<PerformSysConn> list = service.findSysConne(
				(Integer) session.getAttribute("user"), index);
		return list;
	}
}
