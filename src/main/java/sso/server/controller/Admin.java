package sso.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sso.server.dao.Manager;
import sso.server.service.UserService;

@RequestMapping("admin")
@Controller
public class Admin {

	@Lazy
	@Autowired
	UserService userService;

	/*
	 * 系统
	 */
	@RequestMapping("index")
	public String index(HttpSession session, RedirectAttributes attributes) {
	
		if(session.getAttribute("admin")==null)
			return "redirect:login";
		return "Admin/man_sys";
	}
	
	/*
	 * 用户--普通用户
	 */
	@RequestMapping("user")
	public String user(HttpSession session, RedirectAttributes attributes) {
		

		return "Admin/man_user_user";
	}
	
	/*
	 * 用户--系统用户
	 */
	@RequestMapping("sysuser")
	public String sysuser(HttpSession session, RedirectAttributes attributes) {

		return "Admin/man_user_sys";
	}
	
	/*
	 * 用户--管理员
	 */
	@RequestMapping("man")
	public String man(HttpSession session, RedirectAttributes attributes) {

		return "Admin/man_user_admin";
	}
	
	/*
	 * 信任关系
	 */
	@RequestMapping("conn")
	public String conn(HttpSession session, RedirectAttributes attributes) {
		

		return "Admin/man_sys_connected";
	}
	/*
	 * 
	 */
	
	
	/*
	 * use to super Manager to add more managers
	 */
@ResponseBody
@RequestMapping(value="reg",method=RequestMethod.POST)
public boolean reg(Manager manager){
	boolean result = userService.ManagerRegister(manager);
	return result;
}

	@RequestMapping("login")
	public String login(HttpSession session) {
		if (session.getAttribute("admin") != null)
			return "redirect:index";
		return "login/Admin";
	}

	@RequestMapping(value = "loginCheck", method = RequestMethod.GET)
	public String loginCheck() {
		return "redirect:index";
	}

	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String loginCheck(@RequestParam("name") String phone,
			@RequestParam("pwd") String password, HttpSession session) {
		Manager user = userService.ManagerLogin(phone, password);
		System.out.println("manager login" + user + "\t phone" + phone
				+ "\t pwd:" + password);
		if (user != null && user.getMid() != null) {
			session.setAttribute("admin", user.getPhone());
			session.setAttribute("admin_role", user.getRole());
			return "redirect:index";
		}
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("admin_role");
		return "login/Admin";
	}
}
