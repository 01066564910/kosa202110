package kosaShoppingMall.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.GoodsIpgoDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsIpgoListservice {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(Integer page , Model model) {
		
		
		
		int limit =10;
		int limitPage =10;
		
		Long startRow =   ((long)page -1)*limit +1;
		Long endRow = startRow + limit -1;
		
		StartEndPageDTO dto = new StartEndPageDTO();
		dto.setStartRow(startRow);
		dto.setEndRow(endRow);
		int count = goodsMapper.goodsIpgoCount1();
		List<GoodsIpgoDTO> list = goodsMapper.goodsIpgoSelectAll(dto);
		int maxPage = (int)((double)count / limit +0.9);
		int startPage =  ((int)((double)page / limitPage + 0.9)-1)*limitPage +1  ;
		int endPage =  startPage + limitPage -1;
		if(endPage >maxPage) endPage = maxPage;
		model.addAttribute("count" , count);
		model.addAttribute("list" , list);
		
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		//무슨 페이지인지 알기위해서
		model.addAttribute("page" , page);

	}
}
