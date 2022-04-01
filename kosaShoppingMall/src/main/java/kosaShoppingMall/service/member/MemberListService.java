package kosaShoppingMall.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;

	public void execute(Model model, Integer page , String memberSearch) {
		int limit = 2;
		int limitPage = 2;
		// page = 2; startRow = 11 , endRow = 20
		// page = 3; startRow = 21 , endRow = 30
		// page = 4; startRow = 31 , endRow = 40
		Long startRow = ((long)page -1)*limit +1;
		Long endRow = startRow + limit -1;
		// 마이바티스는 두개를 넣을수가 없어서 하나의 DTO를 만들어서 넣기 위해서이다.
		StartEndPageDTO dto = new StartEndPageDTO();
		dto.setStartRow(startRow);
		dto.setEndRow(endRow);
		dto.setGoodsWord(memberSearch);
		// 레코드의 전체의 갯수
		int count = memberMapper.memberCount(memberSearch);
		List<MemberDTO> list = memberMapper.memberSelectAll(dto);
				//   인트(정수값)만 갖고오기 위해서 실수(double)의 값을 더했다.
		int maxPage = (int)((double)count / limit +0.9); // 21/10 = 2.1 + 0.9
		// page = 4;  starPage = 1 , //   endPage 10
		// page = 14; startPage = 11 , // endPage 20
		// page = 24; startPage = 21 , // endPage 30
		// 토탈 page: 25
		// 10을 하면
		//	int startPage =  (int)(page / limitPage)+1;
		int startPage =  ((int)((double)page / limitPage + 0.9)-1)*limitPage +1  ;
		// 							24/10 = 2.4 + 0.9 = 3.3 =>3 -1 =2 *10 + 1 =21
		int endPage =  startPage + limitPage -1;
		if(endPage >maxPage) endPage = maxPage;
		model.addAttribute("count" , count);
		model.addAttribute("list" , list);
		
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		//무슨 페이지인지 알기위해서
		model.addAttribute("page" , page);
		model.addAttribute("memberSearch" , memberSearch);
		
	}
	
}
