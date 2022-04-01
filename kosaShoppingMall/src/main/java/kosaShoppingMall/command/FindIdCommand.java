package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
//자바 소스파일 컴파일 하면 class파일이 만들어지고 get와 set이 있다.
//소스 파일에는 없지만 class파일에는 잇게 만들어 주는게 lombok이다.
public class FindIdCommand {
	@NotBlank(message = "등록한 전화번호를 입력하세요.")
	String memberPhone;
	@NotBlank(message = "등록한 이메일을 입력하세요.")
	String memberEmail;
}
