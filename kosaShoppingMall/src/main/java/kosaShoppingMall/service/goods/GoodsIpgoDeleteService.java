package kosaShoppingMall.service.goods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.GoodsIpgoDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service

public class GoodsIpgoDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		try {
			dto.setIpgoDate(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setGoodsNum(goodsNum);
		goodsMapper.goodsIpgoDelete(dto);
	}

}
