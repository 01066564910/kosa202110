package kosaShoppingMall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.mapper.LoginMapper;

@Service
public class MemEmailcheckService {
	@Autowired
	LoginMapper loginMapper;
	public Integer execute(String memberEmail, String memberId ) {
		AuthInfo authInfo = loginMapper.findId(memberEmail);
		if(authInfo==null) {
			return 0;
		}
		if(authInfo.getUserId().equals(memberId)&&memberEmail.equals(authInfo.getEmail())) {
			return 0;
		}else  return 1;
		
	
	}

}
