package school.service.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.CoursesSubjectCommand;
import school.domain.CoursesSubjectDTO;
import school.mapper.CoursesSubjectMapper;

@Service
public class CoureseProfessorDeleteService {
	@Autowired
	CoursesSubjectMapper coursesSubjectMapper;
	public void execute(CoursesSubjectCommand coursesSubjectCommand) {
		CoursesSubjectDTO dto = new CoursesSubjectDTO();
		dto.setStudentNum(coursesSubjectCommand.getStudentNum());
		dto.setSubjectNum(coursesSubjectCommand.getSubjectNum());
		dto.setProfessorNum(coursesSubjectCommand.getProfessorNum());
		coursesSubjectMapper.setCSDelete(dto);
	}
	
}
