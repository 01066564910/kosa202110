package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import school.command.SubjectCommand;
import school.service.subject.SubjectDelService;
import school.service.subject.SubjectInfoService;
import school.service.subject.SubjectListService;
import school.service.subject.SubjectMaxNumService;
import school.service.subject.SubjectUpdateService;
import school.service.subject.SubjectWriteService;

@Controller
@RequestMapping(value = "subject")
public class SubjectController {
	@Autowired
	SubjectMaxNumService subjectMaxNumService;
	@Autowired
	SubjectWriteService subjectWriteService;
	@Autowired
	SubjectListService subjectListService;
	@Autowired
	SubjectInfoService subjectInfoService;
	@Autowired
	SubjectDelService subjectDelService;
	@Autowired
	SubjectUpdateService subjectUpdateService;
	
	@RequestMapping(value = "subjectUpdate" , method = RequestMethod.POST)
	public String subjectUpdate(SubjectCommand subjectCommand) {
		subjectUpdateService.execute(subjectCommand);
		return "redirect:subList";
	}
	@RequestMapping(value = "subjectUpdate" , method = RequestMethod.GET)
	public String subjectUpdate(@RequestParam(value = "num")String subjectNum ,Model model) {
		subjectInfoService.execute(subjectNum , model);
		return  "subject/subjectUpdate";
	}
	
	@RequestMapping(value = "subjectDelete")
	public String subjectDelete(@RequestParam(value = "num")String num) {
		subjectDelService.execute(num);
		return "redirect:subList";
	}
	
	@RequestMapping(value = "subcjectDetail")
	public String subcjectDetail(@RequestParam(value = "num")String subjectNum , Model model) {
		subjectInfoService.execute(subjectNum , model);
		return "subject/subInfo";
	}
	
	@RequestMapping(value = "subjectWrite" , method = RequestMethod.POST)
	public String subjectWrite1( SubjectCommand subjectCommand ) {
		
		
			
		
		subjectWriteService.execute(subjectCommand);
		return "redirect:subList";
	}
	
	@RequestMapping(value = "subjectWrite" , method = RequestMethod.GET)
	public String subjectWrite(Model model) {
		subjectMaxNumService.execute(model);
		return "subject/subjectWrite";
	}
	
	
	
	@RequestMapping(value = "subList" , method =RequestMethod.GET )
	public String  subList(Model model) {
		subjectListService.execute(model);
		return "subject/subList";
	}
	
		
	
}
