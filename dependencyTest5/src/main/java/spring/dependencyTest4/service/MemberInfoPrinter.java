package spring.dependencyTest4.service;


import org.springframework.beans.factory.annotation.Autowired;

import spring.dependencyTest4.DTO.MemberDTO;
import spring.dependencyTest4.DTO.MemberDao;

public class MemberInfoPrinter {  // 의존객체는 꼭 객체생성이 되야한다. 생성이 될 수도있고 안될 수도있는 것은 의존객체가 아니다. 여기서 DTO는 null일 수도 있어서 의존객체가 아니다.
	@Autowired
	MemberDao memberDao;
	MemberPrinter print = new MemberPrinter();
	
	
	
	public void execute(String email) {
		MemberDTO dto = memberDao.selectByEmail(email);
		if(dto == null) {
			System.out.println("데이터가 없음\n");
			return;
		}
		print.print(dto);
	}
}
