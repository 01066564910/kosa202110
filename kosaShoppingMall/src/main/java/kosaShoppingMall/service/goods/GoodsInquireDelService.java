package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsInquireDelService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String inquireNum) {
		memberMapper.goodsInquireDelete(inquireNum);
	}
}
