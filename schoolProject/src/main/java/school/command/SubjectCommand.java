package school.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SubjectCommand {
	
	String  subjectNum;
	String subjectName;
	String subjectTitle;
	String subjectContent;
}
