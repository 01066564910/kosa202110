package school.service.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.command.SubjectCommand;
import school.domain.SubjectDTO;
import school.mapper.SubjectMapper;

@Service
public class SubjectUpdateService {
	@Autowired
	SubjectMapper subjectMapper;
	public void execute(SubjectCommand subjectCommand) {
		SubjectDTO dto = new SubjectDTO();
		dto.setSubjectContent(subjectCommand.getSubjectContent());
		dto.setSubjectName(subjectCommand.getSubjectName());
		dto.setSubjectNum(subjectCommand.getSubjectNum());
		dto.setSubjectTitle(subjectCommand.getSubjectTitle());
		subjectMapper.setSjUpdate(dto);
		
	}
		
}
