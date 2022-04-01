package kosaShoppingMall.service.memberjoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.MemberMapper;

@Service
public class PurchaseDelsService {
	@Autowired
	MemberMapper memberMapper;
	public Integer execute(String[] purchaseNum) {
		
		return memberMapper.puchaseDels(purchaseNum);
		
	}
	
}
