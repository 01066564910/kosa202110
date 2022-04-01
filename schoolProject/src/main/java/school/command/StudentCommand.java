package school.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data 
public class StudentCommand {
	@NotBlank(message = "학과 번호를 입력하세요")
	String departmentNum;
	@NotBlank(message = "학번을 입력하세요")
	String studentNum;
	@NotBlank(message = "아이디를 입력하세여")
	String studentId;
	@NotBlank(message = "비밀번호를 입력하세여")
	String studentPw;
	@NotBlank(message = "비밀번호 확인을 입력하세여")
	String studentPwCon;
	@NotBlank(message = "이름을 입력하세여")
	String studentName;
	@NotBlank(message = "폰번호를 입력하세여")
	String studentPhone;
	@NotBlank(message = "이메일을 입력하세여")
	String studentEmail;


}
