package school.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import school.domain.DepartmentDTO;
import school.domain.StartEndPageDTO;
import school.mapper.DepartmentMapper;

@Service
public class DepartmentListPageService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(Integer page, Model model , String  searchWord) {
		int limit = 2;
		int limitPage = 2;
		Long startRow = ((long)page -1)*limit +1;
		Long endRow = startRow + limit -1;
		StartEndPageDTO  dto = new StartEndPageDTO();
		dto.setStartRow(startRow);
		dto.setEndRow(endRow);
		dto.setSearchWord(searchWord);
		// 레코드 전체의 갯수
		int count = departmentMapper.getDepartmentCount(searchWord);
		List<DepartmentDTO> list= departmentMapper.getDepartmentSelectPage(dto);
		int maxPage = (int)((double)count/limit +0.9);
		int startPage =  ((int)((double)page / limitPage + 0.9)-1)*limitPage +1 ;
		int endPage =  startPage + limitPage -1;
		if(endPage >maxPage) endPage = maxPage;
		model.addAttribute("count" , count);
		model.addAttribute("list" , list);
		
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		//무슨 페이지인지 알기위해서
		model.addAttribute("page" , page);
		model.addAttribute("searchWord", searchWord);
		
	}
	
}
