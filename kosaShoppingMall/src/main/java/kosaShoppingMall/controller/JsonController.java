package kosaShoppingMall.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.service.member.MemberDetailService;

@Controller
public class JsonController {
	@RequestMapping(value = "jsonMain")
	public String jsonMian() {
		return "thymeleaf/json/jsonMain";
	}
	@RequestMapping("json1")
	public String json1() {
		return "thymeleaf/json/jsonTest";
	}
	
	@Autowired
	MemberDetailService memberDetailService;
	@RequestMapping("ajax")
	public void ajaxView(@RequestParam("id")String id , HttpServletResponse response , Model model) {
		response.setCharacterEncoding("UTF-8");
		String parsonJson;
		MemberDTO member = memberDetailService.execute(id, model);
		if(member != null) {
			parsonJson = "{"
					+ "\"memberName\":\"" +member.getMemberName() +"\""
						+",\"memberAddr\":\"" + member.getMemberAddr() +"\""
					    +"}";
		}else {
			parsonJson = "null";
		}
		try {
			response.getWriter().print(parsonJson);
		}catch(Exception e) {}
	}
	
	
	@RequestMapping("json2")
	public String json2() {
		return "thymeleaf/json/jsonTest2";
	}
	@RequestMapping("ajax1")
	public void ajax1(@RequestParam("id")String id , HttpServletResponse response , Model model) {
		ObjectMapper mapper = new ObjectMapper();
		MemberDTO member = memberDetailService.execute(id, model);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(mapper.writeValueAsString(member));
		}catch(Exception e) {}
	}
	
	
	
	@RequestMapping("json3")
	public String json3() {
		return "thymeleaf/json/jsonTest3";
	}
	@RequestMapping("/ajax2")
	public @ResponseBody MemberDTO ajax2(@RequestParam("id")String id , HttpServletResponse response , Model model ){
		MemberDTO member  = memberDetailService.execute(id, model);
		return member;
	}
	
	@RequestMapping("json4")
	public String json4() {
		return "thymeleaf/json/jsonTest4";
	}
	//ModelAndView 는 모델과 뷰를 리턴한다.
	// 뷰는 밑에있고 model은 서비스 안에 있는 memberCommand이다
	@RequestMapping(value = "/ajax3")
	public ModelAndView  ajax3(@RequestParam("id")String id , HttpServletResponse response , Model model ){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		memberDetailService.execute(id, model);
		
		
		MemberDTO member =  memberDetailService.execute(id, model);
		mav.addObject("member", member);
		// 이렇게 써도 되긴한다.
	//	model.addAttribute("member" , member);
		return mav;
	}
	
	
}
