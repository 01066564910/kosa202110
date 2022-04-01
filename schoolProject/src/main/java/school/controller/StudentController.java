package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import school.command.StudentCommand;
import school.service.check.StudentEmailCkService;
import school.service.check.StudentIdCkService;
import school.service.department.DepartmentListService;
import school.service.student.StudentDelService;
import school.service.student.StudentDetailService;
import school.service.student.StudentListService;
import school.service.student.StudentMaxNumService;
import school.service.student.StudentWriteService;
import school.service.student.StudentUpdateService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	StudentMaxNumService studentMaxNumService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	StudentIdCkService studentIdCkService;
	@Autowired
	StudentWriteService studentWriteService;
	@Autowired
	StudentListService studentListService;
	@Autowired
	StudentDetailService studentDetailService;
	@Autowired
	StudentUpdateService studentupdateService;
	@Autowired
	StudentDelService studentDelService;
	@Autowired
	StudentEmailCkService studentEmailCkService;
	
	@RequestMapping(value = "studentDel" , method = RequestMethod.GET)
	public String studentDel(@RequestParam(value = "num")String num) {
		studentDelService.execute(num);
		return "redirect:studentList";
		
	}
	
	@RequestMapping(value = "studentUpdate" ,method = RequestMethod.POST)
	public String studentUpdate(@Validated StudentCommand  studentCommand ,BindingResult result , Model model) {
		String i = studentIdCkService.execute(studentCommand.getStudentId());
		if(i =="1") {
			departmentListService.execute(model);
			return "student/studentUpdate";
		}
		
		
		if(result.hasErrors()) {
			departmentListService.execute(model);
			studentMaxNumService.execute(studentCommand.getStudentNum() , model);
			return "student/studentUpdate";
		}
		
		studentupdateService.execute(studentCommand);
		return "redirect:studentList";
	}
	
	
	@RequestMapping(value = "studentUpdate" , method = RequestMethod.GET)
	public String studentUpdate(@RequestParam(value = "studentId")String studentId ,
				@RequestParam(value = "studentNum")String studentNum,	Model model) {
		departmentListService.execute(model);
		
		studentDetailService.execute(studentId, model);
		return "student/studentUpdate";
	}
	
	
	@RequestMapping("studentDetail")
	public String studentDetail(@RequestParam(value = "studentId")String studentId , Model model) {
		studentDetailService.execute(studentId , model);
		return "student/studentDetail";
	}
	
	@RequestMapping("studentList")
	public String studentList(Model model) {
		studentListService.execute(model);
		return "student/studentList";
	}
	@RequestMapping(value = "studentWrite" ,method = RequestMethod.GET)
	public String studentWrite(StudentCommand  studentCommand , Model model) {
		departmentListService.execute(model);                                  
		
		return "student/studentWrite";
	}
	@RequestMapping(value = "studentWrite" ,method = RequestMethod.POST)
	public String studentWrite1(@Validated StudentCommand  studentCommand ,BindingResult result , Model model) {
		String i = studentIdCkService.execute(studentCommand.getStudentId());
		if(i =="1") {
			departmentListService.execute(model);
			return "student/studentWrite";
		}
		String ii = studentEmailCkService.execute(studentCommand.getStudentEmail());
		if(ii =="1") {
			departmentListService.execute(model);
			return "student/studentUpdate";
		}
		if(result.hasErrors()) {
			departmentListService.execute(model);
			studentMaxNumService.execute(studentCommand.getStudentNum() , model);
			return "student/studentWrite";
		}
		if(!studentCommand.getStudentPw().equals(studentCommand.getStudentPwCon())) {
			departmentListService.execute(model);
			result.rejectValue("studentPw", "studentCommand.studentPw", "비밀번호와 비밀번호 확인이 다릅니다.");
		}
		studentWriteService.execute(studentCommand);
		return "redirect:studentList";
	}
	@RequestMapping("studentNum")
	public String studentNum(@RequestParam("studentNum")String studentNum , Model model) {
		studentMaxNumService.execute(studentNum , model);
		return "student/studentMaxNum";
	}
}
