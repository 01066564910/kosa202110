package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsInquireCommand;
import kosaShoppingMall.domain.GoodsInquireDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsInquireUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsInquireCommand goodsInquireCommand) {
		GoodsInquireDTO dto = new GoodsInquireDTO();
		dto.setInquireNum(goodsInquireCommand.getInquireNum());
		dto.setInquireKind(goodsInquireCommand.getHchkQueryType());
		dto.setInquireSubject(goodsInquireCommand.getInquireSubject());
		dto.setInquireContent(goodsInquireCommand.getInquireContent());
		dto.setAnswerEmail(goodsInquireCommand.getEmail1()+"@"+goodsInquireCommand.getEmail2());
			
		goodsMapper.setGoodsInquireUpdate(dto);
	}

}
