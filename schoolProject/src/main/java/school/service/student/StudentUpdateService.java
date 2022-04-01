package school.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.command.StudentCommand;
import school.domain.StudentDTO;
import school.mapper.StudentMapper;
@Service
public class StudentUpdateService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(StudentCommand studentCommand) {
		StudentDTO dto = new StudentDTO();
		dto.setDepartmentNum(studentCommand.getDepartmentNum());
		dto.setStudentEmail(studentCommand.getStudentEmail());
		dto.setStudentId(studentCommand.getStudentId());
		dto.setStudentName(studentCommand.getStudentName());
		dto.setStudentNum(studentCommand.getStudentNum());
		dto.setStudentPhone(studentCommand.getStudentPhone());
		studentMapper.setStudentUpdate(dto);
		
	}
	


}
