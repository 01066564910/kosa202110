package kosaShoppingMall.service.help;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.FindPasswordCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.mapper.EmployeeMapper;
import kosaShoppingMall.mapper.LoginMapper;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class FindPwService {
	//흠.. 이메일.
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(FindPasswordCommand findPasswordCommand, BindingResult result ) {
		//int는 null값을 받지 못한다
		//integer는 null값을 받는다.
		String grade = loginMapper.findPw(findPasswordCommand);
		if(grade == null) {
			result.rejectValue("userId", "findPasswordCommand.userId", "정보가 부족합니다.");
			return  "thymeleaf/help/findPw";
		}else {
			
			String tampPw = UUID.randomUUID().toString().substring(0 ,7);
			String pw = passwordEncoder.encode(tampPw);
			System.out.println("변경된 비밀번호"+tampPw);
			AuthInfo authInfo = new AuthInfo();
			authInfo.setUserId(findPasswordCommand.getUserId());
			authInfo.setUserPw(pw);
		
			if(grade.equals("mem")) {
				memberMapper.changePw(authInfo);
			}else if(grade.equals("emp")) {
				
				employeeMapper.changePw(authInfo);
			}
			
			MimeMessage msg = mailSender.createMimeMessage();
			// 제목
			String content = "<html><body>"
					+ "안녕하세요 숭무 쇼핑몰입니다.'"
					+findPasswordCommand.getUserId() +
					"'님의 임시 비밀번호는 ["+ tampPw + "] </b></strong>입니다. <br/>"
					+ "반드시 로그인 후 비밀번호를 변경해 주세요."
					+ "</body></html>";
			// 제목
			String subject ="임시 비밀번호";
			try {
				// 내용을 담아서 보냄
				//헤더
				msg.setHeader("content-type", "text/html; charset=UTF-8");
				//제목
				msg.setSubject(subject);
				//보내는사람
				msg.setFrom(new InternetAddress("ender@gmail.com"));//보내는사람
				// 내용
				msg.setContent(content , "text/html; charset=UTF-8");
				// 받는사람
				msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(findPasswordCommand.getUserEmail()));
				mailSender.send(msg);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			// 내용
			
			return  "thymeleaf/help/findPwOk";
		}
		
	}

}
