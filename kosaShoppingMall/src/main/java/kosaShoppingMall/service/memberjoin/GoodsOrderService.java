package kosaShoppingMall.service.memberjoin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.PurchaseCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsBuy;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.PurchaseDTO;
import kosaShoppingMall.domain.PurchaseListDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsOrderService {
	@Autowired
	MemberMapper memberMapper;
	public String execute( PurchaseCommand purchaseCommand , HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO memberDTO =  memberMapper.myMemberSelectOne(authInfo.getUserId());
		PurchaseDTO dto = new PurchaseDTO();
		// 굼캐
		dto.setPurchaseAddr(purchaseCommand.getPurchaseAddr());
		dto.setMemberNum(memberDTO.getMemberNum());
		dto.setMessage(purchaseCommand.getMessage());
		dto.setPurchasePhone(purchaseCommand.getPurchasePhone());
		dto.setPurchaseStatus("결제준비중");
		dto.setRecieveName(purchaseCommand.getReceiveName());
		dto.setTotalPrice(purchaseCommand.getTotalPrice());
		
		//구매번호
		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmm");
		String purchaseNum = df.format(new Date());
		dto.setPurchaseNum(purchaseNum);
		Integer i = memberMapper.purchase(dto);
		
		// 구매 리스트 : 제품마다 한번씩 insert 해야하므로  반복문을 사용해서 insert문을 실행
		if(i == 1) {
			// 구매 리스트에 정보 저장
			for(String goodsNum : purchaseCommand.getGoodsNums().split("/")) {
				PurchaseListDTO purchaseListDTO	 = new PurchaseListDTO();
				purchaseListDTO.setGoodsNum(goodsNum);
				purchaseListDTO.setPurchaseNum(purchaseNum);
				purchaseListDTO.setMemberNum(memberDTO.getMemberNum());
			     memberMapper.purchaseList(purchaseListDTO);
				
		}
			//장바구니에서 구매된 상품을 제거
		GoodsBuy goodsBuy = new GoodsBuy();
		goodsBuy.setGoodsNums(purchaseCommand.getGoodsNums().split("/"));
		goodsBuy.setMemberNum(memberDTO.getMemberNum());
		memberMapper.cartGoodsDel(goodsBuy);
	
		}
		return purchaseNum;
	}
}
