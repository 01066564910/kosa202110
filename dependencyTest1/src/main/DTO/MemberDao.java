package main.DTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {
	// static는 다른데서 종료가 됐다 하더라도 Map가 계속 남아있게 하기 위해서
	// 다오가 실행이 종료 되었다 하더라도 여기의 Map이라는 변수는 살아있게 하도록 하기 위해서
	static Map<String , MemberDTO> map = new HashMap<String , MemberDTO>();
	static long nextId = 0;
	
	public void update(MemberDTO dto) {
		map.put(dto.getEmail(), dto); // 풋하면 저장인데 같은 키가 있으면 업데이트 맵은 키가 같은게 있으면 업데이트 없으면 생성.
	}
	
	
	
	public MemberDTO selectByEmail(String email) {
		return map.get(email);
	}
	
	
	public Collection<MemberDTO> selectAll(){
		return map.values(); //밸류만 갖고오면 리스트 // 키만 갖고올수도있다. //list의 최상위 객체를 Collection이다.
	}
		
	
	
	
	
	
	public void insert(MemberDTO dto) {
		dto.setId(++nextId);
		map.put(dto.getEmail(), dto); // 이메일을 스트롱을 주고 dto에 저장 하겠다.?
		
		
	}
	
}
