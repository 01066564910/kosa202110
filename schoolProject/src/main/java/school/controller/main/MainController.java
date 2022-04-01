package school.controller.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import school.command.LoginCommand;
import school.service.login.LoginService;

@Controller
public class MainController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/")
	public String main(LoginCommand loginCommand) {
		return "thymeleaf/index";
	}
	// 로그인 컨트롤러 따로 맹글맹글어야해여 
//	@RequestMapping(value = "loginPro" ,method = RequestMethod.POST)
//	public String loginPro(LoginCommand loginCommand , HttpSession session) {
//		String  i = loginService.execute(loginCommand , session);
//		return i;
//	}
}
