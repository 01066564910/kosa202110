package school.service.check;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.domain.AuthInfo;
import school.domain.CheckIdEmail;
import school.mapper.CkMapper;
import school.mapper.StudentMapper;

@Service
public class StudentEmailUpdateCkService {
	@Autowired
	 CkMapper ckMapper ;
	public String execute(String userEmail, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		CheckIdEmail ck =  ckMapper.setEmailCheck(userEmail);
		if(ck == null) {
			return "0";
		}else if(ck !=null && authInfo.getUserId().equals(ck.getUserId())) {
			return "0";
		}else {
			return "1";
		}
	}
	
}
