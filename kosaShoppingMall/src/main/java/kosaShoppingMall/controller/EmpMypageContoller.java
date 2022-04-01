package kosaShoppingMall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.command.NewPwCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.service.EmpMyPage.EmployeeMyPageDetailService;
import kosaShoppingMall.service.EmpMyPage.EmployeeMyPagePwService;
import kosaShoppingMall.service.EmpMyPage.EmployeeMyPageUpdateService;

@Controller
@RequestMapping("empMypage")
public class EmpMypageContoller {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMyPageDetailService employeeMyPageDetailService; 
	@Autowired
	EmployeeMyPageUpdateService employeeMyPageUpdateService;
	@Autowired
	EmployeeMyPagePwService employeeMyPagePwService;
	@ModelAttribute
	EmployeeCommand setEmployeeCommand() {
		return new EmployeeCommand();
	}
	
	
	@RequestMapping("empDetail")
	public String empDetail(HttpSession session , Model model) {
		employeeMyPageDetailService.execute(session ,model);
		return "thymeleaf/employeeShip/empShipDetail";
	}
	@RequestMapping("empModify")
	public String empModify(  HttpSession session , Model model) {
		employeeMyPageDetailService.execute( session, model);
		return "thymeleaf/employeeShip/empShipUpdate";
	}
	
	@RequestMapping(value="empUpdate" , method = RequestMethod.POST)
	public String empUpdate(@Validated EmployeeCommand employeeCommand ,BindingResult result , HttpSession session ) {
		if(result.hasErrors()) {
			return "thymeleaf/employeeShip/empShipUpdate";
		}
		String path= employeeMyPageUpdateService.execute(employeeCommand , result ,session );
		return path;
	}
	@RequestMapping("empMyPw")
	public String empMyDelete(EmployeeCommand employeeCommand ,Model model, HttpSession session ,  NewPwCommand newPwCommand ) {
		String path = "thymeleaf/employeeShip/empShipPw";
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(employeeCommand.getEmpPw(), authInfo.getUserPw())) {
			
		}else{
			employeeMyPageDetailService.execute(session ,model);
			model.addAttribute("err_pw" ,"비밀번호가 다릅니다.");
			return "thymeleaf/employeeShip/empShipDetail";
		}return path;
	
		
	}
	@RequestMapping(value = "empMyPwOk" , method = RequestMethod.POST)
	public String empMyPwOk(@Validated NewPwCommand  newPwCommand , BindingResult result , HttpSession session
				,EmployeeCommand employeeCommand) {
		if(result.hasErrors()) {
			return "thymeleaf/employeeShip/empShipPw";
		}
		String	path= employeeMyPagePwService.execute(newPwCommand , result, session);
		return path;
	}
	
	
	
}
