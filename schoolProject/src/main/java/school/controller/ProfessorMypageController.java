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

import school.command.ProfessorCommand;
import school.command.PwCommand;
import school.service.department.DepartmentListService;
import school.service.professor.ProfessorDetailService;
import school.service.professor.ProfessorUpdateService;
import school.service.promypage.ProMypageDetailService;
import school.service.promypage.ProMypagePwChangeService;
import school.service.promypage.ProfessorPwService;

@Controller
@RequestMapping("professorMypage")
public class ProfessorMypageController {
	@Autowired
	ProMypageDetailService proMypageDetailService;
	@Autowired
	ProMypagePwChangeService  proMypagePwService;
	@Autowired
	ProfessorDetailService professorDetailService;
	@Autowired
	ProfessorUpdateService professorUpdateService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	ProfessorPwService professorPwService;
	
	@RequestMapping(value = "professorMyPageUpdate" , method = RequestMethod.POST)
	public String professorMyPageUpdate1(@Validated ProfessorCommand professorCommand , BindingResult result) {
		 professorUpdateService.execute(professorCommand);
		return "professorMypage/proMypage";
				
	}
	
	
	@RequestMapping(value = "professorMyPageUpdate" , method = RequestMethod.GET)
	public String professorMyPageUpdate(@RequestParam(value = "num")String professorNum ,
				@RequestParam(value = "pw")String pw , Model model , HttpSession session , ProfessorCommand professorCommand) {
		departmentListService.execute(model);  
		professorDetailService.execute(professorNum, model);
		String i = professorPwService.execute(pw , session);
		return i;
	}
	
	@RequestMapping(value = "professorMyPagePw")
	public String professorMyPagePw(HttpSession session , Model model) {
		proMypageDetailService.execute(session , model );
		return "professorMypage/proMypagePwCon";
	}
	
	@RequestMapping(value = "professorPwChange" , method = RequestMethod.POST)  
	public String professorPwChange1(@Validated PwCommand pwCommand ,BindingResult result , HttpSession session ) {
		
		if(result.hasErrors()) {
			return "professorMypage/proMypagePw";
		}
		String i = proMypagePwService.execute(pwCommand , result , session  );
		return i;
	}
	
	@RequestMapping(value = "professorPwChange" , method = RequestMethod.GET)
	public String professorPwChange(PwCommand pwCommand) {
		
		return "professorMypage/proMypagePw";
	}
	
	
	@RequestMapping("proMypage")
	public String proMypage(HttpSession session , Model model) {
		proMypageDetailService.execute(session , model );
		return "professorMypage/proMypage";
	}
	
}
