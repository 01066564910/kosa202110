package main;

import java.util.Scanner;

import main.DTO.CachedMemberDao;
import main.DTO.MemberDao;
import main.DTO.RegisterRequest;
import main.service.Assembler;
import main.service.ChangePasswordService;
import main.service.MemberInfoPrinter;
import main.service.MemberListPrinter;
import main.service.MemberPrinter;
import main.service.MemberRegisterService;

public class App {
	// 객체 조립기 생성 
	static Assembler assembler = new Assembler(); 
	// 객체주입을 하면 유지보수가 편해진다.
	//부모객체를 상속받은 자식 클래스는 부모 객체를 사용가능하다.
	 // static MemberDao memberDao = new MemberDao ();을 밑에처럼 바궜다. 
	// static MemberDao memberDao = new CachedMemberDao();
	// static MemberPrinter printer = new MemberPrinter();
	
	// FrontController
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = sc.nextLine();
			// new highland0@nate.com 1234 1234
			// new SM@naver.com 3456 3456
			if (command.startsWith("new ")) {
				String arg[] = command.split(" ");
				// new high 이숭무 1234 1234
				// [0] [1]   [2] [3]  [4]
				if (arg.length != 5) {
					printHelp();
					continue;
				}
				
				//입력한 값 받기위해 만듬 frontcontroller의 request
				RegisterRequest req = new RegisterRequest(); 
				req.setEmail(arg[1]);
				req.setName(arg[2]);
				req.setPassword(arg[3]);
				req.SetConfirmPassword(arg[4]);
				boolean bl = req.isPasswordEqualConfirmPassword();
				if(!bl) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					continue;
				}//의존객체 리퀘스트가 받아온 값을 저장 해야하기 때문에.
					//
					// assembler.getMeberRegisterService로 바뀜
				MemberRegisterService action = assembler.getMeberRegisterService(); 
				action.execute(req);
			}else if (command.equals("list")) {
				//의존객체
				//new MemberListPrinter(memberDao,printer);가밑에처럼 바뀐다.
					MemberListPrinter listPrint = assembler.getMemberListPrinter();
					listPrint.printAll();
			}else if(command.startsWith("change ")) {
				String [] arg = command.split(" ");
				if(arg.length != 4) {
					printHelp();
					continue;
				}
				ChangePasswordService action =
						assembler.getChangePasswordService();
				action.execute(arg[1] , arg[2] , arg[3]);
			}else if(command.startsWith("info ")) {
				String [] arg = command.split(" ");
				if(arg.length != 2) {
					printHelp();
					continue;
				}
				MemberInfoPrinter action =
						assembler.getMemberInfoPrinter();
				action.execute(arg[1]);
			}
			else if(command.equals("exit")) {
				System.out.println("프로그램 종료되었습니다.");
				System.exit(0); // 프로그램 종료
			}else {printHelp();}
		}
	
	}

	public static void printHelp() {
		System.out.println();
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재 비밀번호와 변경비밀번호");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("프로그램 종료는 exit");
	}

}
