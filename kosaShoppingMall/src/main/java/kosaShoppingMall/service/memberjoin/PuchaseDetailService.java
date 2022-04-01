package kosaShoppingMall.service.memberjoin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.OrderListDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class PuchaseDetailService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String purchaseNum , Model model) {
		List<OrderListDTO> list = memberMapper.purchaseDetail(purchaseNum);
		
		model.addAttribute("list" , list);
		
	}
	
}
