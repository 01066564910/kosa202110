package school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.service.check.StudentEmailCkService;
import school.service.check.StudentEmailUpdateCkService;
import school.service.check.StudentIdCkService;

@RestController
public class CheckController {
	@Autowired
	StudentIdCkService studentIdCkService;
	@Autowired
	StudentEmailCkService studentEmailCkService;
	
	@Autowired
	StudentEmailUpdateCkService studentEmailUpdateCkService;
	
	// 교수 마이페이지
	// 교수 업데이트 페이지
	
	@RequestMapping("/professor/professorUpdateEmailCk")
	public String professorUpdateEmailCk(@RequestParam(value = "professorEmailCk")String professorEmail , HttpSession session) {
		String i =	studentEmailUpdateCkService.execute(professorEmail , session);
		if(i=="0") {
			return "사용 가능한 이메일입니다.";
		}else {
			return "사용할 수 없는 이메일입니다.";
		}
	}
	// 교수 페이지
	@RequestMapping("/professor/professorEmailCk")
	public String professorEmailCk(@RequestParam(value = "professorEmailCk")String professorEmail) {
		String i =	studentEmailCkService.execute(professorEmail);
		if(i=="0") {
			return "사용 가능한 이메일입니다.";
		}else {
			return "사용할 수 없는 이메일입니다.";
		}
	}
	
	
	@RequestMapping("/professor/professorIdCk")
	public  String professorIdCk(@RequestParam(value = "professorId")String professorId) {
		String i =	studentIdCkService.execute(professorId);
		if(i=="0") {
			return "사용 가능한 아이디입니다.";
		}else {
			return "사용할 수 없는 아이디입니다.";
		}
	}
	
	
	// 학생 마이페이지
	
	@RequestMapping("/studentMypage/studentMyIdCk")
	public  String studentIdCk1(@RequestParam(value = "studentId")String studentId) {
		String i =	studentIdCkService.execute(studentId);
		if(i=="0") {
			return "사용 가능한 아이디입니다.";
		}else {
			return "사용할 수 없는 아이디입니다.";
		}
	}
	
	@RequestMapping("/studentMypage/studentMyUpEmailCk")
	public String studentMyUpEmailCk(@RequestParam(value = "studentEmail")String studentEmail , HttpSession session) {
		String i =	studentEmailUpdateCkService.execute(studentEmail , session);
		if(i=="0") {
			return "사용 가능한 이메일입니다.";
		}else {
			return "사용할 수 없는 이메일입니다.";
		}
	}
	
	// update
	
	
	@RequestMapping("/student/studentUpEmailCk")
	public String studentEmail1(@RequestParam(value = "studentEmail")String studentEmail , HttpSession session) {
		String i =	studentEmailUpdateCkService.execute(studentEmail , session);
		if(i=="0") {
			return "사용 가능한 이메일입니다.";
		}else {
			return "사용할 수 없는 이메일입니다.";
		}
	}
	
	
	// write
	@RequestMapping("/student/studentEmailCk")
	public String studentEmail(@RequestParam(value = "studentEmail")String studentEmail) {
		String i =	studentEmailCkService.execute(studentEmail);
		if(i=="0") {
			return "사용 가능한 이메일입니다.";
		}else {
			return "사용할 수 없는 이메일입니다.";
		}
	}
	
	
	@RequestMapping("/student/studentIdCk")
	public  String studentIdCk(@RequestParam(value = "studentId")String studentId) {
		String i =	studentIdCkService.execute(studentId);
		if(i=="0") {
			return "사용 가능한 아이디입니다.";
		}else {
			return "사용할 수 없는 아이디입니다.";
		}
	}
}
