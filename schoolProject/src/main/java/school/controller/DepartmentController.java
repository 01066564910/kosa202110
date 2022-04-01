package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import school.command.DepartmentCommand;
import school.service.department.DepartmentDelService;
import school.service.department.DepartmentListPageService;
import school.service.department.DepartmentListService;
import school.service.department.DepartmentMaxService;
import school.service.department.DepartmentModifyService;
import school.service.department.DepartmentUpdateService;
import school.service.department.DepartmentWriteService;

@Controller
@RequestMapping("major")
public class DepartmentController {
	@Autowired
	DepartmentMaxService departmentMaxService;
	@Autowired
	DepartmentWriteService departmentWriteService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	DepartmentUpdateService departmentUpdateService;
	@Autowired
	DepartmentModifyService departmentModifyService;
	@Autowired
	DepartmentDelService departmentDelService;
	@Autowired
	DepartmentListPageService departmentListPageService;
	
	@RequestMapping(value = "departmentDel")
	public String departmentDel(@RequestParam(value = "num")String departmentNum) {
		departmentDelService.execute(departmentNum);
		return "redirect:majorList";
	}
	
	@RequestMapping(value = "departmentUpdate" , method = RequestMethod.POST)
	public String departmentUpdate1(DepartmentCommand departmentCommand){
		departmentModifyService.execute(departmentCommand);
		return "redirect:majorList";
	}
	
	@RequestMapping(value = "departmentUpdate/{num}" , method = RequestMethod.GET)
	public String departmentUpdate(@PathVariable(value = "num")String departmentNum,Model model ) {
		departmentUpdateService.execute(departmentNum , model);
		return "thymeleaf/major/departmentUpdate";
		
	}
	
	@RequestMapping(value =  "departmentWrite" , method = RequestMethod.POST)
	public String departmentWrite(@Validated DepartmentCommand  departmentCommand , BindingResult result , Model model) {
		if(result.hasErrors()) {
			departmentMaxService.execute(model);
			return "thymeleaf/major/departmentWrite";
		}
		departmentWriteService.execute(departmentCommand );
		return "redirect:majorList";
	}
	
	@RequestMapping(value = "departmentWrite" , method = RequestMethod.GET)
	public String departmentWrite( DepartmentCommand departmentCommand ,  Model model ) {
		
		departmentMaxService.execute(model);
		return "thymeleaf/major/departmentWrite";
	}
	
	@RequestMapping("majorList")
	public String majorList(
			@RequestParam(value = "searchWord" , required = false)String searchWord,
			@RequestParam(value="page" , defaultValue = "1" , required = false) Integer Page ,	Model model) {
		departmentListPageService.execute(Page ,model , searchWord);
		return "thymeleaf/major/majorList";
	}
	


	
}
