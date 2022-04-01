package kosaShoppingMall.service.member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.mapper.MemberMapper;

@Service
public class MemberDelService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String[] delete) {
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("memberNum", delete);
		memberMapper.memberDel(condition);
	}
	
}
