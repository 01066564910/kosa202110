package kosaShoppingMall.service.memberjoin;

import java.util.HashMap;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Component
@Service
public class MemberWriterService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	JavaMailSender mailSender;
	public void execute(MemberCommand memberCommand, Model model) {
		String memPw = passwordEncoder.encode(memberCommand.getMemberPw()); 
		MemberDTO dto = new MemberDTO();
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setGender(memberCommand.getGender());
		dto.setMemberPw(memPw);
		Integer  i = memberMapper.memberJoinInsert(dto);
		//메일 보내기
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
		//SMS
		String api_key = "NCSW6VCZXODXGPOX";
	    String api_secret = "UZMGJ9ADCK2LCTIEWQRITSVRXW3J0B3T";
		
		Message  coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to" , memberCommand.getMemberPhone()); //수신번호
	    params.put("from", "01071461970"); // 발신번호
	    content = 
				 "안녕하세요. 숭무 쇼핑몰입니다. \n"
				+ memberCommand.getMemberId() +"님 가입을 환영님합니다. \n ";
	    if(content.length()>80) {
	    	params.put("type", "LMS");
	    }else {
	    	params.put("type", "SMS");
	    }
	    params.put("text", content);
	    params.put("app_version", "JAVA SDK v2.2");
	    try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
	    // sms 스몰 메세지 , lms 롱 메세지  mms 미디어 메세지
		
		
	}
	
}
