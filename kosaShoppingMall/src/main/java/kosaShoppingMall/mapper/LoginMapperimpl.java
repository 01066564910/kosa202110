package kosaShoppingMall.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.command.FindPasswordCommand;
import kosaShoppingMall.domain.AuthInfo;

@Component

public interface LoginMapperimpl {
	public AuthInfo UserLogin(String userId);
	public AuthInfo findId(String email);
	public String findPw(FindPasswordCommand findPasswordCommand);
}
