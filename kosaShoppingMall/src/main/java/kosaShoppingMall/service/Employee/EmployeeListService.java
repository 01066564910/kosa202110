package kosaShoppingMall.service.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.repository.EmployeeRepository;

@Component
@Service
public class EmployeeListService {
	@Autowired
//	EmployeeMapper employeeMapper;
	EmployeeRepository employeeRepository;
	public void execute(Model model, Integer page) {
		int limit =2;
		int limetPage = 2;
		
		Long startRow = ((long)page -1)*limit +1;
		Long endRow = startRow + limit -1;
		StartEndPageDTO dto = new StartEndPageDTO();
		dto.setStartRow(startRow);
		dto.setEndRow(endRow);
		
		int count = employeeRepository.empCount();
		List<EmployeeDTO> list =employeeRepository.employeeSelectAll(dto);
		int maxPage = (int)((double)count / limit+0.9);
		int startPage = ((int)((double)page/limetPage + 0.9)-1)*limetPage+1;
		int endPage = startPage + limetPage -1;
		if(endPage>maxPage)endPage = maxPage;
		
		model.addAttribute("count" , count);
		model.addAttribute("list" , list);
		
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage" , startPage);
		model.addAttribute("endPage" , endPage);
		//무슨 페이지인지 알기위해서
		model.addAttribute("page" , page);
	}
	
}
