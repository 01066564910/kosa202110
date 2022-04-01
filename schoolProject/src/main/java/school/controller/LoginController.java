package school.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import school.command.LoginCommand;
import school.service.login.LoginService;

@Controller

public class LoginController {
	@Autowired
	LoginService loginService; 

	
	@RequestMapping(value="/login/loginPro" , method = RequestMethod.POST)
	public String loginPro(@Validated LoginCommand loginCommand ,BindingResult result,HttpSession session  , HttpServletRequest request,
			HttpServletResponse response) {
		
		if(result.hasErrors()) {
			return  "thymeleaf/index";
		}
		String  path= loginService.execute( loginCommand , result , session , request ,response);
		return path;
		
	}@RequestMapping("/login/logout")
	 public String logout(HttpSession session , HttpServletResponse response) {
		Cookie cookie = new Cookie("autoLogin" ,"");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		session.invalidate();
		return "redirect:/";
	}
			
}
