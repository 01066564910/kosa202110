package school.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import school.domain.CoursesSubjectDTO;
import school.domain.IntegrityCk;

@Repository("school.mapper.CoursesSubjectMapper")
public interface CoursesSubjectMapper {

	public Integer setCSInsert(CoursesSubjectDTO dto);

	public List<CoursesSubjectDTO> getCoursesSubjectSelectAll(String studentNum);

	public void setCSDelete(CoursesSubjectDTO dto);

	public IntegrityCk getIntegrityCk(CoursesSubjectDTO dto);
	
}
