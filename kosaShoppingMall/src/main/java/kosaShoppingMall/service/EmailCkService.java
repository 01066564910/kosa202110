package kosaShoppingMall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.mapper.LoginMapper;

@Service
public class EmailCkService {
	@Autowired
	LoginMapper loginMapper;
	public Integer execute(EmployeeCommand employeeCommand) {
			AuthInfo authInfo = loginMapper.findId(employeeCommand.getEmpEmail());
			if(authInfo ==null) {
				return 0;
			}else if(authInfo.getUserId().equals(employeeCommand.getEmpId())&&employeeCommand.getEmpEmail().equals(authInfo.getEmail())) {
				return 0;
			}else if(authInfo != null) {
				return 1;
			}else {
				return 0;
			}
		
	}
	
}
