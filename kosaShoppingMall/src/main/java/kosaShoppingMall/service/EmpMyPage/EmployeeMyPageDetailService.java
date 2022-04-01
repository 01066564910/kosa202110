package kosaShoppingMall.service.EmpMyPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeMyPageDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		EmployeeDTO empDto =  employeeMapper.employeeSelectOne(authInfo.getUserId());
		model.addAttribute("employeeCommand", empDto);
	}
	
}
