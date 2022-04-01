package kosaShoppingMall.service.myPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.command.NewPwCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberMyPageDelService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(NewPwCommand newPwCommand, BindingResult result, HttpSession session ) {
		String path ="redirect:/";
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO dto = memberMapper.myMemberSelectOne(authInfo.getUserId());
		if(passwordEncoder.matches(newPwCommand.getOldPw(),authInfo.getUserPw())) {
			memberMapper.memberDeletes(authInfo.getUserId());
		}else {
			result.rejectValue("oldPw", "newPwCommand.oldPw", "비밀번호가 틀렸습니다.");
			path = "thymeleaf/memberShip/memberDel";
		}
		return path;
	}
	
	
}
