package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.service.EmailCheckService;
import kosaShoppingMall.service.IdcheckService;
import kosaShoppingMall.service.MemEmailcheckService;
import kosaShoppingMall.service.member.MemberDelService;
import kosaShoppingMall.service.member.MemberDeleteService;
import kosaShoppingMall.service.member.MemberDetailService;
import kosaShoppingMall.service.member.MemberListService;
import kosaShoppingMall.service.member.MemberMaxNumService;
import kosaShoppingMall.service.member.MemberUpdateService;
import kosaShoppingMall.service.member.MemberWriteService;
import kosaShoppingMall.service.memberjoin.MeberMailService;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	MemberMaxNumService memberMaxNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	@Autowired
	MemberDelService memberDelService;
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	MemEmailcheckService memEmailcheckService;
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
	

	@RequestMapping(value = "memDel", method = RequestMethod.POST)
	public String memDel(@RequestParam(value = "delete") String[] delete) {
		memberDelService.execute(delete);
		return "redirect:memberList";
	}

	@RequestMapping(value = "memberDelete", method = RequestMethod.POST)
	public String memberDelete(@RequestParam(value = "memberNum") String memberNum, Model model) {
		memberDeleteService.execute(memberNum, model);
		return "thymeleaf/member/memberDel";
		// return "member/memberDel";
	}

	@RequestMapping(value = "memberModify", method = RequestMethod.POST)
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberUpdate";
			// return "member/memberUpdate";
		}
	
		Integer i = memEmailcheckService.execute(memberCommand.getMemberEmail(),memberCommand.getMemberId());
		if (i == 1) {
			result.rejectValue("memberEmail", "memberCommand.memberEmail", "중복된 이메일입니다.");
			return "thymeleaf/member/memberUpdate";
//			return "member/memberForm";
		}
		memberUpdateService.execute(memberCommand, result);
		return "redirect:memberDetail/" + memberCommand.getMemberNum();
		
	}

	@RequestMapping(value = "memberModify", method = RequestMethod.GET)
	public String memberModify(@RequestParam(value = "memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
		// return "member/memberUpdate";
	}

	@RequestMapping("memberDetail/{memberNum}")
	public String memberDetail(@PathVariable(value = "memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
		// return "member/memberDetail";
	}

	@RequestMapping(value = "memberRegist", method = RequestMethod.POST)
	public String memberRegist(@Validated MemberCommand memberCommand, BindingResult result) {

		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
			// return "member/memberForm";
		}

		if (!memberCommand.isMemPwEqulsIsMemberPwCon()) {
			result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호 확인이 다릅니다.");
			return "thymeleaf/member/memberForm";
			// return "member/memberForm";
		}
		Integer i = idcheckService.execute(memberCommand.getMemberId());
		if (i == 1) {
			result.rejectValue("memberId", "memberCommand.memberId", "중복 아이디입니다.");
			return "thymeleaf/member/memberForm";
//			return "member/memberForm";
		}
		i = emailCheckService.execute(memberCommand.getMemberEmail());
		if (i == 1) {
			result.rejectValue("memberEmail", "memberCommand.memberEmail", "중복된 이메일입니다.");
			return "thymeleaf/member/memberForm";
//			return "member/memberForm";
		}
		memberWriteService.execute(memberCommand);
		return "redirect:memberList";

	}

	@RequestMapping(value = "memberRegist", method = RequestMethod.GET)
	public String memberRegist(MemberCommand memberCommand, Model model) {
		memberMaxNumService.execute(memberCommand, model);
		return "thymeleaf/member/memberForm";
		// return "member/memberForm";
	}

	@RequestMapping("memberList") // 페이지 기본값 쿼리스트링이 없어도 된다. 기본적으로 1 페이지가 되는거고.
	public String memberList(
			@RequestParam(value = "memberSearch" , required = false)String memberSearch ,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
			Model model) {
		memberListService.execute(model, page ,memberSearch);
		 return "thymeleaf/member/memberList";
		//return "member/memberList";
	}

}
