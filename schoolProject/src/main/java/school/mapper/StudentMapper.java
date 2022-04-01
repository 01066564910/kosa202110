package school.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import school.domain.StudentDTO;

@Repository("school.mapper.StudentMapper")
public interface StudentMapper {

	public String setStudentMaxNum(String departNum);

	public Integer setStudentInsert(StudentDTO dto);

	public List<StudentDTO> getSdSelectAll();

	public StudentDTO getStudentSelectOne(String studentId);

	public Integer setStudentUpdate(StudentDTO dto);

	public Integer setStudentDelete(String num);

	public void setPwChange(StudentDTO dto);
	
}
