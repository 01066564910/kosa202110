package school.service.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.ProfessorDTO;
import school.mapper.DepartmentMapper;

@Service
public class ProfessorListAjaxService {
	@Autowired
	DepartmentMapper departmentMapper;
	
	public void execute(String subjectNum , Model model) {
		List<ProfessorDTO> list = departmentMapper.selectProfessor(subjectNum);
		model.addAttribute("list" , list);
	}
}
