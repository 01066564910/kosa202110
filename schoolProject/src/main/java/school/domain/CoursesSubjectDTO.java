package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("coursesSubjectDTO")
public class CoursesSubjectDTO {
	String departmentNum;
	String subjectNum;
	String professorNum;
	String studentNum;
	String cnt;
	ProfessorSubjectDTO professorSubjectDTO;
	ProfessorDTO professorDTO;
	SubjectDTO subjectDTO ;
	DepartmentDTO departmentDTO;
}
