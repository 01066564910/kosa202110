package kosaShoppingMall.service.memberjoin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.CartDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsCartQtyDownService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String goodsNum , HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO memberDTO = memberMapper.myMemberSelectOne((authInfo.getUserId())); 
		String memberNum = memberDTO.getMemberNum();
		CartDTO dto = new CartDTO();
		dto.setGoodsNum(goodsNum);
		dto.setMemberNum(memberNum);
		memberMapper.goodsCartQtyDown(dto);
	}
}
