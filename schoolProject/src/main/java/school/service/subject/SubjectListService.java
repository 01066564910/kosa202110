package school.service.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.SubjectDTO;
import school.mapper.SubjectMapper;

@Service
public class SubjectListService {
	@Autowired
	SubjectMapper subjectMapper;
	public void execute(Model model) {
		List<SubjectDTO> list =  subjectMapper.getSjSelect();
		model.addAttribute("list" , list);
	}

	
}
