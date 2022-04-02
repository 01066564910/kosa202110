package school.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class kakaoPayController {
	
	@RequestMapping("/cls/jq/kakaopay.cls")
	@ResponseBody
	public String  kakaoPay() {
		try {
			URL adds = new URL("https://kapi.kakao.com/v1/payment/ready");
			// 서버연결 = 주소.
			//  HttpURLConnection :서버 연결을 해주는 역할  openConnection?
			HttpURLConnection connect =(HttpURLConnection)adds.openConnection();
			connect.setRequestMethod("POST");
			connect.setRequestProperty("Authorization", "KakaoAK de68f02bcb512604726aa3346642556a");
			connect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connect.setDoOutput(true); // 서버에 넣어줘여 할게 있으니 true 커넥션은 생성되면 기본적으로 input은 트루가 되어잇기때문에 필요없다.
			String paramiter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://localhost:8080/cls/jq/kakaopay.cls&fail_url=https://localhost:8080/fail&cancel_url=https://localhost:8080/cancel";
			OutputStream outPut = connect.getOutputStream(); // 이러면 줄 수 있다 request.
			DataOutputStream dataOutPut = new DataOutputStream(outPut); // output stream은 바이트 형식으로 주고받아야 한다.
			dataOutPut.writeBytes(paramiter);
//			dataOutPut.flush(); // 자기가 가지고 있는 어떤것을 비운다는 함수 java에서는 자기가 가지고 있는것을 태워 보낼 수 있다.
			dataOutPut.close();//flush도 있다
			int results = connect.getResponseCode();
			
			InputStream receive;
			// http 코드에서 정상적인 통신 코드는 200이고 그 외는 전부 에러이다.
			if(results == 200) {
				receive = connect.getInputStream();
			}else {
				receive = connect.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(receive); // 받는애
			// BufferedReader 는 진짜 형변환을 위한 클레스는 아니다.
			BufferedReader cast = new BufferedReader(reader); //읽는애
			return cast.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return "{\"result\":\"NO\"}";
	}
	
}
