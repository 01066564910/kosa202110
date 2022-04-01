package kosaShoppingMall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.GoodsInquireCommand;
import kosaShoppingMall.service.goods.GoodsDetailService;
import kosaShoppingMall.service.goods.GoodsInquireDelService;
import kosaShoppingMall.service.goods.GoodsInquireListService;
import kosaShoppingMall.service.goods.GoodsInquireUpdateService;
import kosaShoppingMall.service.goods.GoodsProdInfoService;
import kosaShoppingMall.service.goods.GoodsViewListService;
import kosaShoppingMall.service.goods.GoodsinquireWriteService;
import kosaShoppingMall.service.memberjoin.GoodsInquireModifyService;

@Controller

public class CornerController {
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsViewListService goodsViewListService;
	@Autowired
	GoodsInquireListService goodsInquireListService;
	@Autowired
	GoodsinquireWriteService goodsinquireWriteService;
	@Autowired
	GoodsInquireDelService goodsInquireDelService;
	@Autowired
	GoodsInquireModifyService goodsInquireModifyService;
	@Autowired
	GoodsInquireUpdateService goodsInquireUpdateService;
	@Autowired
	GoodsProdInfoService goodsProdInfoService;
	
	
	@RequestMapping(value = "/corner/inquireUpdate" , method = RequestMethod.POST)
	public String inquireUpdate1(GoodsInquireCommand goodsInquireCommand , HttpServletResponse response) {
		goodsInquireUpdateService.execute(goodsInquireCommand);
		try {
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			String str = "<script language='javascript'>" 
			           + " opener.parent.inquire();"
			           + " window.self.close();"
			           + "</script>";
			 out.print(str);
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/corner/inquireUpdate" , method = RequestMethod.GET)
	public String inquireUpdate(@RequestParam(value = "inquireNum")String inquireNum , Model model) {
		goodsInquireModifyService.execute(inquireNum , model);
		return "thymeleaf/goods/inquireUpdate";
	}
	
	@RequestMapping(value = "/corner/inquireDelete")
	public String inquireDelete(@RequestParam(value = "inquireNum")String inquireNum ,  HttpServletResponse response) {
		goodsInquireDelService.execute(inquireNum);
		try {
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			String str = "<script language='javascript'>" 
			           + " opener.parent.inquire();"
			           + " window.self.close();"
			           + "</script>";
			 out.print(str);
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/corner/inquireWrite" , method = RequestMethod.POST)
	public String inquireWrite1(@RequestParam (value = "goodssNum")String goodssNum ,
			GoodsInquireCommand goodsInquireCommand , HttpServletResponse response  , HttpSession session) {
		goodsinquireWriteService.execute(goodssNum , goodsInquireCommand , session);
		// 오픈 되어있는 부모의 함수를 실행시키겠다.
		try {
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			String str = "<script language='javascript'>" 
			           + " opener.parent.inquire();"
			           + " window.self.close();"
			           + "</script>";
			 out.print(str);
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "thymeleaf/goods/inquireWrite";
	}
	
	@RequestMapping(value = "/corner/inquireWrite" , method = RequestMethod.GET)
	public String inquireWrite(@RequestParam(value = "goodsNum")String goodsNum , Model model) {
		goodsDetailService.execute(goodsNum , model);
		return "thymeleaf/goods/inquireWrite";
	}
	
	
	@RequestMapping(value = "/corner/inquire")
	public String inquire(@ModelAttribute(value = "goodsNum")String goodsNum , Model model) {
		goodsInquireListService.execute(goodsNum , model);
		return "thymeleaf/goods/inquireList";
	}
	
	@RequestMapping(value = "/corner/reviewList")
	public String reviewList(@RequestParam(value = "goodsNum")String goodsNum , Model model) {
		goodsViewListService.execute(goodsNum , model);
		model.addAttribute("newLineChar" , "\n");
		return "thymeleaf/goods/viewList";
	}
	
	@RequestMapping("/corner/prodInfo")
	public  String prodInfo(@RequestParam (value ="goodsNum" )String goodsNum , Model model ,HttpSession session) {
		goodsProdInfoService.execute(goodsNum, model , session);
		
		model.addAttribute("newLineChar" ,"\n");
		return "thymeleaf/goods/prodInfo";
	}
	
}
