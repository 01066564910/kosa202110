package kosaShoppingMall.controller;

import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import kosaShoppingMall.command.LoginCommand;
import kosaShoppingMall.service.goods.GoodsListPageService;
import kosaShoppingMall.service.login.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService; 
	@Autowired
	GoodsListPageService goodsListPageService; 
	@RequestMapping("/login/loginPro")
	public String loginPro(@Validated LoginCommand loginCommand ,BindingResult result,HttpSession session  , HttpServletRequest request,
			HttpServletResponse response) {
		String path =null;
		if(result.hasErrors()) {
			
			goodsListPageService.execute(request);
			
			return  "thymeleaf/index";
		}
		 path= loginService.execute( loginCommand , result , session , request ,response);
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
