package kosaShoppingMall.service.memberjoin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.CartDTO;
import kosaShoppingMall.domain.GoodsCartDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Service
public class GoodsCartListService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(HttpSession session , Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memberNum = memberMapper.myMemberSelectOne(authInfo.getUserId()).getMemberNum();
		List<GoodsCartDTO> list = memberMapper.cartList(memberNum);
		model.addAttribute("lists" ,list);
	}
	
}
