package school.service.courses;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.AuthInfo;
import school.mapper.CoursesSubjectMapper;
import school.mapper.StudentMapper;

@Service
public class CoureseProfessorCountService {
	@Autowired
	CoursesSubjectMapper coursesSubjectMapper;
	@Autowired
	StudentMapper studentMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String studentNum = studentMapper.getStudentSelectOne(authInfo.getUserId()).getStudentNum();
		
	}

}
