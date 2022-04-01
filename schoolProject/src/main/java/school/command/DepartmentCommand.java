package school.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DepartmentCommand {
	@NotBlank(message = "입력해주세요")	 // 1. 객체가 없거나 not null
	String departmentNum;            // 1. 객체가 없거나 2. "" notBlank
	@NotBlank(message = "입력해주세요")  // 1. 객체가 없거나, 2. "" , 3. " " notempty
	String departmentName;
	@NotBlank(message = "입력해주세요")
	String departmentPhone;
	@NotBlank(message = "입력해주세요")
	String depatymentAddr;
	@NotBlank(message = "입력해주세요")
	String fullAddr;
	@NotBlank(message = "입력해주세요")
	String addr1;
	@NotBlank(message = "입력해주세요")
	String addr2;
}
