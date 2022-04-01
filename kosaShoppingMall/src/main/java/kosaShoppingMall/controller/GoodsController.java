package kosaShoppingMall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.DeliveryCommand;
import kosaShoppingMall.command.FileInfo;
import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.command.GoodsIpgoCommand;
import kosaShoppingMall.service.goods.DeliveryActionService;
import kosaShoppingMall.service.goods.DeliveryDeleteService;
import kosaShoppingMall.service.goods.FileDelService;
import kosaShoppingMall.service.goods.GoodsAutoNum;
import kosaShoppingMall.service.goods.GoodsDeleteService;
import kosaShoppingMall.service.goods.GoodsDelsService;
import kosaShoppingMall.service.goods.GoodsDetailService;
import kosaShoppingMall.service.goods.GoodsInquireAnswerWriteService;
import kosaShoppingMall.service.goods.GoodsInquireDetailServce;
import kosaShoppingMall.service.goods.GoodsIpgoDeleteService;
import kosaShoppingMall.service.goods.GoodsIpgoDelsSevice;
import kosaShoppingMall.service.goods.GoodsIpgoDetailService;
import kosaShoppingMall.service.goods.GoodsIpgoListservice;
import kosaShoppingMall.service.goods.GoodsIpgoMdifyService;
import kosaShoppingMall.service.goods.GoodsIpgoUpdateService;
import kosaShoppingMall.service.goods.GoodsIpgoWriteService;
import kosaShoppingMall.service.goods.GoodsItemService;
import kosaShoppingMall.service.goods.GoodsListService;
import kosaShoppingMall.service.goods.GoodsModifyService;
import kosaShoppingMall.service.goods.GoodsQuestionService;
import kosaShoppingMall.service.goods.GoodsSerchService;
import kosaShoppingMall.service.goods.GoodsUpdateService;
import kosaShoppingMall.service.goods.GoodsWriteService;
import kosaShoppingMall.service.goods.PurchaseDetailService;
import kosaShoppingMall.service.goods.PurchaseListService;
import kosaShoppingMall.service.memberjoin.PurchaseDelService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsAutoNum goodsAutoNum;
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsModifyService goodsModifyService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@Autowired
	GoodsSerchService goodsSerchService;
	@Autowired
	GoodsItemService goodsItemService;
	@Autowired
	GoodsIpgoWriteService goodsIpgoWriteService;
	@Autowired
	GoodsIpgoListservice goodsIpgoListservice;
	@Autowired
	GoodsIpgoDetailService goodsIpgoDetailService; 
	@Autowired
	GoodsIpgoMdifyService goodsIpgoMdifyService;
	@Autowired
	GoodsIpgoUpdateService goodsIpgoUpdateService;
	@Autowired
	GoodsIpgoDeleteService goodsIpgoDeleteService;
	@Autowired
	GoodsDelsService goodsDelsService;
	@Autowired
	GoodsIpgoDelsSevice goodsIpgoDelsSevice;
	@Autowired
	FileDelService fileDelService;
	@Autowired
	PurchaseListService purchaseListService;
	@Autowired
	PurchaseDetailService purchaseDetailService;
	@Autowired
	DeliveryActionService deliveryActionService;
	@Autowired
	DeliveryDeleteService  deliveryDeleteService;
	@Autowired
	PurchaseDelService purchaseDelService;
	@Autowired
	GoodsQuestionService goodsQuestionService;
	@Autowired
	GoodsInquireDetailServce goodsInquireDetailServce;
	@Autowired
	GoodsInquireAnswerWriteService goodsInquireAnswerWriteService;
	
	@ModelAttribute
	GoodsIpgoCommand setGoodsIpgoCommand() {
		return new GoodsIpgoCommand();
	}
	
	
	@RequestMapping("answerWrite")
	public String answerWrite(@RequestParam(value = "memberNum")String memberNum ,
			@RequestParam(value = "inquireNum")Integer inquireNum ,
			@RequestParam(value = "inquireAnswer")String inquireAnswer,
			@RequestParam(value = "answerEmail")String answerEmail,
			@RequestParam(value = "inquireSubject")String inquireSubject){
		goodsInquireAnswerWriteService.execute(memberNum , inquireNum ,inquireAnswer ,answerEmail , inquireSubject);
		return "redirect:goodsQuestion";
	}
	
	@RequestMapping("goodsInquireDetail/{id}")
	public String goodsInquireDetail(@PathVariable(value = "id")String inquireNum , Model model) {
		goodsInquireDetailServce.execute(inquireNum , model);
		return "thymeleaf/goods/goodsInquireDetail";
	}
	@RequestMapping("goodsQuestion")
	public String goodsQuestion(Model model) {
		goodsQuestionService.execute(model);
		return "thymeleaf/goods/goodsQuestion";
	}
	
	@RequestMapping("deleiveryDel")
	public String deleiveryDel(@RequestParam(value = "purchaseNum")String purchaseNum , HttpServletResponse response) {
		deliveryDeleteService.execute(purchaseNum);
		try{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String str = "<script loanguage='javascript'>"
					+ "opener.location.reload();"
					+ "			window.self.close();"
					+ "</script>";
			out.print(str);
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@RequestMapping("deliveryUpdate")
	public String deliveryUpdate(DeliveryCommand deliveryCommand) {
		
		 return "thymeleaf/goods/deliveryUpdate";
	}
	
	@RequestMapping(value = ( "deliveryAction") ,method = RequestMethod.POST)
	public String deliveryAction(DeliveryCommand deliveryCommand) {
		deliveryActionService.execute(deliveryCommand);
		return "redirect:purchaseList";
	}
	
	@RequestMapping("paymentDel")
	public String paymentDel(@RequestParam(value = "purchaseNum")String purchaseNum) {
		purchaseDelService.execute(purchaseNum);
		return "redirect:purchaseList";
		
	}
	
		
	
	@RequestMapping("puchaseDetail")
	public String puchaseDetail(@RequestParam(value = "purchaseNum")String purchaseNum ,Model model) {
		purchaseDetailService.execute(purchaseNum ,model);
		model.addAttribute("newLineChar" ,"\n");
		return "thymeleaf/goods/purchaseDetail";
	}
	
	
	@RequestMapping("purchaseList")
	public String purchaseList(Model model) {
		purchaseListService.execute(model);
		return "thymeleaf/goods/purchaseList";
		
	}
	
	
	
	
	@RequestMapping("fileDel")
	public String fileDel(FileInfo fileInfo , HttpSession session , Model model) {
		fileDelService.execute(fileInfo , session ,model );
		return "thymeleaf/goods/delPage";
	}
	
	@RequestMapping(value = "goodsIpgoDels" , method = RequestMethod.POST)
	public String goodsIpgoDels(@RequestParam(value="delete") String [] deletes) {
		goodsIpgoDelsSevice.execute(deletes);
		return "redirect:goodsIpgoList";
	}
	
	
	@RequestMapping(value = "goodsDels" , method = RequestMethod.GET)
	public String goodsDels(@RequestParam(value = "delete" )String[] deletes , HttpServletRequest request) {
		goodsDelsService.execute(deletes , request);
		return "redirect:goodsList";
	}
	
	
	@RequestMapping("goodsIpgoDelte")
	public String goodsIpgoDelte(@RequestParam(value = "goodsNum")String goodsNum,
				@RequestParam(value = "ipgoDate")String date) {
		goodsIpgoDeleteService.execute(goodsNum ,date );
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "goodsIpgoModify" , method = RequestMethod.POST)
	public String goodsIpgoModify(GoodsIpgoCommand goodsIpgoCommand) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(goodsIpgoCommand.getIpgoDate());
		
		
		goodsIpgoUpdateService.execute(goodsIpgoCommand);
		return "redirect:goodsIpgoDetail?goodsNum="+goodsIpgoCommand.getGoodsNum()+
				"&ipgoDate="+date;
	}
	
	@RequestMapping(value = "goodsIpgoModify", method = RequestMethod.GET)
	public String goodsIpgoModify(@RequestParam(value = "goodsNum")String goodsNum,
			@RequestParam(value="ipgoDate")String ipgoDate , Model model , HttpSession session) {
		session.removeAttribute("fileList");
		goodsIpgoMdifyService.execute(goodsNum , ipgoDate , model);
		return "thymeleaf/goods/goodsIpgoUpdate";
	}
	
	
	@RequestMapping("goodsIpgoDetail")
	public String goodsIpgoDetail(@RequestParam(value = "goodsNum")String goodsNum,
			@RequestParam(value="ipgoDate")String ipgoDate , Model model ) {
		
		goodsIpgoDetailService.execute(goodsNum,ipgoDate,model);
		return "thymeleaf/goods/goodsIpgoDetail";
	}

	@RequestMapping(value = "ipgoRegist", method = RequestMethod.POST)
	public String ipgoRegist(@Validated GoodsIpgoCommand goodsIpgoCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/goods/goodsIpgo";
		}
		goodsIpgoWriteService.execute(goodsIpgoCommand);
		return "redirect:goodsIpgoList";
	}
	
	@RequestMapping("goodsIpgoList")
	public String goodsIpgoList(@RequestParam(value = "page" , defaultValue = "1" , required = false)Integer page,  Model model) {
		goodsIpgoListservice.execute(page ,model);
		return "thymeleaf/goods/goodsIpgoList";
	}


///	@RequestMapping(value = "goodsItem", method = RequestMethod.POST)
//	public String goodsItems(@RequestParam(value = "page" , defaultValue = "1" , required = false)Integer page, @RequestParam(value = "goodsName") String goodsName, Model model) {
//		goodsItemService.execute(page, goodsName, model);
//		return "thymeleaf/goods/goodsItem";
//	}

	@RequestMapping(value = "goodsItem")
	public String goodsItem(@RequestParam(value = "page" , defaultValue = "1" , required = false)Integer page ,@RequestParam(value = "goodsWord" ,required = false) String goodsWord, Model model) {
		goodsItemService.execute(page, goodsWord, model);
		return "thymeleaf/goods/goodsItem";
	}

	@RequestMapping("goodsIpgo")
	public String goodsIpgo(GoodsIpgoCommand goodsIpgoCommand) {
		// html은 텍스트기 떄문에 날짜형태로 전달을 못해서 문자열로 변환해서 전달해야 한다.
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ipgoDate = sdf.format(now);
		goodsIpgoCommand.setIpgoDate(now);
		return "thymeleaf/goods/goodsIpgo";
	}
////////////////////////////////////////////////// 여기까지 goods /////////////////
	@RequestMapping("goodsSearch")
	public String goodsSearch(@RequestParam(value = "goodsWord") String goodsWord, Model model) {
		goodsSerchService.execute(goodsWord, model);
		return "thymeleaf/goods/goodsList";
	}

	@RequestMapping("goodsDelete")
	public String goodsDelete(@RequestParam(value = "goodsNum") String goodsNum , HttpServletRequest request) {
		goodsDeleteService.execute(goodsNum , request);
		return "redirect:goodsList";
	}

	@RequestMapping("goodsUpdate")
	public String goodsUpdate(@Validated GoodsCommand goodsCommand, BindingResult result , HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/goods/goodsUpdate";
		}
		goodsUpdateService.execute(goodsCommand , session);
		return "redirect:goodsDetail/" + goodsCommand.getGoodsNum();
	}

	@RequestMapping("goodsModify")
	public String goodsModify(GoodsCommand goodsCommand, Model model) {
		goodsModifyService.execute(goodsCommand, model);
		return "thymeleaf/goods/goodsUpdate";
	}

	@RequestMapping("goodsDetail/{goodsNum}")
	public String goodsDetail(@PathVariable(value = "goodsNum") String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		model.addAttribute("newLineChar" ,"\n"); // 'a' ,"a"
		return "thymeleaf/goods/goodsDetail";
	}

	/* 하나하나 받을때 쓰는 방법
	RequestParam(value = "goodsMain" )MultipartFile goodsMain,
		RequestParam(value = "goodsImages")MultipartFile [] goodsImages
	 */
	@RequestMapping(value = ("goodsRegist"), method = RequestMethod.POST)
	public String goodsRegistPost(
			
			@Validated	GoodsCommand goodsCommand , BindingResult result , HttpServletRequest request) {
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsForm";
		}
		if(goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
			result.rejectValue("goodsMain", "goodsCommand.goodsMain", "대문이미지를 선택해주세여");
		}
		goodsWriteService.execute(goodsCommand , request);
		return "redirect:/";

	}

	@RequestMapping(value = ("goodsRegist"), method = RequestMethod.GET)
	public String goodsRegistGet(GoodsCommand goodsCommand) {
		goodsAutoNum.execute(goodsCommand);
		return "thymeleaf/goods/goodsForm";

	}

	@RequestMapping(value = "goodsList")
	public String goodsList(@RequestParam(value = "goodsWord" , required = false) String goodsWord,
			@RequestParam(value = "page" , defaultValue = "1", required = false )Integer page,
			Model model) {
		goodsListService.execute(page , model , goodsWord);
		return "thymeleaf/goods/goodsList";
	}

	/////////////////////// 다른방식으로 들어오면 처음으로 /////////////////////
	@RequestMapping(value = "ipgoRegist", method = RequestMethod.GET)
	public String homeBack() {
		return "redirect:/";
	}
}
