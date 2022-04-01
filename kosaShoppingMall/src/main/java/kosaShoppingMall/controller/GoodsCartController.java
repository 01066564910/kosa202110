package kosaShoppingMall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.PurchaseCommand;
import kosaShoppingMall.service.goods.GoodsReViewUpdateService;
import kosaShoppingMall.service.goods.ReviewWriteService;
import kosaShoppingMall.service.memberjoin.DoPaymentService;
import kosaShoppingMall.service.memberjoin.GoodsBuyService;
import kosaShoppingMall.service.memberjoin.GoodsCartDelService;
import kosaShoppingMall.service.memberjoin.GoodsCartListService;
import kosaShoppingMall.service.memberjoin.GoodsCartQtyDownService;
import kosaShoppingMall.service.memberjoin.GoodsDeleteSevice;
import kosaShoppingMall.service.memberjoin.GoodsOrderService;
import kosaShoppingMall.service.memberjoin.OrederProcessListService;
import kosaShoppingMall.service.memberjoin.PaymentDelService;
import kosaShoppingMall.service.memberjoin.PuchaseDetailService;
import kosaShoppingMall.service.memberjoin.PurchaseDelService;

@Controller

public class GoodsCartController {
	@Autowired
	GoodsCartListService goodsCartListService;
	@Autowired
	GoodsCartQtyDownService goodsCartQtyDownService;
	@Autowired
	GoodsBuyService goodsBuyService; 
	@Autowired
	GoodsCartDelService goodsCartDelService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	OrederProcessListService orederProcessListService;
	@Autowired
	DoPaymentService doPaymentService;
	@Autowired
	PaymentDelService paymentDelService;
	@Autowired
	PurchaseDelService PurchaseDelService;
	@Autowired
	PuchaseDetailService puchaseDetailService;
	@Autowired
	ReviewWriteService reviewWriteService;
	@Autowired
	GoodsReViewUpdateService goodsReViewUpdateService;
	@Autowired
	GoodsDeleteSevice goodsDeleteSevice;
	
	
	@RequestMapping(value = "/cart/goodsDelete")
	public String goodsDelete(@RequestParam(value="purchaseNum")String purchaseNum,
			@RequestParam(value="goodsNum")String  goodsNum ) {
			goodsDeleteSevice.execute(purchaseNum , goodsNum);
			return "redirect:/cart/orderList";
		
	}
			
	
	@RequestMapping(value="/cart/goodsReViewUpdate" , method = RequestMethod.POST)
	public String reViewUpdate1(@RequestParam(value="purchaseNum")String purchaseNum,
			@RequestParam(value="goodsNum")String  goodsNum ,
			@RequestParam(value = "reviewContent")String reviewContent) {
		goodsReViewUpdateService.execute(purchaseNum, goodsNum, reviewContent);
		return "redirect:/cart/orderList";
	}
	
	@RequestMapping(value = "/cart/goodsReViewUpdate" , method = RequestMethod.GET)
	public String reViewUpdate(
			@RequestParam(value="purchaseNum")String purchaseNum,
			@RequestParam(value="goodsNum")String  goodsNum ,
			HttpSession session , Model model) {
		goodsReViewUpdateService.execute( purchaseNum ,  goodsNum ,  session , model );
		return "thymeleaf/memberShip/goodsReViewUpdate";
	}
	
	
	@RequestMapping(value ="/cart/reviewWrite" , method = RequestMethod.POST)
	public String reviewWrite(@RequestParam (value="goodsNum")String goodsNum ,
			@RequestParam(value="reviewWrite")String reviewWrite,
			@RequestParam(value = "purchaseNum")String purchaseNum) {
		reviewWriteService.execute(goodsNum , reviewWrite ,purchaseNum);
		return "redirect:/cart/orderList";
	}
	
	@RequestMapping("/cart/goodsReView")
	public String goodsReView(@ModelAttribute(value="goodsNum")String  goodsNum ,
		 @ModelAttribute(value = "purchaseNum")String purchaseNum){
		return "thymeleaf/memberShip/goodsReView";
	}

	
	
	@RequestMapping(value="/cart/puchaseDetal")
	public String puchaseDetail(@RequestParam(value = "purchaseNum")String purchaseNum , Model model) {
		puchaseDetailService.execute(purchaseNum , model);
		model.addAttribute("newLineChar" ,"\n");
		return "thymeleaf/memberShip/purchaseDetail";
	}
	
	
	@RequestMapping(value = "/cart/paymentDel")
	public String PurchaseDelList(@RequestParam(value = "purchaseNum")String purchaseNum ) {
		PurchaseDelService.execute(purchaseNum);
		return "redirect:/cart/orderList";
	}
	
	
	@RequestMapping(value = "/cart/paymentCancel")
	public String paymentCancel(@RequestParam(value = "purchaseNum")String purchaseNum ) {
		paymentDelService.execute(purchaseNum);
		return "redirect:/cart/orderList";
	}
	
	
	
	
	@RequestMapping(value = "/cart/doPayment")
	public String doPayment(@RequestParam(value = "purchaseNum")String purchaseNum,
			@RequestParam(value = "totalPrice")String totalPrice,
			@RequestParam(value = "cardNumber")String cardNumber ,Model model) {
		doPaymentService.exeucte(purchaseNum , totalPrice , cardNumber , model);
		return "thymeleaf/memberShip/buyfinished";
	}
	
	
	@RequestMapping(value = "/cart/orderList")
	public String orderList(HttpSession session , Model model) {
		orederProcessListService.execute(session , model);
		return "thymeleaf/memberShip/orderList";
	}
	
	
	@RequestMapping(value = "/cart/goodsOrder" , method = RequestMethod.POST)
										//주문 내역 저장
	public String goodsOrder(PurchaseCommand purchaseCommand , HttpSession session) {
		
		//주문 번호 받아오기
		String purchaseNum = goodsOrderService.execute(purchaseCommand , session);
		// 결제 페이지로 이동
		return "redirect:/cart/paymentOk?purchaseNum="+purchaseNum+
				"&totalPrice="+purchaseCommand.getGoodsTotalPrice();
		
	}
	
	
	@RequestMapping(value = "/cart/paymentOk")
	//	  		ModelAttribute 
	public String paymentOk(@ModelAttribute(value = "purchaseNum")String purchaseNum ,
			@ModelAttribute(value = "totalPrice")String totalPrice) {
		return "thymeleaf/memberShip/payment";
	}
	
	
	
	@RequestMapping(value = "/cart/cartdel")
	public String cartdel(@RequestParam(value = "goodsNum")String  goodsNum ,HttpSession session) {
		goodsCartDelService.execute(goodsNum , session);
		return "redirect:/cart/goodsCartList";
	}
	
	@RequestMapping(value = "/cart/goodsBuy" , method = RequestMethod.POST)
	public String goodsBuy(@RequestParam(value ="prodCk")String [] goodsNum , HttpSession session , Model model) {
		goodsBuyService.execute(goodsNum , session , model);
		return "thymeleaf/memberShip/goodsOrder";
	}
	
	
	
	@RequestMapping(value = "/cart/goodsCartQtyDown")
	public String goodsCartQtyDown(@RequestParam(value = "goodsNum")String goodsNum , HttpSession session) {
		goodsCartQtyDownService.execute(goodsNum , session);
		return "redirect:/cart/goodsCartList";
	}
	
	@RequestMapping("/cart/goodsCartList")
	public String goodsCarList(HttpSession session , Model model) {
		goodsCartListService.execute(session , model);
		return "thymeleaf/memberShip/cartList";
	}
	
	
}
