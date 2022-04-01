package school.service.stuMypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.AuthInfo;
import school.domain.StudentDTO;
import school.mapper.StudentMapper;

@Service
public class StuentMypageDetailService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(HttpSession session , Model model) {
		 AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		 StudentDTO dto = studentMapper.getStudentSelectOne(authInfo.getUserId());
		 model.addAttribute("studentCommand" , dto);
	}
}
