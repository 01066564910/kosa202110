package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NewPwCommand {
	@NotBlank(message = "비밀번호를 입력해주세요")
	String oldPw;
	@NotBlank(message = "새로운 비밀번호를 입력해주세요")
	String newPw;
	@NotBlank(message = "새로운 비밀번호확인을 입력해주세요")
	String newPwCon;
}
