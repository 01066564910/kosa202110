package spring.dependencyTest4.service;



import java.util.Collection;

import spring.dependencyTest4.DTO.MemberDTO;
import spring.dependencyTest4.DTO.MemberDao;

public class MemberListPrinter {
	MemberDao memberDao;
	MemberPrinter print;
	public MemberListPrinter(MemberDao memberDao , MemberPrinter printer) {
		this.memberDao = memberDao;
		this.print = printer;
	}
	public void printAll() {

		
		Collection<MemberDTO> lists = memberDao.selectAll();
		for(MemberDTO dto : lists) {
			print.print(dto);
		}
	}
}
