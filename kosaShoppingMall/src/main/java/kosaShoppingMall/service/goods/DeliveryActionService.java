package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.DeliveryCommand;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class DeliveryActionService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(DeliveryCommand deliveryCommand) {
		Integer i = goodsMapper.deliveryInsert(deliveryCommand);
		if(i  > 0) {
				// 상품 대기중 ==> 배송중 으로 바꾸지만 실제 사이트에서는
				// 우리는 없기떄ㅔ문에 배송 완료로 바뀐다.
				goodsMapper.deliveryStatus(deliveryCommand.getPurchaseNum());
			
		}
	}
}
