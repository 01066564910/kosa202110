package kosaShoppingMall.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.DeliveryCommand;
import kosaShoppingMall.domain.DeliveryDTO;
import kosaShoppingMall.mapper.GoodsMapper;
@Service
public class EmpDeliveryUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public Integer execute(DeliveryCommand deliveryCommand) {
		DeliveryDTO dto = new DeliveryDTO();
		dto.setDeliveryCompany(deliveryCommand.getDeliveryCompany());
		dto.setDeliveryNumber(deliveryCommand.getDeliveryNumber());
		dto.setPurchaseNum(deliveryCommand.getPurchaseNum());
		Integer i = goodsMapper.empDeliveryUpdate(dto);
		return i;
	}

}
