package spring.dependencyTest4.service;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dependencyTest4.DTO.MemberDTO;
import spring.dependencyTest4.DTO.MemberDao;

public class MemberListPrinter {
	@Autowired
	MemberDao memberDao;
	MemberPrinter print;
	
	public void printAll() {

		
		Collection<MemberDTO> lists = memberDao.selectAll();
		for(MemberDTO dto : lists) {
			print.print(dto);
		}
	}
}
