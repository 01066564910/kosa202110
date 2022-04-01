package school.service.courses;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.CoursesSubjectCommand;
import school.domain.AuthInfo;
import school.domain.CoursesSubjectDTO;
import school.domain.IntegrityCk;
import school.mapper.CoursesSubjectMapper;
import school.mapper.StudentMapper;

@Service
public class CoursesSubjectInsertService {
	@Autowired
	CoursesSubjectMapper CoursesSubjectMapper;
	@Autowired
	StudentMapper studentMapper;
	public String execute( CoursesSubjectCommand coursesSubjectCommand, HttpSession session) {
		
		try {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String studentNum = studentMapper.getStudentSelectOne(authInfo.getUserId()).getStudentNum();
		CoursesSubjectDTO dto = new CoursesSubjectDTO();
		dto.setStudentNum(studentNum);
		dto.setProfessorNum(coursesSubjectCommand.getProfessorNum());
		dto.setSubjectNum(coursesSubjectCommand.getSubjectNum());
		IntegrityCk integrityCk = CoursesSubjectMapper.getIntegrityCk(dto);
		Integer i;
		if(integrityCk ==null) {
				i = CoursesSubjectMapper.setCSInsert(dto);
		}else {
			i = 5;
		}
		
			return i.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return "10";
		}
	}
}
