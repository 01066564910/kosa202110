package school.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import school.command.LoginCommand;
import school.domain.AuthInfo;
import school.mapper.CkMapper;

@Service
public class LoginService {
	@Autowired
	CkMapper ckMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(LoginCommand loginCommand, BindingResult result, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		AuthInfo authInfo =  ckMapper.setLogin(loginCommand.getUserId());
		
		if(authInfo == null) {
			result.rejectValue("userId", "loginCommand.userId", "유저아이디가 없습니다.");
			return "thymeleaf/index";
		}
		else if(!passwordEncoder.matches(loginCommand.getUserPw(),authInfo.getUserPw() )) {
			result.rejectValue("userPw", "loginCommand.userPw", "유저비밀번호가 다릅니다.");
			return  "thymeleaf/index";
		}else {
			session.setAttribute("authInfo", authInfo);
		}
		return "thymeleaf/index";
		
	}
	
}
