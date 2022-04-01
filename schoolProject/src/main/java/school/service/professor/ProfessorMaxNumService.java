package school.service.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.ProfessorCommand;
import school.mapper.ProfessorMapper;

@Service
public class ProfessorMaxNumService {
	@Autowired
	ProfessorMapper professorMapper;
	public void execute(ProfessorCommand professorCommand) {
		String maxNum =  professorMapper.setProMaxNum();
		professorCommand.setProfessorNum(maxNum);
		
	}

}
