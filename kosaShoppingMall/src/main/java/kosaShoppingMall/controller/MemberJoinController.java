package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.service.EmailCheckService;
import kosaShoppingMall.service.IdcheckService;
import kosaShoppingMall.service.memberjoin.MeberMailService;
import kosaShoppingMall.service.memberjoin.MemberWriterService;

@Controller
@RequestMapping("register")
public class MemberJoinController {
	@Autowired
	MemberWriterService memberWriterService;
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	MeberMailService meberMailService; 
	@ModelAttribute
	MemberCommand setMemberCommand() {
		return new MemberCommand();
	}
	
	
	
	@RequestMapping("memberMail")
	public String memberMail(@RequestParam(value="num")String num ,
			@RequestParam(value = "reciver")String reciver ,
			@RequestParam(value = "userId")String userId) {
		Integer i = meberMailService.execute(num , reciver , userId);
		if(i >0) {
			return "thymeleaf/memberShip/success";
		}else {
			return "thymeleaf/memberShip/fail";
		}
		
	}
	
	
	
	
	@RequestMapping(value = "memberJoinAction", method = RequestMethod.GET )
	public String memberJoinAction() {
		return "redirect:/register/agree";
	}
	
	@RequestMapping(value = "memberJoinAction", method = RequestMethod.POST)
	public String memberJoinAction(@Validated MemberCommand memberCommand, BindingResult result , Model model) {

		if (result.hasErrors()) {
			return "thymeleaf/memberShip/memberJoinForm";
		}
		if (!memberCommand.isMemPwEqulsIsMemberPwCon() && memberCommand.getMemberPw() != null) {
			// html 네임 커맨드에서 받아오는 값 내가 출력할 값
			result.rejectValue("memberPw", "memberCommand.memberPw()", "비밀번호 또는 비밀번호 확인이 틀렸습니다.");
			return "thymeleaf/memberShip/memberJoinForm";
		}
		Integer i = idcheckService.execute(memberCommand.getMemberId());
		if(i == 1) {
			result.rejectValue("memberId", "memberCommand.memberId", "중복 아이디입니다.");
			return "thymeleaf/memberShip/memberJoinForm";
		}
		i = emailCheckService.execute(memberCommand.getMemberEmail());
		if(i == 1) {
			result.rejectValue("memberEmail", "memberCommand.memberEmail", "중복된 이메일입니다.");
			return "thymeleaf/memberShip/memberJoinForm";
		}
		
		memberWriterService.execute(memberCommand , model);
		return "thymeleaf/memberShip/welcome";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regist(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) {

		if (agree == false) {
			return "thymeleaf/memberShip/agree";
		}
		return "thymeleaf/memberShip/memberJoinForm";
		
	}

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String agree1() {
		return "thymeleaf/memberShip/agree";
	}

	@RequestMapping(value = "agree", method = RequestMethod.GET)
	public String agree() {
		return "thymeleaf/memberShip/agree";
	}

}
