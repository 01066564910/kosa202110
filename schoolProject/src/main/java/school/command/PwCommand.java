package school.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PwCommand {
	@NotBlank(message = "기존 비밀번호를 입력하세요")
	String oldPw;
	@NotBlank(message = "변경하실 비밀번호를 입력하세요")
	String newPw;
	@NotBlank(message = "변결하실 비밀번호 확인을 입력하세요")
	String newPwCon;
}
