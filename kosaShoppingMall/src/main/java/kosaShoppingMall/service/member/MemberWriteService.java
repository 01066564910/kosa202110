package kosaShoppingMall.service.member;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JavaMailSender mailSender;
	public void execute(MemberCommand memberCommand) {
		String empPw = passwordEncoder.encode(memberCommand.getMemberPw());
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setMemberRegist(memberCommand.getMemberRegist());
		dto.setMemberPw(empPw);
		
		Integer i = memberMapper.memberInsert(dto);
		
		String num = UUID.randomUUID().toString().substring(0 , 10);
		MimeMessage msg = mailSender.createMimeMessage();
		String content = "<html><body>"
				+ "안녕하세요. 숭무 쇼핑몰입니다.<br/>"
				+ memberCommand.getMemberId() +"님 가입을 환영님합니다. <br/>"
				+ "<a href='http://localhost:8080/register/memberMail?"
				+ "num="+ num +"&reciver="+ memberCommand.getMemberEmail() +""
				+ "&userId="+ memberCommand.getMemberId() + "'>"
				+ "가입을 완료하시려면 여기를 눌러주세요.</b></strong></a>"
				+ "</brdy></html>";
		String subject = "가입환영인사";
		try {
			msg.setHeader("content-type", "text/html; charset=UTF-8");// 타입 
			msg.setContent(content,"text/html; charset=UTF-8");// 내용
			msg.setSubject(subject); //제목
			msg.setFrom(new InternetAddress("sender@gmail.com")); //보내는사람
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(memberCommand.getMemberEmail()));//받는사람
			mailSender.send(msg);//보내기
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		

	}

}
