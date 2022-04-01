package kosaShoppingMall.service.memberjoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.MemberMapper;

@Service
public class PurchaseDelService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String purchaseNum) {
		memberMapper.PurchaseDel(purchaseNum);
		
	}

}
