package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("professorSubjectDTO")
public class ProfessorSubjectDTO {
	String professorNum;
	String subjectNum;
	String state;
}
