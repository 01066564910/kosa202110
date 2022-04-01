package kosaShoppingMall.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.WishDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsDetailService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Model model) {

		goodsMapper.goodsVisitCnt(goodsNum);
		 GoodsDTO dto  = goodsMapper.goodsSelectOne(goodsNum);

		 model.addAttribute("goodsCommand" , dto);

	}
	
}
