package kosaShoppingMall.service.memberjoin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsBuy;
import kosaShoppingMall.domain.GoodsCartDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsBuyService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String [] goodsNum , HttpSession session , Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO memberDTO = memberMapper.myMemberSelectOne(authInfo.getUserId());
		GoodsBuy goodsBuy = new GoodsBuy();
		goodsBuy.setGoodsNums(goodsNum);
		goodsBuy.setMemberNum(memberDTO.getMemberNum());
		
		List<GoodsCartDTO>	list =  memberMapper.goodsOrder(goodsBuy);
		Long goodsTotalPrice = 0L;
		// 배송비와 토탈금액을 더해준다.
		for(GoodsCartDTO dto : list) {
			goodsTotalPrice += dto.getCartDTO().getTotalPrice(); // + dto.getGoodsDTO().getDeliveryCost();
		}
		Long goodsTotalDelivery = 0L;
		for ( GoodsCartDTO dto :list) {
			goodsTotalDelivery +=dto.getGoodsDTO().getDeliveryCost();
		}
		String goodsNums= "";
		for(GoodsCartDTO dto:list) {
			goodsNums += dto.getCartDTO().getGoodsNum() +"/";
		}
			
		model.addAttribute("goodsTotalDelivery" , goodsTotalDelivery);	
		model.addAttribute("goodsTotalPrice" , goodsTotalPrice);
		model.addAttribute( "list", list );
		model.addAttribute("goodsNums" , goodsNums);
		
		
		
	}
}
