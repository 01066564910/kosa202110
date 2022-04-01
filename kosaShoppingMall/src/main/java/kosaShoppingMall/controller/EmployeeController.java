package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.service.EmailCheckService;
import kosaShoppingMall.service.EmailCkService;
import kosaShoppingMall.service.EmpEmailUpCheckService;
import kosaShoppingMall.service.IdcheckService;
import kosaShoppingMall.service.Employee.EmployeeDelService;
import kosaShoppingMall.service.Employee.EmployeeDeleteService;
import kosaShoppingMall.service.Employee.EmployeeDetailService;
import kosaShoppingMall.service.Employee.EmployeeListService;
import kosaShoppingMall.service.Employee.EmployeeModifyService;
import kosaShoppingMall.service.Employee.EmployeePwChangeOkService;
import kosaShoppingMall.service.Employee.EmployeePwChangeService;
import kosaShoppingMall.service.Employee.EmployeeUpdateService;
import kosaShoppingMall.service.Employee.EmployeesWriteService;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	@Autowired
	EmployeesWriteService employeesWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	EmployeeModifyService employeeModifyService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@Autowired
	EmployeePwChangeService  employeePwChangeService;
	@Autowired
	EmployeePwChangeOkService employeePwChangeOkService;
	@Autowired
	EmployeeDelService employeeDelService;
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	EmailCkService EmailCkService;
	@Autowired
	EmpEmailUpCheckService empEmailUpCheckService;
	
	//커맨드가 필요한 곳에 알아서 해주겠다?
	@ModelAttribute
	EmployeeCommand seteEmployeeCommand() {
		return new EmployeeCommand();
	}
	
	
	@RequestMapping(value="empDel" , method = RequestMethod.POST)
	public String empDel(@RequestParam (value="delete") String [] delete) {
		employeeDelService.exeute(delete);
		return "redirect:empList";
	}
	
	
	
	@RequestMapping("empPwChangeOk")
	public String empPwChangeOk(EmployeeCommand employeeCommand , Model model) {
		String path = employeePwChangeOkService.execute(employeeCommand , model);
		return path;
	}
	
	@RequestMapping("empPwChange")
	public String empPwChange(EmployeeCommand employeeCommand , Model model) {
		String path = employeePwChangeService.execute(employeeCommand , model);
		return path;
	}
	
	@RequestMapping("empDelete")
	public String empDelete(@RequestParam(value="empId")String empId , 
			@RequestParam(value="empPw") String empPw  ,EmployeeCommand employeeCommand , Model model) {
		employeeDeleteService.execute( empId , empPw,employeeCommand , model);
		return "thymeleaf/employee/empdel";
	}
	
	
	@RequestMapping(value="empUpdate" ,method = RequestMethod.POST)//BindingResult 는 커멘드에 저장이 되어서 저장이 된다.
	public String empUpdate(EmployeeCommand employeeCommand , BindingResult result ) {
		
		
		Integer i = empEmailUpCheckService.execute(employeeCommand.getEmpEmail() , employeeCommand.getEmpId());

		if(i == 1) {
			result.rejectValue("empEmail", "employeeCommand.empEmail", "중복된 이메일입니다.");
			return "thymeleaf/employee/empUpdate";
		}
		String path = employeeUpdateService.execute(employeeCommand ,result );
		return path;
	}
	
	@RequestMapping("empModify")
	public String empModify(@RequestParam(value="empId")String empId , 
							@RequestParam(value="empPw") String empPw  ,Model model) {
	
	String 	path = employeeModifyService.execute(empId , empPw , model);
		return path;
	}
	
	@RequestMapping("empDetail")
	public String  empDetail(EmployeeCommand employeeCommand , Model model) {
		employeeDetailService.execute(employeeCommand , model);
		return "thymeleaf/employee/empDetail";	
		}
	
	
	
	@RequestMapping(value="empWrite" , method =RequestMethod.POST )//커맨드 다음에 바인딩 리절트가 있어야 한다.
	public String empWritel(@Validated  EmployeeCommand employeeCommand , BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/empForm";
		}
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			return "thymeleaf/employee/empForm";
		}
		Integer i = idcheckService.execute(employeeCommand.getEmpId());
		if(i == 1) {
			result.rejectValue("empId", "employeeCommand.empId", "중복 아이디입니다.");
			return "thymeleaf/employee/empForm";
		}
		i = emailCheckService.execute(employeeCommand.getEmpEmail());
		if(i == 1) {
			result.rejectValue("empEmail", "employeeCommand.empEmail", "중복된 이메일입니다.");
			return "thymeleaf/employee/empForm";
		}
		employeesWriteService.execute(employeeCommand);
		return "redirect:/";
	}
	
	
	@RequestMapping(value="empWrite" , method = RequestMethod.GET)
	public String empWrite(EmployeeCommand employeeCommand) {
		return  "thymeleaf/employee/empForm";
	}
	
	@RequestMapping("empJoin")
	public String empJoin( ){
		//public String empJoin(Model model){ 이렇게 적어준것이랑 같다.
		//model.addAttribute("employeeCommand" ,new EmployeeCommand());
		return "thymeleaf/employee/empForm";
	}
	
	@RequestMapping("empList")
	public String empList(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page, Model model) {
		employeeListService.execute(model , page);
		return "thymeleaf/employee/empList";
	}
	
	

}
