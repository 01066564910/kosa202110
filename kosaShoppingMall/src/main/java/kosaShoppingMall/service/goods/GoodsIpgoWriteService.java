package kosaShoppingMall.service.goods;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.command.GoodsIpgoCommand;
import kosaShoppingMall.domain.GoodsIpgoDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsIpgoWriteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute( GoodsIpgoCommand goodsIpgoCommand ) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		dto.setGoodsNum(goodsIpgoCommand.getGoodsNum());
		dto.setIpgoDate(goodsIpgoCommand.getIpgoDate());
		dto.setIpgoQty(goodsIpgoCommand.getIpgoQty());
		dto.setMadeDate(
				Timestamp.valueOf(goodsIpgoCommand.getMadeDate()));
		System.out.println("1631561651561"+dto.getMadeDate());
		Integer i = goodsMapper.ipgoInsert(dto);
		System.out.println(i +"개의 상품이 입고되었습니다.");
	}

}
