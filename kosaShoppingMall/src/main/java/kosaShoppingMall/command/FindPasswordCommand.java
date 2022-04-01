package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("fpwc")
public class FindPasswordCommand {
	// NotEmpty는 공백문자가 있어도 허용한다  스페이스바
	// not null은 객체가 생성이 안되었을 때
	
	@NotBlank(message = "아이디를 입력해주세요.")
	String userId;
	@NotBlank(message = "연락처를 입력해주세요.")
	String userPhone;
	@NotBlank(message = "이메일을 입력해주세요.")
	String userEmail;
}
