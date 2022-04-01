package school.service.promypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import school.domain.AuthInfo;

@Service
public class ProfessorPwService {
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(String pw, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(!passwordEncoder.matches(pw, authInfo.getUserPw())){
			return  "professorMypage/proMypagePwCon";
		}else {
			return  "professorMypage/proMypageUpdate";
		}
		
	}
	
}
