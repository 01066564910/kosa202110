package school.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.StudentDTO;
import school.mapper.StudentMapper;

@Service
public class StudentListService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(Model model) {
		List<StudentDTO> list =  studentMapper.getSdSelectAll();
		model.addAttribute("list" , list);
		
	}
	
}
