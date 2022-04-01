
package kosaShoppingMall.service.myPage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberMyPagePasswordService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	public String execute(String newMemberPw, String newMemberPwCon, BindingResult result, MemberCommand memberCommand,  HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String path = "redirect:/";
		if(passwordEncoder.matches(memberCommand.getMemberPw(), authInfo.getUserPw())) {
			if(newMemberPw.equals(newMemberPwCon)) {
				String memberPw = passwordEncoder.encode(newMemberPw);
				MemberDTO dto = new MemberDTO();
				dto.setMemberPw(memberPw);
				dto.setMemberId(authInfo.getUserId());
				 memberMapper.myPageMemberPw(dto);
			}
		}else{
			result.rejectValue("newMemberPw", "memberCommand.newMemberPw", "비밀번호가 다릅니다.");
			path = "thymeleaf/memberShip/memberPw";
		}
		return path;
		
	}
	
}