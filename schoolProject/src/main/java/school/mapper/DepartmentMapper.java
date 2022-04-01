package school.mapper;

import java.util.List;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;

import school.domain.DepartmentDTO;
import school.domain.ProfessorDTO;
import school.domain.StartEndPageDTO;
import school.domain.SubjectDTO;


@Repository(value="school.mapper.DepartmentMapper")
public interface DepartmentMapper {

	public String departmentMaxNum();

	public Integer setDepartmentInsert(DepartmentDTO dto);

	public List<DepartmentDTO> getDepartmentSelect();

	public DepartmentDTO getDepartmentSelectOne(String departmentNum);

	public Integer setDepartmentUpdate(DepartmentDTO dto);

	public Integer setDepartmentDelete(String departmentNum);

	public List<SubjectDTO> getSelectSubject(String departmentNum);

	public List<ProfessorDTO> selectProfessor(String subjectNum);

	public int getDepartmentCount(String searchWord);

	public List<DepartmentDTO> getDepartmentSelectPage(StartEndPageDTO dto);

	
	
}
