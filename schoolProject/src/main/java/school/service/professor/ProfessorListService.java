package school.service.professor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.ProfessorDTO;
import school.mapper.ProfessorMapper;

@Service
public class ProfessorListService {
	@Autowired
	ProfessorMapper professorMapper;
	public void execute(Model model) {
		List<ProfessorDTO> list = professorMapper.getPfSelectAll();
		model.addAttribute("list" , list);
		
	}
	
}
