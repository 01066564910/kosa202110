package school.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.mapper.DepartmentMapper;

@Service
public class DepartmentMaxService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(Model model) {
		String Maxnum =  departmentMapper.departmentMaxNum();
		model.addAttribute("departmentNum" ,  Maxnum);
	}
}
