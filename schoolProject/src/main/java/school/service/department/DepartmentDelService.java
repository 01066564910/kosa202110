package school.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.mapper.DepartmentMapper;

@Service
public class DepartmentDelService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(String departmentNum) {
		departmentMapper.setDepartmentDelete(departmentNum);
		
	}

}
