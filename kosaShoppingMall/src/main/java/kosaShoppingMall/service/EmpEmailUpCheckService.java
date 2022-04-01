package kosaShoppingMall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.mapper.LoginMapper;

@Service
public class EmpEmailUpCheckService {
	@Autowired
	LoginMapper loginMapper;

	public Integer  execute(String empEmail, String empId) {
		AuthInfo authInfo = loginMapper.findId(empEmail);
		if (authInfo == null) {
			return 0;
		} else if (authInfo.getUserId().equals(empId)
				&& empEmail.equals(authInfo.getEmail())) {
			return 0;
		} else if (authInfo != null) {
			return 1;
		} else {
			return 0;
		}
	}
}