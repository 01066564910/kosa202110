package main.service;

import java.util.Collection;

import main.DTO.MemberDTO;
import main.DTO.MemberDao;

public class MemberListPrinter {
	MemberDao memberDao;
	MemberPrinter printer;
	public MemberListPrinter(MemberDao memberDao , MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	public void printAll() {

		
		Collection<MemberDTO> lists = memberDao.selectAll();
		for(MemberDTO dto : lists) {
			printer.print(dto);
		}
	}
}
