package school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import school.command.CoursesSubjectCommand;
import school.service.courses.CoursesSubjectInsertService;

@RestController
public class RestController1 {
	@Autowired
	CoursesSubjectInsertService coursesSubjectInsertService;
	@RequestMapping(value =  "/courses/coursesSubject" , method = RequestMethod.POST)
	public String coursesSubject(CoursesSubjectCommand coursesSubjectCommand , HttpSession session) {
		String i= coursesSubjectInsertService.execute(coursesSubjectCommand , session);
		return i;
	}
}
