package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("integrityCk")
public class IntegrityCk {
	String departmentNum;
	String subjectNum;
	String professorNum;
}
