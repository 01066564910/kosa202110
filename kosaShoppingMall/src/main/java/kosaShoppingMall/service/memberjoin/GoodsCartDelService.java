package kosaShoppingMall.service.memberjoin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsBuy;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsCartDelService {
	@Autowired
	MemberMapper memberMapper; 
	
	public void execute(String  goodsNum , HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
			MemberDTO memDTO = memberMapper.myMemberSelectOne(authInfo.getUserId());
			String memberNum = memDTO.getMemberNum();
			GoodsBuy goodsBuy = new GoodsBuy();
			goodsBuy.setMemberNum(memberNum);
			goodsBuy.setGoodsNums(goodsNum.split("/"));
			
		 String [] goodsNum1 = goodsNum.split("/");
		
		List<String[]> condition = new ArrayList<String[]>();
		
		List<String> cs = new ArrayList<String>();
		for(String num:goodsNum1) {
			cs.add(num); } 
		memberMapper.cartDel(goodsBuy);
		}
}
