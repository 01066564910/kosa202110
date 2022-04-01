package kosaShoppingMall.service.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.EmployeeMapper;

@Service
public class EmployeeDelService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void exeute(String[] delete) {
		
		employeeMapper.employeedel(delete);
		
	}

}
