package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.mapper.MemberMapper;
@Component
@Service
public class MemberMaxNumService {
	@Autowired
	MemberMapper mebmerMapper;
	public void execute(MemberCommand memberCommand , Model model) {

		String memberNum = mebmerMapper.memberMaxNum();
		// jsp방식
		// model.addAttribute("memberNum"  ,memberNum );
		// 스프링 부트 방식
		 memberCommand.setMemberNum(memberNum);
	}

}
