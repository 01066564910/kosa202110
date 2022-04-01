package kosaShoppingMall.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.WishDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.MemberMapper;
@Service
public class GoodsProdInfoService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(String goodsNum, Model model, HttpSession session) {
		
		 AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		 goodsMapper.goodsVisitCnt(goodsNum);
		 GoodsDTO dto  = goodsMapper.goodsSelectOne(goodsNum);

		 GoodsDTO goodsDTO = new GoodsDTO(goodsNum); 
		 
		 try {
			 MemberDTO memberDTO = memberMapper.myMemberSelectOne(authInfo.getUserId());
			 memberDTO.setGoodsDTO(goodsDTO);
			 memberDTO.setGoodsDTO(new GoodsDTO());
			 memberDTO.getGoodsDTO().setGoodsNum(goodsNum);
				String chk = goodsMapper.getWishSelectOne(memberDTO);
				 model.addAttribute("goodsCommand" , dto);
				 model.addAttribute("chk" , chk);
				 model.addAttribute("authInfo" , authInfo.getUserId());
		 }catch(NullPointerException e) {
			
		 }
		 
		 
		
		 
	
	
		
	}

}
