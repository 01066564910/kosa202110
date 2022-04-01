package kosaShoppingMall.service.EmpMyPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeMyPageUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(EmployeeCommand employeeCommand, BindingResult result, HttpSession session) {
		String path="thymeleaf/employeeShip/empShipUpdate";
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(employeeCommand.getEmpPw(),authInfo.getUserPw())) {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setEmpId(employeeCommand.getEmpId());
			dto.setEmpAddr(employeeCommand.getEmpAddr());
			dto.setEmpEmail(employeeCommand.getEmpEmail());
			dto.setEmpName(employeeCommand.getEmpName());
			dto.setEmpPhone(employeeCommand.getEmpPhone());
			System.out.println(dto.getEmpAddr());
			System.out.println(dto.getEmpEmail());
			System.out.println(dto.getEmpName());
			System.out.println("jjkvkvkvkvk");
			employeeMapper.employeeUpdate(dto);
			path = "redirect:empDetail";
			
		}else {
			result.rejectValue("newEmpPw","employeeCommand.newEmpPw" , "변경할 비밀번호가 틀렸습니다.");
		}
		
		return path;
	}

}
