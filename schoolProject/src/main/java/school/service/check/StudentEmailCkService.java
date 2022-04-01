package school.service.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.domain.AuthInfo;
import school.domain.CheckIdEmail;
import school.mapper.CkMapper;

@Service
public class StudentEmailCkService {
	@Autowired
	CkMapper ckMapper;
	public String execute(String userEmail) {
		CheckIdEmail ck = ckMapper.setEmailCheck(userEmail);
	
		if(ck == null) {
			return "0";
		}
		return "1";
	
	}

}
