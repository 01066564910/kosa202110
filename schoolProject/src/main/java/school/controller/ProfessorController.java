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
import school.service.check.StudentEmailCkService;
import school.service.check.StudentEmailUpdateCkService;
import school.service.check.StudentIdCkService;
import school.service.department.DepartmentListService;
import school.service.professor.ProfessorDeleteService;
import school.service.professor.ProfessorDetailService;
import school.service.professor.ProfessorListService;
import school.service.professor.ProfessorMaxNumService;
import school.service.professor.ProfessorUpdateService;
import school.service.professor.ProfessorWriteService;

@Controller
@RequestMapping("professor")
public class ProfessorController {
	@Autowired
	ProfessorMaxNumService professorMaxNumService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	ProfessorWriteService professorWriteService;
	@Autowired
	ProfessorListService professorListService;
	@Autowired
	ProfessorDetailService professorDetailService;
	@Autowired
	ProfessorUpdateService professorUpdateService;
	@Autowired
	ProfessorDeleteService professorDeleteService;
	@Autowired
	StudentIdCkService studentIdCkService;
	@Autowired
	StudentEmailCkService studentEmailCkService;
	@Autowired
	StudentEmailUpdateCkService studentEmailUpdateCkService;
	
	
	@RequestMapping(value = "professDelete")
	public String professDelete(@RequestParam(value = "num")String num) {
		professorDeleteService.execute(num);
		return "redirect:professorList";
	}
	
	@RequestMapping(value = "professUpdate" ,method = RequestMethod.POST )
	public String professUpdate(@Validated  ProfessorCommand professorCommand , BindingResult result , Model model , HttpSession session) {
		String i = studentEmailUpdateCkService.execute(professorCommand.getProfessorEmail() , session);
		if(i =="1") {
			departmentListService.execute(model);
			return "professor/proUpdate";
		}
		
		if(result.hasErrors()) {
			departmentListService.execute(model);
			return "professor/proUpdate";
		}
		professorUpdateService.execute(professorCommand);
		return "redirect:professDetail?num="+professorCommand.getProfessorNum();
		
	}
	
	@RequestMapping(value = "professUpdate" , method = RequestMethod.GET)
	public String professUpdate(@RequestParam(value = "num")String professorNum  , ProfessorCommand professorCommand , Model model) {
		departmentListService.execute(model);
		professorDetailService.execute(professorNum , model);
		return "professor/proUpdate";
	}
	@RequestMapping(value = "professDetail")
	public String professDetail(@RequestParam(value = "num")String professorNum  ,Model model) {
		professorDetailService.execute(professorNum , model);
		return "professor/proDateil";
	}
	
	
	@RequestMapping(value = "proWrite" ,method = RequestMethod.POST )
	public String proWrite(@Validated  ProfessorCommand professorCommand , BindingResult result , Model model) {
		String i = studentIdCkService.execute(professorCommand.getProfessorId());
		if(i =="1") {
			departmentListService.execute(model);
			return "professor/proWrite";
		}
		String ii = studentEmailCkService.execute(professorCommand.getProfessorEmail());
		if(ii =="1") {
			
			departmentListService.execute(model);
			return "professor/proWrite";
		}
		
		if(result.hasErrors()) {
			departmentListService.execute(model);
			return "professor/proWrite";
		}
		
		if(!professorCommand.isEqPw(professorCommand.getProfessorPw(), professorCommand.getProfessorPwCon())) {
			result.rejectValue("getProfessorPw", "professorCommand.getProfessorPw", "비밀번호가 다릅니다");
			return "professor/proWrite";
		}
		professorWriteService.execute(professorCommand);
		return "redirect:professorList";
		
	}
	
	@RequestMapping(value = "proWrite" ,method = RequestMethod.GET )
	public String proWrite(ProfessorCommand professorCommand , Model model) {
		departmentListService.execute(model);
		professorMaxNumService.execute(professorCommand);
		return "professor/proWrite";
	}
	
	@RequestMapping("professorList")
	public String professorList(Model model) {
		professorListService.execute(model);
		return "professor/professorList";
	}
}
