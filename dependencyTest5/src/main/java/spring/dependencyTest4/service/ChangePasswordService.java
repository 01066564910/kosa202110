package spring.dependencyTest4.service;


import org.springframework.beans.factory.annotation.Autowired;

import spring.dependencyTest4.DTO.MemberDTO;
import spring.dependencyTest4.DTO.MemberDao;

public class ChangePasswordService {
	@Autowired
	// 의존객체
		MemberDao memberDao;
	// 
		
	public void execute(String email,String oldPw,String newPw) {
		MemberDTO dto = memberDao.selectByEmail(email);
		if(dto == null) {
			System.out.println("이메일이 존재하지 않습니다.");
			return;
		}
		dto.changePassword(oldPw , newPw);
		memberDao.update(dto);
	}
}
