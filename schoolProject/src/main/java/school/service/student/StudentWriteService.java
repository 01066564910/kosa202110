package school.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import school.command.StudentCommand;
import school.domain.StudentDTO;
import school.mapper.StudentMapper;
@Service
public class StudentWriteService {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(StudentCommand studentCommand) {
		StudentDTO dto = new StudentDTO();
		dto.setDepartmentNum(studentCommand.getDepartmentNum());
		dto.setStudentEmail(studentCommand.getStudentEmail());
		dto.setStudentId(studentCommand.getStudentId());
		dto.setStudentName(studentCommand.getStudentName());
		dto.setStudentNum(studentCommand.getStudentNum());
		dto.setStudentPhone(studentCommand.getStudentPhone());
		String pw = passwordEncoder.encode(studentCommand.getStudentPw());
		dto.setStudentPw(pw);
		studentMapper.setStudentInsert(dto);
	}

}
