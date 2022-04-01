package school.mapper;

import org.springframework.stereotype.Repository;

import school.domain.AuthInfo;
import school.domain.CheckIdEmail;

@Repository("school.mapper.CkMapper")
public interface CkMapper {
	public CheckIdEmail setIdCheck(String UserId);

	public AuthInfo setLogin(String userId);

	public CheckIdEmail setEmailCheck(String userEmail);
}
