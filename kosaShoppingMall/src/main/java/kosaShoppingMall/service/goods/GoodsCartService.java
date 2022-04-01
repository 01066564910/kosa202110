package kosaShoppingMall.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.CartDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.MemberMapper;


@Service
public class GoodsCartService {
	@Autowired
	MemberMapper memberMapper; 
	@Autowired
	GoodsMapper goodsMapper;
	public String execute( String goodsNum ,Integer goodsQty ,  HttpSession session ) {
		CartDTO cart = new CartDTO();
		cart.setCartQty(Long.valueOf(goodsQty));
		cart.setGoodsNum(goodsNum);
		cart.setMemberNum((memberMapper.myMemberSelectOne(((AuthInfo)session.getAttribute("authInfo")).getUserId())).getMemberNum());
		return 	goodsMapper.cartAdd(cart).toString();
		
	}
}
