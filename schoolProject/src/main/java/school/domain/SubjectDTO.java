package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias(value = "subjectDTO")
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
	String subjectNum;
	String subjectName;
	String subjectTitle;
	String subjectContent;
}
