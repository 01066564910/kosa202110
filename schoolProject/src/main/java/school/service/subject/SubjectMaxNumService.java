package school.service.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.mapper.SubjectMapper;

@Service
public class SubjectMaxNumService {
	@Autowired
	SubjectMapper subjectMapper;
	public void execute(Model model) {
		String max = subjectMapper.setSjMaxNum();
		model.addAttribute("subjectCommand" ,  max);
		
	}

}
