package kosaShoppingMall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
@Repository
public class EmployeeRepository {
	@Autowired
	SqlSession sqlSession;
	String namespace="kosaShoppingMall.mapper.EmployeeMapper";
	String statement;
	// employee
	public Integer employeeInsert(EmployeeDTO dto) {
		statement = namespace +".employeeInsert";
		return sqlSession.insert("kosaShoppingMall.mapper.EmployeeMapper.employeeInsert" , dto);
		
	};
	public List<EmployeeDTO> employeeSelectAll(StartEndPageDTO dto){
		statement = namespace +".employeeSelectAll";
		return sqlSession.selectList(statement , dto);
	};
	public EmployeeDTO employeeSelectOne(String empId) {
		statement = namespace +"employeeSelectOne";
		return sqlSession.selectOne(statement , empId);
		
	};
	public Integer employeeUpdate(EmployeeDTO dto) {
		statement = namespace +".employeeUpdate";
		return sqlSession.update(statement , dto);
	};
	public Integer employeeDelete(String empId) {
		statement = namespace +".employeeDelete";
		return sqlSession.delete(statement , empId);
	};
	public Integer employeePwChageOk(EmployeeDTO dto) {
		statement = namespace +".employeePwChageOk";
		return sqlSession.update(statement , dto);
	};
	
	public Integer employeedel(String[] delete) {
		statement = namespace +".employeedel";
		return sqlSession.delete(statement , delete);
	};
	public int empCount() {
		statement = namespace + ".empCount";
		return sqlSession.selectOne(statement);
	};
	public Integer changePw(AuthInfo authInfo) {
		statement = namespace + ".changePw";
		return sqlSession.update(statement , authInfo);
	};
	
}
