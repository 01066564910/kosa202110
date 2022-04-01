package school.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ckeckIdEmail")
public class CheckIdEmail {
	String userId;
	String userEmail;
}
