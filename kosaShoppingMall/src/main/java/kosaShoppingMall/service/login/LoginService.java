package kosaShoppingMall.service.login;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.LoginCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.LoginMapper;
import kosaShoppingMall.repository.LoginRepository;

@Component
@Service
public class LoginService {
	@Autowired
	//LoginMapper loginMapper;
	LoginRepository loginRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	GoodsMapper goodsMapper;
	public String execute(LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response ) {
		
		List<GoodsDTO> list = goodsMapper.goodsSelectAll(new StartEndPageDTO());
		request.setAttribute("lists" , list);
		
		String path = "redirect:/";
		System.out.println(loginCommand.getUserId());
		AuthInfo authInfo = loginRepository.UserLogin(loginCommand.getUserId());
		
		
		
		
		
		if(authInfo!= null) {
			if(authInfo.getMemberOk() == null && authInfo.getGrade().equals("member")) {
				result.rejectValue("userId", "loginCommand.userId", "이메일을 확인해주세요.");
				return path;
			}
		}
		
		if(authInfo==null) {
			
			result.rejectValue("userId", "loginCommand.userId", "아이디나  없거나 틀렸습니다.");
			path="thymeleaf/index";
		}
		else if(!passwordEncoder.matches(loginCommand.getUserPw(), authInfo.getUserPw())) {
			path="thymeleaf/index";
			result.rejectValue("userPw", "loginCommand.userPw", " 비밀번호가 없거나 틀렸습니다.");
		}else {
			
			session.setAttribute("authInfo", authInfo);
			
			if(loginCommand.getAutoLogin()!= null && loginCommand.getAutoLogin()) {
				Cookie cookie = new Cookie("autoLogin" , authInfo.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
			}
				
			
			
			//   체크가 아니라고 펄스가 아닐때만
			if(loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
				
				// 객체는 메모리 그러므로 파일에 저장할 수가 없다 .그래서  메모리 안에있는 텍스트를 저장한다
				// 쿠키는 문자열만 저장 가능
				Cookie cookie = new Cookie("idStore" , authInfo.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
				//여기까지 쿠키 만들었음
			}else {
				Cookie cookie = new Cookie("idStore" , "");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			return "redirect:/";
		}
		
		return path;
		
		
	}	
}
