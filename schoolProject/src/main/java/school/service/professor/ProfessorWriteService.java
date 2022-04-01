package school.service.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import school.command.ProfessorCommand;
import school.domain.ProfessorDTO;
import school.mapper.ProfessorMapper;

@Service
public class ProfessorWriteService {
	@Autowired
	ProfessorMapper professorMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(ProfessorCommand professorCommand) {
		ProfessorDTO dto = new ProfessorDTO();
		dto.setDepartmentNum(professorCommand.getDepartmentNum());
		dto.setProfessorEmail(professorCommand.getProfessorEmail());
		dto.setProfessorId(professorCommand.getProfessorId());
		dto.setProfessorName(professorCommand.getProfessorName());
		dto.setProfessorNum(professorCommand.getProfessorNum());
		dto.setProfessorPhone(professorCommand.getProfessorPhone());
		String pw = passwordEncoder.encode(professorCommand.getProfessorPw());
		dto.setProfessorPw(pw);
		professorMapper.setProInsert(dto);
	}
	
}
