package school.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.StudentDTO;
import school.mapper.StudentMapper;
@Service
public class StudentDetailService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(String studentId, Model model) {
		StudentDTO dto = studentMapper.getStudentSelectOne(studentId);
		model.addAttribute("studentCommand" , dto);
	}

}
