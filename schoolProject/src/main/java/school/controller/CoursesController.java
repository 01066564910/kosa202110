package school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import school.command.CoursesSubjectCommand;
import school.service.courses.ClasesListService;
import school.service.courses.ClassSubjectService;
import school.service.courses.CoureseProfessorCountService;
import school.service.courses.CoureseProfessorDeleteService;
import school.service.courses.CoureseProfessorListService;
import school.service.courses.CoursesSubjectInsertService;
import school.service.courses.ProfessorListAjaxService;

@Controller
@RequestMapping("courses")
public class CoursesController {
	@Autowired
	ClasesListService clasesListService;
	@Autowired
	ClassSubjectService classSubjectService ;
	@Autowired
	ProfessorListAjaxService professorListAjaxService;
	@Autowired
	CoureseProfessorListService coureseProfessorListService;
	@Autowired
	CoureseProfessorCountService coureseProfessorCountService;
	@Autowired
	CoureseProfessorDeleteService coureseProfessorDeleteService;
	
	
	@RequestMapping("professorList")
	public String professorList(@RequestParam(value = "subjectNum")String subjectNum, Model model) {
		professorListAjaxService.execute(subjectNum , model );
		
		return "thymeleaf/courses/professorList";
	}
		
	@RequestMapping("classSubject")
	public String classSubject(@RequestParam(value = "departmentNum") String departmentNum , HttpSession session , Model model) {
		classSubjectService.execute(departmentNum , session , model);
		
		return "thymeleaf/courses/classSubject";
	}
	
	@RequestMapping("coursesCheck")
	public String courses(Model model , HttpSession session) {
		clasesListService.execute(model);
		coureseProfessorListService.execute(session ,model );
		coureseProfessorCountService.execute(session , model);
		return "thymeleaf/courses/coursesList";
	}
	@RequestMapping("couSubDelete")
	public String couSubDelete(CoursesSubjectCommand coursesSubjectCommand) {
		coureseProfessorDeleteService.execute(coursesSubjectCommand);
		return "redirect:coursesCheck";
	}
	
	
}
