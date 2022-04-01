package kosaShoppingMall.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.FindIdCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.LoginMapper;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class FindIdService {
	@Autowired
	LoginMapper loginMapper;
	public String execute(FindIdCommand findIdCommand, 
			Model model, BindingResult result) {
	
		
		AuthInfo authInfo = loginMapper.findId(findIdCommand.getMemberEmail());
		if(authInfo == null) {
			result.rejectValue("memberEmail", 
					"findIdCommand.memberEmail", "이메일이 틀렸습니다.");
			return "thymeleaf/help/findId";
		
		}else {
			if(authInfo.getPhone().equals(findIdCommand.getMemberPhone())) {
				model.addAttribute("userId" , authInfo.getUserId());
				return "thymeleaf/help/findOk";
			}else {					//필드
				result.rejectValue("memberPhone", 
						"bad");
				// 에러를 커맨드에다가 저장하기 때문에
				// 커맨드 . 		필드
				return "thymeleaf/help/findId";
			}
		
		}
		
	}
	
}
