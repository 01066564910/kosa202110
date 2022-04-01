package school.domain;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("professorDTO")
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
	String professorNum;
	String professorName;	
	String professorPhone;
	String professorEmail;
	String professorId;
	String professorPw;

	String departmentNum;
	
}
