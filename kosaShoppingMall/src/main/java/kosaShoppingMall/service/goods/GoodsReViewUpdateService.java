package kosaShoppingMall.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsReviewDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.ReviewDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsReViewUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(String purchaseNum , String goodsNum , HttpSession session , Model  model) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		MemberDTO memberDTO = memberMapper.myMemberSelectOne(authInfo.getUserId());
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setGoodsNum(goodsNum);
		reviewDTO.setPurchaseNum(purchaseNum);
		reviewDTO.setMemberNum(memberDTO.getMemberNum());
		GoodsReviewDTO dto = memberMapper.reviewSelect(reviewDTO);
		model.addAttribute("dto" ,dto);
	}
	public void execute(String purchaseNum, String goodsNum, String reviewContent) {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setGoodsNum(goodsNum);
		reviewDTO.setPurchaseNum(purchaseNum);
		reviewDTO.setReviewContent(reviewContent);
		memberMapper.reviewUpdate(reviewDTO);
	}
}
