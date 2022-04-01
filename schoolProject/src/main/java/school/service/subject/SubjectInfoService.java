package school.service.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.SubjectDTO;
import school.mapper.SubjectMapper;

@Service
public class SubjectInfoService {
	@Autowired
	SubjectMapper subjectMapper;
	public void execute(String subjectNum , Model model) {
		SubjectDTO dto = subjectMapper.getSjSelectOne(subjectNum);
		model.addAttribute("SubjectCommand" , dto);
	}
	
}
