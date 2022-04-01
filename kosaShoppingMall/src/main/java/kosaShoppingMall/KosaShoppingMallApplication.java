package kosaShoppingMall;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kosaShoppingMall.command.LoginCommand;
import kosaShoppingMall.service.goods.GoodsListPageService;
// SpringBootApplication을 이용해서 자동생성 
@SpringBootApplication
// 코사 쇼핑몰에 있는 모든 것을
@ComponentScan(value = "kosaShoppingMall")
// kosaShoppingMall 패키지 안에있는 mapper를 사용하기 위해서 적음
@MapperScan(value = {"kosaShoppingMall"})
@Controller

public class KosaShoppingMallApplication {
	@Autowired
	GoodsListPageService  goodsListPageService;
	@RequestMapping("/")
	public String test(LoginCommand loginCommand ,  HttpServletRequest request) {
		
		goodsListPageService.execute( request);
		return "thymeleaf/index";
	}
	// 주소를 /라는 주소로 불러온다.
	
	public static void main(String[] args) {
		SpringApplication.run(KosaShoppingMallApplication.class, args);
	}
}