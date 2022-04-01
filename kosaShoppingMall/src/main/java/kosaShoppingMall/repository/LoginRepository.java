package kosaShoppingMall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.command.FindPasswordCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.mapper.LoginMapperimpl;

@Repository
public class LoginRepository  implements LoginMapperimpl{
	@Autowired
	SqlSession sqlSession;
	String namespace="kosaShoppingMall.mapper.LoginMapper";
	String statement;
	@Override
	public AuthInfo UserLogin(String userId) {
		statement = namespace + ".UserLogin";
		return sqlSession.selectOne( statement ,userId) ;
	}
	@Override
	public AuthInfo findId(String email) {
		statement = namespace + ".findId";
		return sqlSession.selectOne( statement ,email) ;
	}
	@Override
	public String findPw(FindPasswordCommand findPasswordCommand) {
		statement = namespace + ".findPw";
		return sqlSession.selectOne( statement ,findPasswordCommand) ;
	}


}
