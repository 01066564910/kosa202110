package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EducationDTO;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.domain.StartEndPageDTO;

@Component
@Repository(value="kosaShoppingMall.mapper.EmployeeMapper")
public interface EmployeeMapper {
	// employee
	public Integer employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeSelectAll(StartEndPageDTO dto);
	public EmployeeDTO employeeSelectOne(String empId);
	public Integer employeeUpdate(EmployeeDTO dto);
	public Integer employeeDelete(String empId);
	public Integer employeePwChageOk(EmployeeDTO dto);
	
	public Integer employeedel(String[] delete);
	public int empCount();
	public Integer changePw(AuthInfo authInfo);
		// writer이렇게 쓸 수 있다.
	 // public Integer educationInsert(EducationDTO educationDTO);
	public void educationInserts(List<EducationDTO> listss);

}
