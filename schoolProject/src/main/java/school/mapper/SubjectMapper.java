package school.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import school.domain.SubjectDTO;

@Repository(value = "school.mapper.SubjectMapper" )
public interface SubjectMapper {
	public String setSjMaxNum();

	public Integer setSjInsert(SubjectDTO dto);

	public List<SubjectDTO> getSjSelect();

	public SubjectDTO getSjSelectOne(String subjectNum);

	public Integer setSjDelete(String num);

	public Integer setSjUpdate(SubjectDTO dto);
}
