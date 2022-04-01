package kosaShoppingMall.service.EmpMyPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.NewPwCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeMyPagePwService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute( NewPwCommand newPwCommand , BindingResult result, HttpSession session) {
		
		String path="redirect:empDetail";
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		if(newPwCommand.getNewPw().equals(newPwCommand.getNewPwCon())) {
			EmployeeDTO dto = new EmployeeDTO();
			
			String newEmpPw = passwordEncoder.encode(newPwCommand.getNewPw());
			dto.setEmpId(authInfo.getUserId());
			System.out.println(newEmpPw);
			dto.setEmpPw(newEmpPw);
			employeeMapper.employeePwChageOk(dto);
			authInfo.setUserPw(newEmpPw);
		}else {
		
			result.rejectValue("newEmpPw", "newPwCommand.newEmpPw", "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			path="thymeleaf/employeeShip/empShipPw";
		}
		
		
		return path;
	}
	
}
