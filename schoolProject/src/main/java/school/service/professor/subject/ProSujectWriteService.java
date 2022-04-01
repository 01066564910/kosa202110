package school.service.professor.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.ProfessorSubjectCommand;
import school.domain.ProfessorSubjectDTO;
import school.mapper.ProfessorSubjectMapper;

@Service
public class ProSujectWriteService {
	@Autowired
	ProfessorSubjectMapper professorSubjectMapper;
	public void execute(ProfessorSubjectCommand professorSubjectCommand) {
		ProfessorSubjectDTO dto = new ProfessorSubjectDTO();
		dto.setProfessorNum(professorSubjectCommand.getProfessorNum());
		dto.setSubjectNum(professorSubjectCommand.getSubjectNum());
		professorSubjectMapper.setProSubInsert(dto);
	}
	
}
