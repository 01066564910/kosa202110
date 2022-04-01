package kosaShoppingMall.service.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.FileInfo;

@Service
public class FileDelService {
	public void execute(FileInfo fileInfo , HttpSession session , Model model) {
			
		Integer num = 0;
		Boolean newFile = true;
		//session 이 있는 경우
		List<FileInfo> list  = (List<FileInfo>) session.getAttribute("fileList");
		
		//session이 없는 경우
		if(list == null) {
			list = new ArrayList<FileInfo>();
		}
		
		for(int i=0 ; i<list.size(); i++) {
			//    셉션에 있는애                      //받아온애
			if(list.get(i).getOrgFile().equals(fileInfo.getOrgFile())) {// list = 1 : 이숭무 , 2: 이상범 , 3:이장범 , 
				// 리스트에 있는 것을 										// list.remove(0)  ,list.remove(1) , list.remove(2)
				// 셉션에서 지우면 삭제를 하지않겠다.								// list = 0 : 이숭무 , 1: 이장범
				// 삭제하지 않겠따.
				list.remove(i);
				newFile = false;
				num = 0;
			}
		}
		// if문의 결과는 bollean타입 이므로 boolean타입인 경우 비교연산을 하지 않아도 된다.
		if(newFile) { // 논리 연산과 비교 연산식 : 결과값을 boolean타입을 가져와서
					// 3>2 true
					// true == true : true
					// false == turn : false
			list.add(fileInfo);
			num= 1;
		}
		
		
		session.setAttribute("fileList", list);
		model.addAttribute("val" , num);
		

	}
}
