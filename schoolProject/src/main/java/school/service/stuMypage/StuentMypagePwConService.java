package school.service.stuMypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import school.command.PwCommand;
import school.domain.AuthInfo;
import school.service.department.DepartmentListService;
import school.service.student.StudentDetailService;

@Service
public class StuentMypagePwConService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	StudentDetailService studentDetailService;
	
	public String execute(PwCommand pwCommand, BindingResult result, Model model , HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if(!passwordEncoder.matches(pwCommand.getOldPw(), authInfo.getUserPw())) {
			result.rejectValue("oldPw", "pwCommand.oldPw", "비밀번호가 다릅니다");
			return "studentMypage/stuMyPw";
		}else {
			departmentListService.execute(model);
			studentDetailService.execute(authInfo.getUserId(), model);
			return "studentMypage/stuMypageUpdate";
		}
	}     

}
