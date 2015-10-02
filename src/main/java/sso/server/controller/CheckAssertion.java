package sso.server.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sso.server.dao.SysRegister;
import sso.server.dao.User;
import sso.server.service.AssertionLink;
import sso.server.service.AssertionService;
import sso.server.service.UserService;
import sso.server.service.Verify;

@RequestMapping("service")
@Controller
public class CheckAssertion {

	@Lazy
	@Autowired
	UserService service;

	@Lazy
	@Autowired
	AssertionLink object;

	@Lazy
	@Autowired
	Verify verify;

	@Lazy
	@Autowired
	AssertionService ticketLib;

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue(value = "sso", required = false) String ssoSys,
			@RequestParam("target") String Targeturl,
			RedirectAttributes attributes) throws Exception {
		System.out.println("the cookie from user:" + ssoSys);

		System.out.println("target:" + Targeturl);
		String[] urls = service.decrypt(Targeturl);
		if (urls != null && urls.length != 2) {

			return new ModelAndView("error/reject");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("target", Targeturl);
		System.out.println("urls:" + urls[0] + "\t" + urls[1]);
		Integer flag = service.checkRoot(urls);// 确定访问系统是否使用单点登录，是则返回系统id,否则，返回空
		System.out.println("当前系统id:" + flag);
		if(flag==null){//flag=null表明当前系统没有在IDP端进行系统登记
			return new ModelAndView("error/error");
		}
		String assertion = null;
		if (ssoSys == null) {
			map.put("flag", "" + flag);
			return new ModelAndView("login/login", map);
		}
		Cookie[] cookie = request.getCookies();
		String jsessionid = "";
		for (Cookie c : cookie) {
			System.out.println(c.getName() + c.getDomain() + "\t" + c.getPath()
					+ "\t" + c.getValue());
			if (c.getName().equals("JSESSIONID")) {
				System.out.println("find jessionid:" + c.getValue());
				jsessionid = c.getValue();
				break;
			}
		}
		if (ssoSys.startsWith("" + flag) || ssoSys.endsWith("*" + flag)
				|| ssoSys.contains("*" + flag + "*")) {// 查找系统是否已已登录
			assertion = ticketLib.findAssertion(ssoSys, jsessionid);
			if(assertion!=null){
			response.sendRedirect(urls[0] + "?assert=" + assertion);
			return null;}

		} else {// 检查信任该系统的登录信息，如果有，则使用该assertion
			if (flag != null) {
				assertion = ticketLib.FindRelativeSys(flag, ssoSys, jsessionid);
				for (Cookie c : cookie) {
					if (c.getName().equals("sso")) {

						if (assertion != null) {
							 c.setValue(ssoSys+"*"+flag);
							c.setPath("/");
							c.setMaxAge(24*60*60);
							response.addCookie(c);
							response.sendRedirect(urls[0] + "?assert=" + assertion);
							return null;
						}
					}
				}
			} else
				return new ModelAndView("error/error");
		}
		map.put("flag", "" + flag);
		return new ModelAndView("login/login", map);
	}

	@RequestMapping(value = "check", method = RequestMethod.GET)
	public ModelAndView turnToLogin(@RequestParam("target") String targetUrl) {
		return new ModelAndView("redirect:login?target=" + targetUrl);
	}

	@RequestMapping(value = "check", method = RequestMethod.POST)
	public ModelAndView checkForm(HttpServletRequest request,@CookieValue(value="sso",required=false) String ssoSys,
			HttpServletResponse response,
			@RequestParam(value = "flag", required = false) Integer flag,
			@RequestParam("name") String username,
			@RequestParam("pwd") String pwd,
			@RequestParam("target") String targetUrl,
			RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		User u = service.userLogin(username, pwd);
		if (u != null) {
			String assertion = null;
			try {
				System.out.println("用户验证成功,当前验证的系统id是" + flag);
				String ip = getIpAddr(request);
				System.out.println(ip);
				assertion = object.generateAssertion(username);
				System.out.println("记录assertion" + request.getRemoteAddr());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("generate assertion error");
				e1.printStackTrace();
				model.setViewName("error/error");
				return model;
			}
			try {
				String[] urls = service.decrypt(targetUrl);
				String jsessionid = "";
				Cookie[] cookie = request.getCookies();
				for (Cookie c : cookie) {
					System.out.println(c.getName() + c.getDomain() + "\t"
							+ c.getPath() + "\t" + c.getValue());
					if (c.getName().equals("JSESSIONID")) {
						System.out.println("find jessionid:" + c.getValue());
						jsessionid = c.getValue();
						Cookie k ;
						if(ssoSys==null)
							k=new Cookie("sso",""+flag);
						else k=new Cookie("sso", ssoSys+"*" + flag);
						k.setPath("/");
						k.setComment(jsessionid);
						k.setMaxAge(24 * 60 * 60);
						System.out.println("jsessionid是:" + c.getValue()
								+ k.getComment());
						response.addCookie(k);
						break;
					}
				}
				if(ssoSys==null)
				ticketLib
						.SaveAssertion(assertion, jsessionid, flag, new Date());
				else 
					ticketLib.upateAssertion(flag, ssoSys, jsessionid);
				System.out.println("urls:" + urls[0] + "\t" + urls[1]);
				// ticketLib.SaveAssertion(assertion,
				// urls[0]==urls[1]?urls[0]:urls[0]+","+urls[1], new Date());
				System.out.println("转到目标地址" + urls[0] + "?assert=" + assertion);

				response.sendRedirect(urls[0] + "?assert=" + assertion);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView("error");
			}

			return null;
		} else {
			System.out.println("验证失败");
			model.setViewName("redirect:login?target=" + targetUrl);
			redirectAttributes.addFlashAttribute("ques", "error");
		}
		return model;
	}

	@RequestMapping("failed")
	public ModelAndView power() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", "hahahah");
		return new ModelAndView("error/no_power", map);
	}

	@RequestMapping("error")
	public ModelAndView error() {
		return new ModelAndView("error/error");
	}

	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request,@CookieValue(value="sso",required=false) String ssoSys,
			@CookieValue(value="JSESSIONID",required=false) String jsessionid,
			HttpServletResponse response, @RequestParam("params") String url)
			throws Exception {
		// delete the assertion
		// String ip = getIpAddr(request);
		if(ssoSys==null||ssoSys=="")
			return new ModelAndView("error/no_power", "mesg","单点登出成功，请勿刷新界面");
		url = URLDecoder.decode(url, "utf-8"); 
		//确认链接是否存在以及是否使用单点登录
		SysRegister sys = ticketLib.findRegister(url);
		if (sys == null) {// 非注册系统
			return new ModelAndView("error/no_power", "mesg", url
					+ "系统没有注册，无需单点登出");
		}
		else{
			String result = "";
			if(sys.getSso()==0){
				if(ssoSys==""+sys.getSid())
					result ="";
				if(ssoSys.startsWith(""+sys.getSid()+"*")){
					result=ssoSys.replaceFirst(ssoSys, ""+sys.getSid()+"*");
				System.out.println("start"+result);	
				}
				if(ssoSys.endsWith("*"+sys.getSid()))
					result=ssoSys.substring(0,ssoSys.length()-(""+sys.getSid()).length()-1);
				if(ssoSys.contains("*"+sys.getSid()+"*"))
					result = result.replace("*"+sys.getSid()+"*", "");
			}
			else{
				result = ticketLib.DelRelativeSys(sys.getSid(), ssoSys);
				
			}
			//更新对应的cookie系统数据
			System.out.println("结果："+result);
			boolean logout = ticketLib.upateAssertion(result, ssoSys, jsessionid);
			if(logout){Cookie c;
				if(result==""){ result = null;
				 c = new Cookie("sso", result);
				c.setMaxAge(0);		
				}
				else{
					c = new Cookie("sso",result);
					c.setMaxAge(24*60*60);
				}
				c.setPath("/");
				response.addCookie(c);
				return new ModelAndView("logout/logout", "meg", "单点登出成功");
			}
			else return new ModelAndView("logout/logout", "meg", "单点登出失败");
				
		}
//		System.out.println("cookie.size:" + cookie.length);
//		String ssoSys = "";
//		Cookie test = null;
//		String Jsessionid = "";
//
//		for (Cookie c : cookie) {
//			System.out.println(c.getName() + c.getDomain() + "\t" + c.getPath()
//					+ "\t" + c.getValue());
//			if (c.getName().equals("sso")) {
//				ssoSys = c.getValue();
//				if (ssoSys.startsWith("" + flag) || ssoSys.endsWith("*" + flag)
//						|| ssoSys.contains("*" + flag + "*")) {
//					test = c;
//
//				}}
//				if (c.getName().equals("JSESSIONID")) {
//					System.out.println("find jessionid:" + c.getValue());
//					Jsessionid = c.getValue();
//
//				}
//
//			
//		}
//		System.out.println("ssosys:" + ssoSys + "\t" + "jsessionid:"
//				+ Jsessionid);
//		// 得到更新后的sso cookie的值
//		boolean result = ticketLib.dropAssertion(ssoSys, Jsessionid);
//		System.out.println("删除结果：" + result);
//		if (result) {
//			test.setValue(null);
//			test.setMaxAge(0);
//			test.setPath("/");
//			response.addCookie(test);
//			return new ModelAndView("logout/logout", "meg", "单点登出成功");
//		}
//		return new ModelAndView("logout/logout", "meg", "单点登出失败");
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.split(",")[0];
	}
}
