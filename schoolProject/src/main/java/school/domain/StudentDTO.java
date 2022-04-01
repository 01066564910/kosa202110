package school.domain;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("studentDTO")
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	String departmentNum;
	String studentNum;
	String studentId;
	String studentPw;
	String studentName;
	String studentPhone;
	String studentEmail;
	
}
