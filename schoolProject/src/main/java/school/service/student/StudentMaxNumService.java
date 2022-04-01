package school.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.mapper.StudentMapper;

@Service
public class StudentMaxNumService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(String studentNum , Model model) {
		String i = studentMapper.setStudentMaxNum(studentNum); 
		System.out.println(i);
		model.addAttribute("i" , i);
	}
	
}
