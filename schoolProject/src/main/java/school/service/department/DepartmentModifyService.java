package school.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.DepartmentCommand;
import school.domain.DepartmentDTO;
import school.mapper.DepartmentMapper;

@Service
public class DepartmentModifyService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(DepartmentCommand departmentCommand) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentName(departmentCommand.getDepartmentName());
		dto.setDepartmentNum(departmentCommand.getDepartmentNum());
		dto.setDepartmentPhone(departmentCommand.getDepartmentPhone());
		dto.setDepatymentAddr(departmentCommand.getDepatymentAddr());
		departmentMapper.setDepartmentUpdate(dto);
	}
	
}
