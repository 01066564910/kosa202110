package school.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import school.domain.ProfessorSubjectDTO;

@Repository("school.mapper.ProfessorSubjectMapper")
public interface ProfessorSubjectMapper {
	
	public Integer setProSubInsert(ProfessorSubjectDTO dto);

	public List<ProfessorSubjectDTO> getProSubSelectAll();

	public Integer setProSubState(ProfessorSubjectDTO dto);

	public Integer setProSubStateNull(ProfessorSubjectDTO dto);

	public Integer setPSDelete(ProfessorSubjectDTO dto);
	
}
