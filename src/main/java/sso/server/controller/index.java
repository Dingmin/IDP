package sso.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class index {

	@RequestMapping(value={"","/"})
	public ModelAndView Index(){
		return new ModelAndView("register/index");
	}
}
