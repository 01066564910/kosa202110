package school.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import school.domain.ProfessorDTO;

@Repository("school.mapper.ProfessorMapper")
public interface ProfessorMapper {

	public String setProMaxNum();

	public Integer setProInsert(ProfessorDTO dto);

	public List<ProfessorDTO> getPfSelectAll();

	public ProfessorDTO getPfSelectOne(String professorNum);

	public Integer setPfUpdate(ProfessorDTO dto);

	public Integer setProDel(String num);

	// 마이페이지
	public ProfessorDTO getProMypageSelectOne(String userId);

	public void setPwChange(ProfessorDTO dto);

}
