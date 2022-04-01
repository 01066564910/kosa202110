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
public class MemberShipUpdateService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(MemberCommand memberCommand, BindingResult result, HttpSession session) {
		String path = "thymeleaf/memberShip/memberUpdate";
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(memberCommand.getMemberPw(), authInfo.getUserPw())) {
			MemberDTO dto = new MemberDTO();
			dto.setGender(memberCommand.getGender());
			dto.setMemberAddr(memberCommand.getMemberAddr());
			dto.setMemberBirth(memberCommand.getMemberBirth());
			dto.setMemberEmail(memberCommand.getMemberEmail());
			dto.setMemberId(memberCommand.getMemberId());
			dto.setMemberName(memberCommand.getMemberName());
			dto.setMemberNum(memberCommand.getMemberNum());
			dto.setMemberPhone(memberCommand.getMemberPhone());
			
		path="redirect:memberInfo";
			
			memberMapper.myPageMemberUpdate(dto);
		}
		
		
		
		return path;
		
	}
	
}
