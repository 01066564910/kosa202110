package kosaShoppingMall.service.memberjoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.MemberMapper;

@Service
public class PaymentDelService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String purchaseNum) {
		Integer i = memberMapper.paymentDelete(purchaseNum);
		if(i ==1) {
			memberMapper.purchaseStatusBack(purchaseNum);
		}
	}
}
