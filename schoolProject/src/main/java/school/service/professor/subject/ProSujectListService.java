package school.service.professor.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.ProfessorSubjectDTO;
import school.mapper.ProfessorSubjectMapper;

@Service
public class ProSujectListService {
	@Autowired
	ProfessorSubjectMapper professorSubjectMapper;
	public void execute(Model model) {
		List<ProfessorSubjectDTO> list = professorSubjectMapper.getProSubSelectAll();
		model.addAttribute("list" , list);
	}

}
