package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.LoginMapper;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	LoginMapper loginMapper;
	public void execute( MemberCommand memberCommand, BindingResult result) {
		
		
		
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setMemberRegist(memberCommand.getMemberRegist());
		dto.setMemberNum(memberCommand.getMemberNum());
		
		
		memberMapper.memberUpdate(dto);
		
		
		
	}
	
}
