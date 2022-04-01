package kosaShoppingMall.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import lombok.Data;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	public List<GoodsDTO> execute(Integer page, Model model, String goodsWord) {
		int limit =5;// 리스트 갯수
		int limitPage = 10; // 페이지 번호 갯수
		
		Long startRow = ((long)page -1)*limit +1;
		Long endRow = startRow + limit -1;
		// 마이바티스는 두개를 넣을수가 없어서 하나의 DTO를 만들어서 
		StartEndPageDTO dto = new StartEndPageDTO();
		dto.setStartRow(startRow);
		dto.setEndRow(endRow);
		dto.setGoodsWord(goodsWord);
		int count = goodsMapper.goodsCount(goodsWord); 
		
		List<GoodsDTO> list = goodsMapper.goodsSelectAll(dto);
		
		int maxPage = (int)((double)count/limit + 0.9);
		int startPage =((int)((double)page/limitPage+0.9)-1)*limitPage +1 ;
		
		int endPage = startPage + limitPage -1;
		if(endPage >maxPage) endPage = maxPage;
		model.addAttribute("count" , count);
		model.addAttribute("list" , list);
		
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		//무슨 페이지인지 알기위해서
		model.addAttribute("page" , page);
		model.addAttribute("goodsWord" , goodsWord);
		
		return list;
	}
	
}
