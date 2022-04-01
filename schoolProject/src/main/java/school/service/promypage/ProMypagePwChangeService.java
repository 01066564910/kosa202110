package school.service.promypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import school.command.PwCommand;
import school.domain.AuthInfo;
import school.domain.ProfessorDTO;
import school.mapper.ProfessorMapper;

@Service
public class ProMypagePwChangeService {
	@Autowired
	ProfessorMapper professorMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(PwCommand pwCommand, BindingResult result , HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if(!passwordEncoder.matches(pwCommand.getOldPw(), authInfo.getUserPw())) {
			result.rejectValue("oldPw", "pwCommand.oldPw", "기존 비밀번호가 다릅니다");
			return "professorMypage/proMypagePw";
		}else if(!pwCommand.getNewPw().equals(pwCommand.getNewPwCon())) {
			result.rejectValue("newPw", "pwCommand.newPw", "비밀번호와 비밀번호 확인이 다릅니다");
			return "professorMypage/proMypagePw";
		}else {
			
			ProfessorDTO dto = new ProfessorDTO();
			dto.setProfessorPw(passwordEncoder.encode(pwCommand.getNewPw()));
			dto.setProfessorId(authInfo.getUserId());
			professorMapper.setPwChange(dto);
			authInfo.setUserPw(dto.getProfessorPw());
			return "redirect:proMypage";
		}
		
	}
	
}
