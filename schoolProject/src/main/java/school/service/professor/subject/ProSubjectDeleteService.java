package school.service.professor.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.command.ProfessorSubjectCommand;
import school.domain.ProfessorSubjectDTO;
import school.mapper.ProfessorSubjectMapper;

@Service
public class ProSubjectDeleteService {
	@Autowired
	ProfessorSubjectMapper professorSubjectMapper;
	public void execute(ProfessorSubjectCommand professorSubjectCommand , Model model) {
		ProfessorSubjectDTO dto = new ProfessorSubjectDTO();
		dto.setProfessorNum(professorSubjectCommand.getProfessorNum());
		dto.setSubjectNum(professorSubjectCommand.getSubjectNum());
		Integer i;
		String a;
		try { 
			 i = professorSubjectMapper.setPSDelete(dto);
			 a = i.toString();
		}catch (Exception e) {
			a = "4";
		}
		
		model.addAttribute("a" , a);
	}
	
}
