package school.service.courses;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.AuthInfo;
import school.domain.CoursesSubjectDTO;
import school.mapper.CoursesSubjectMapper;
import school.mapper.StudentMapper;

@Service
public class CoureseProfessorListService {
	@Autowired
	CoursesSubjectMapper coursesSubjectMapper;
	@Autowired
	StudentMapper studentMapper;
	public void execute(HttpSession session, Model model) {
		try {
			AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
			String studentNum = studentMapper.getStudentSelectOne(authInfo.getUserId()).getStudentNum();
			System.out.println("asdfsdafsadfdsadasdsaffdsa");
			List<CoursesSubjectDTO> list = coursesSubjectMapper.getCoursesSubjectSelectAll(studentNum);
			model.addAttribute("cSList" , list);
		}catch(Exception e) {
			model.addAttribute("aa" , "10");
		}
		
	} 
	
}
