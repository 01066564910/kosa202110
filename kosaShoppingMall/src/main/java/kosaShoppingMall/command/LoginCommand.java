package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginCommand {
	@NotBlank (message = "아이디를 입력하세요")
	String userId;
	@NotBlank (message = "비밀번호를 입력하세요")
	String userPw;
	
	Boolean idStore;
	Boolean autoLogin;

}
