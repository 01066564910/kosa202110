package main.service;

import main.DTO.MemberDao;

public class Assembler { // 객체 조립기
	MemberDao memberDao;	
	MemberPrinter printer;
	MemberRegisterService MeberRegisterService;
	MemberListPrinter MemberListPrinter;
	ChangePasswordService ChangePasswordService;
	MemberInfoPrinter MemberInfoPrinter;
	public Assembler() {
		this.memberDao = new MemberDao();
		this.printer = new MemberPrinter();
		this.MeberRegisterService 
					= new MemberRegisterService(memberDao);
		this.MemberListPrinter = new MemberListPrinter(memberDao, printer);
		this.MemberInfoPrinter = new MemberInfoPrinter(memberDao);
		
		
	}
	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberPrinter getPrinter() {
		return printer;
	}
	public MemberRegisterService getMeberRegisterService() {
		return MeberRegisterService;
	}
	public MemberListPrinter getMemberListPrinter() {
		return MemberListPrinter;
	}
	public ChangePasswordService getChangePasswordService() {
		return ChangePasswordService;
	}
	public MemberInfoPrinter getMemberInfoPrinter() {
		return MemberInfoPrinter;
	}
	
	
}
