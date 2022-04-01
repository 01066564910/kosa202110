package main.service;

import main.DTO.MemberDTO;
import main.DTO.MemberDao;

public class MemberInfoPrinter {  // 의존객체는 꼭 객체생성이 되야한다. 생성이 될 수도있고 안될 수도있는 것은 의존객체가 아니다. 여기서 DTO는 null일 수도 있어서 의존객체가 아니다.
	MemberDao meberDao;
	MemberPrinter print = new MemberPrinter();
	public MemberInfoPrinter(MemberDao meberDao) {
		this.meberDao = meberDao;
	}
	
	public void execute(String email) {
		MemberDTO dto = meberDao.selectByEmail(email);
		if(dto == null) {
			System.out.println("데이터가 없음\n");
			return;
		}
		print.print(dto);
	}
}
