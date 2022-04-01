package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias(value="departmentDTO")
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
	String departmentNum;
	String departmentName;
	String departmentPhone;
	String depatymentAddr;
	String fullAddr;
	String addr1;
	String addr2;
}
