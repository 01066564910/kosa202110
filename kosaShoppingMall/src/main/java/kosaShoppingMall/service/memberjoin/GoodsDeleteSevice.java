package kosaShoppingMall.service.memberjoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.ReviewDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsDeleteSevice {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String purchaseNum, String goodsNum) {
		ReviewDTO dto = new ReviewDTO();
		dto.setPurchaseNum(purchaseNum);
		dto.setGoodsNum(goodsNum);
		memberMapper.reviewDelete(dto);
	}
	
}
