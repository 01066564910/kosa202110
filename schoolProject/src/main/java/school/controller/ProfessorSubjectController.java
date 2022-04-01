package school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import school.command.ProfessorSubjectCommand;
import school.service.professor.subject.ProSubjectDeleteService;
import school.service.professor.subject.ProSujectListService;
import school.service.professor.subject.ProSujectProNumService;
import school.service.professor.subject.ProSujectStateNullService;
import school.service.professor.subject.ProSujectStateService;
import school.service.professor.subject.ProSujectWriteService;
import school.service.subject.SubjectListService;

@Controller
@RequestMapping("proSubject")
public class ProfessorSubjectController {
	@Autowired
	ProSujectProNumService proSujectProNumService;
	@Autowired
	SubjectListService subjectListService;
	@Autowired
	ProSujectWriteService proSujectWriteService;
	@Autowired
	ProSujectListService proSujectListService;
	@Autowired
	ProSujectStateService ProSujectStateService;
	@Autowired
	ProSujectStateNullService proSujectStateNullService;
	@Autowired
	ProSubjectDeleteService proSubjectDeleteService;
	
	
	@RequestMapping("pSDelete")
	public String pSDelete(ProfessorSubjectCommand professorSubjectCommand , Model model ) {
		proSubjectDeleteService.execute(professorSubjectCommand ,model);
		return "professorSubject/proSujectDelete";
	}
	
	@RequestMapping("statusModifyToNull")
	public String statusModifyToNull(ProfessorSubjectCommand professorSubjectCommand) {
		proSujectStateNullService.execute(professorSubjectCommand);
		return "redirect:proSubjectList";
	}
	
	
	@RequestMapping("statusModifyTo1")
	public String statusModifyTo1(ProfessorSubjectCommand professorSubjectCommand  ) {
		ProSujectStateService.execute(professorSubjectCommand );
		return "redirect:proSubjectList";
	}
	
	
	@RequestMapping(value ="proSujectRegist" ,method = RequestMethod.POST)
	public String proSujectRegist(ProfessorSubjectCommand professorSubjectCommand ) {
		proSujectWriteService.execute(professorSubjectCommand);
		return "redirect:proSubjectList";
	}
	
	@RequestMapping(value = "proSujectRegist" , method = RequestMethod.GET)
	public String proSujectRegist(HttpSession session , Model model) {
		subjectListService.execute(model);
		proSujectProNumService.execute(session , model);
		return "professorSubject/proSujectRegist";
	}
	@RequestMapping("proSubjectList")
	public String prosubjectList(Model model) {
		proSujectListService.execute(model);
		return "professorSubject/proSubjectList";
	}
}
