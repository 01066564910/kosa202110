package kosaShoppingMall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kosaShoppingMall.command.DeliveryCommand;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.service.EmailCheckService;
import kosaShoppingMall.service.EmpEmailUpCheckService;
import kosaShoppingMall.service.IdcheckService;
import kosaShoppingMall.service.MemEmailcheckService;
import kosaShoppingMall.service.goods.EmpDeliveryUpdateService;
import kosaShoppingMall.service.goods.GoodsCartService;
import kosaShoppingMall.service.goods.GoodsListService;
import kosaShoppingMall.service.goods.GoodsWishService;
import kosaShoppingMall.service.memberjoin.PurchaseDelsService;

@RestController
public class CheckController {
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	EmailCheckService emailCheckService; 
	@Autowired
	EmpEmailUpCheckService empEmailUpCheckService;
	@Autowired
	MemEmailcheckService memEmailcheckService;
	@Autowired
	GoodsWishService goodsWishService;
	@Autowired
	GoodsCartService goodsCartService;
	@Autowired
	PurchaseDelsService purchaseDelsService;
	@Autowired
	EmpDeliveryUpdateService empDeliveryUpdateService;
	@Autowired
	GoodsListService goodsListService;
	
	
	
	@RequestMapping(value = "/android" , produces = "application/json;charset=utf-8")
	public @ResponseBody String json(Model model ) {
		Gson gson = new Gson();
		List<GoodsDTO> dailyBoxOfficeList = goodsListService.execute(1,model , null);
		return gson.toJson(dailyBoxOfficeList);
				
	}
	
	
	@RequestMapping(value = "/goods/deliveryUpdatePro")
	public  Integer deliveryUpdatePro(DeliveryCommand deliveryCommand) {
		Integer i = empDeliveryUpdateService.execute(deliveryCommand);
		return  i;
	}
	
	
	@RequestMapping(value = "/cart/paymentDels")
	public String paymentDels(@RequestParam(value = "purchaseNum[]")String [] purchaseNum) {
		 Integer i =	purchaseDelsService.execute(purchaseNum);
		 return i.toString();
					 
		}
		
	
	
	
	@RequestMapping(value = "/cart/goodsCartAdd")
	public String goodsCartAdd(@RequestParam(value = "goodsNum") String goodsNum,
			@RequestParam(value = "goodsQty")Integer goodsQty , HttpSession session) {
		
		return goodsCartService.execute(goodsNum ,goodsQty , session);
	}
	
	@RequestMapping(value ="/cart/goodsWishAdd")
	public String goodsWishAdd(@RequestParam(value = "goodsNum")String goodsNum , HttpSession session) {
		
		return  goodsWishService.execute(goodsNum ,session );
		
	}
	
	/// 멤버 업데이트
	@RequestMapping(value="/member/emailCheck" , method = RequestMethod.POST)
	public String emailCheck1(@RequestParam(value="memberEmail")String memberEmail,@RequestParam(value = "memberId")String memberId) {
		Integer i = memEmailcheckService.execute(memberEmail , memberId);
		if(i==0) {
			return "사용 가능한 이메일 입니다.";
		}else {
			return "사용중인 이메일 입니다.";
		}
	}
	// emp 업데이트
	@RequestMapping(value = "/emp/emailChecks" , method = RequestMethod.POST)
	public String empEmailUpCheckService(@RequestParam(value="empEmail")String empEmail,
			@RequestParam(value = "empId")String empId) {
		Integer i = empEmailUpCheckService.execute(empEmail,empId);
		if(i==0) {
			return "사용 가능한 이메일 입니다.";
		}else {
			return "사용중인 이메일 입니다.";
		}
	}
	
	
	/// emp form
	@RequestMapping(value = "/emp/emailCheck" , method = RequestMethod.POST)
	public String empEmailCheck(@RequestParam(value="empEmail")String memberEmail) {
		Integer i = emailCheckService.execute(memberEmail);
		if(i==0) {
			return "사용 가능한 email입니다.";
		}else {
			return "사용중인 email입니다.";
		}
	}
	
	@RequestMapping(value="/emp/idCheck" ,method = RequestMethod.POST)
	public String empIdCheck(@RequestParam(value = "empId")String memberId) {
		Integer i = idcheckService.execute(memberId);
		if(i==0) {
			return "사용 가능한 id입니다.";
		}else {
			return "사용중인 id입니다.";
		}
	}
	//// member///
	@RequestMapping(value = "/member/memEmailCheck" , method = RequestMethod.POST)
	public String memEmailCheck(@RequestParam(value="memEmail")String memberEmail) {
		Integer i = emailCheckService.execute(memberEmail);
		if(i==0) {
			return "사용 가능한 email입니다.";
		}else {
			return "사용중인 email입니다.";
		}
	}
	
	@RequestMapping(value="/member/memIdCheck" ,method = RequestMethod.POST)
	public String memIdCheck(@RequestParam(value = "memberId")String memberId) {
		Integer i = idcheckService.execute(memberId);
		if(i==0) {
			return "사용 가능한 id입니다.";
		}else {
			return "사용중인 id입니다.";
		}
	}
	
	
	@RequestMapping(value = "/register/emailCheck" , method = RequestMethod.POST)
	public String emailCheck(@RequestParam(value="memberEmail")String memberEmail) {
		Integer i = emailCheckService.execute(memberEmail);
		if(i==0) {
			return "사용 가능한 email입니다.";
		}else {
			return "사용중인 email입니다.";
		}
	}
	
	
	
	@RequestMapping(value="/register/idCheck" , method = RequestMethod.POST)
	public String idCheck(@RequestParam(value="memberId") String memberId) {
		System.out.println(memberId);
		Integer i = idcheckService.execute(memberId);
		if( i == 0) {
			return "사용 가능한 id입니다.";
		}else {
			return "사용중인 id입니다.";
		}
	}
	

	
}
