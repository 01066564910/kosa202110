package school.service.promypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.AuthInfo;
import school.domain.ProfessorDTO;
import school.mapper.ProfessorMapper;


@Service
public class ProMypageDetailService {
	@Autowired
	ProfessorMapper professorMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		ProfessorDTO dto =  professorMapper.getProMypageSelectOne(authInfo.getUserId());
		model.addAttribute("professorCommand" ,  dto);
	}

}
