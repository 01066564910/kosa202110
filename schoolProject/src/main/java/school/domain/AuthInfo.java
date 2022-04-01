package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authInfoDTO")
public class AuthInfo {
	String userId;
	String userPw;
	String grade;
	
	
}
