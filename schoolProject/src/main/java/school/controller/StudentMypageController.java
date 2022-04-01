package school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import school.command.PwCommand;
import school.command.StudentCommand;
import school.service.department.DepartmentListService;
import school.service.stuMypage.StuentMypageDetailService;
import school.service.stuMypage.StuentMypagePwChangeService;
import school.service.stuMypage.StuentMypagePwConService;
import school.service.student.StudentDetailService;
import school.service.student.StudentMaxNumService;
import school.service.student.StudentUpdateService;

@Controller
@RequestMapping("studentMypage")
public class StudentMypageController {
	@Autowired
	StuentMypageDetailService stuentMypageDetailService;
	@Autowired
	StuentMypagePwChangeService stuentMypagePwChangeService;
	@Autowired
	StuentMypagePwConService stuentMypagePwConService;
	@Autowired
	StudentMaxNumService studentMaxNumService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	StudentDetailService studentDetailService;
	@Autowired
	StudentUpdateService studentUpdateService;
	
	@RequestMapping("studentNum")
	public String studentNum(@RequestParam("studentNum")String studentNum , Model model) {
		studentMaxNumService.execute(studentNum , model);
		return "student/studentMaxNum";
	}
	
	@RequestMapping(value = "studentMyPageUpdate" , method = RequestMethod.POST)
	public String studentMyPageUpdate1(@Validated StudentCommand studentCommand , BindingResult result , Model model) {
		if(result.hasErrors()) {
			departmentListService.execute(model);
			return "studentMypage/stuMypageUpdate";
		}
		studentUpdateService.execute(studentCommand);
		return "redirect:stuMypage";
	}
	
	@RequestMapping(value = "stuMypageModify" , method = RequestMethod.POST)
	public String stuMypageUpdate(@Validated PwCommand pwCommand , BindingResult result , Model model ,HttpSession session ) {
		if(result.hasErrors()) {
			return "studentMypage/stuMypageUpdate";
		}
		String path = stuentMypagePwConService.execute(pwCommand , result ,model ,session );
		return path;
	}
	@RequestMapping(value = "studentPw")
	public String studentPw( PwCommand pwCommand) {
		return "/studentMypage/stuMyPw";
	}
	
	@RequestMapping(value = "studentPwChange" , method = RequestMethod.POST)
	public String studentPwChange(@Validated  PwCommand pwCommand , BindingResult result , HttpSession session) {
		if(result.hasErrors()) {
			return "/studentMypage/stuPwChange";
		}
		String path = stuentMypagePwChangeService.execute(pwCommand , result , session);
		return path;
	}
	
	@RequestMapping(value = "studentPwChange" ,method = RequestMethod.GET)
	public String studentPwChange(HttpSession session) {
		return "/studentMypage/stuPwChange";
		
	}
	@RequestMapping("stuMypage")
	public String stuMypage(HttpSession session , Model model) {
		stuentMypageDetailService.execute(session , model);
		return "/studentMypage/stuMypage";
	}
	
}
