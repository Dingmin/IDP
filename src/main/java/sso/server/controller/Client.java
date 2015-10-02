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

import sso.server.dao.SsoClient;
import sso.server.service.UserService;

@RequestMapping("client")
@Controller
public class Client {

	@Lazy
	@Autowired
	UserService userService;
	
	/*
	 * the main page of the client
	 * 系统
	 */
	@RequestMapping("index")
	public String index(HttpSession session, RedirectAttributes attributes) {
	
		if(session.getAttribute("user")==null)
			return "redirect:login";
		return "Client/client_sys";
	}
	
	/*
	 * 信任关系
	 */
	@RequestMapping("conn")
	public String conn(HttpSession session, RedirectAttributes attributes) {
		
		if(session.getAttribute("user")==null)
			return "redirect:login";
		return "Client/client_sys_connected";
	}	
	
	/*
	 * 信任请求
	 */
	@RequestMapping("reply")
	public String reply(HttpSession session){
		if(session.getAttribute("user")==null)
			return "redirect:login";
		return "Client/client_sys_reply";
	}
	
	/*
	 * the register page for client to register
	 */
	@ResponseBody
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public boolean reg(SsoClient client) {
		boolean result = userService.ClientRegister(client);
		return result;
	}
	
	/*
	 * the login page of the client
	 */
	@RequestMapping("login")
	public String login(HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:index";
		return "login/ClientLogin";
	}

	@RequestMapping(value = "loginCheck", method = RequestMethod.GET)
	public String loginCheck() {
		return "redirect:index";
	}

	/*
	 * the request of client to login
	 */
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String loginCheck(@RequestParam("email") String email,
			@RequestParam("pwd") String password, HttpSession session) {
		SsoClient user = userService.ClientLogin(email, password);
		System.out.println("client login:" + user);
		if (user != null && user.getUid() != null) {
			session.setAttribute("user", user.getUid());
			session.setAttribute("user_name", user.getUsername());
			return "redirect:index";
		}
		return "redirect:index";
	}

	/*
	 * the request of the client to logout
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("user_name");
		return "redirect:index";
	}
	
	/*
	 * the  page of the client to register
	 */
	@RequestMapping("sign")
	public String register(){
		return "register/ClientReg";
	}
	
	/*
	 * register request
	 */
	@ResponseBody
	@RequestMapping(value="sign_up",method=RequestMethod.POST)
	public boolean sign(SsoClient client){
		boolean result = userService.ClientRegister(client);
		return result;
	}
	@ResponseBody
	@RequestMapping("checkemail")
	public boolean checkEmail(@RequestParam("name") String email){
		System.out.println(email);
		boolean result = userService.checkEmail(email);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("checkephone")
	public boolean checkPhone(@RequestParam("name") String phone){
		System.out.println(phone);
		boolean result = userService.checkPhone(phone);
		return result;
	}
}
