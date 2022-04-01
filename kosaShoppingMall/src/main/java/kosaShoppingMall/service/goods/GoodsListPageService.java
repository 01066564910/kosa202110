package kosaShoppingMall.service.goods;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.LoginMapper;

@Service
public class GoodsListPageService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	LoginMapper loginMapper;
	public void execute(HttpServletRequest request) {
	
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().startsWith("id")) {
					// 사용자한테 다시 받아오기 위해서 request를 받아왔고 내가 원하는 쿠키가 있냐 라고 물어 봣다
					request.setAttribute("isId", cookie.getValue());
				}
				if(cookie.getName().startsWith("autu")) {
					
					AuthInfo authInfo = loginMapper.UserLogin(cookie.getValue());
					HttpSession session = request.getSession();
					session.setAttribute("authInfo" , authInfo);
					
				}
			}
		}
		List<GoodsDTO> list = goodsMapper.goodsSelectAll(new StartEndPageDTO());
		request.setAttribute("lists", list);
		
	
	}

	
	
}
