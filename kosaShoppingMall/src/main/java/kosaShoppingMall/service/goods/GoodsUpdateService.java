package kosaShoppingMall.service.goods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kosaShoppingMall.command.FileInfo;
import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(GoodsCommand goodsCommand, HttpSession session) {
		GoodsDTO dto = new GoodsDTO();
		dto.setDeliveryCost(goodsCommand.getDeliveryCost());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		
		
		
		//session에 삭제하려는 파일 정보
		List<FileInfo> list = (List<FileInfo>) session.getAttribute("fileList");

		/// 이미지 정보를 가져오기 위해서
		GoodsDTO lib = goodsMapper.goodsSelectOne(goodsCommand.getGoodsNum());
		
		String fileDir = "/view/goods/upload";
		String filePath = session.getServletContext().getRealPath(fileDir);
		
		// 데이터 베이스에 있는 파일 정보를 dto에 저장 수정을 하지 않아도 그 값을 그대로 저장
		dto.setGoodsImages(lib.getGoodsImages()); 
		dto.setGoodsOriginal(lib.getGoodsOriginal());
		
		// file 삭제 session이 있다면 데이터베이스에서 DTO에서 삭제
		// 셉션에 있는것만 지워서 DTO에 저장한다.
		if (list != null && dto.getGoodsOriginal() != null) {
			//dto에 저장된 파일 정보를 스플릿 후 리스트에 저장
			List<String> orgFile = new ArrayList<String>();
			List<String> strFile = new ArrayList<String>();
			// 삭제하기위해서 스플릿해서 데이터를 리스트에 추가햇다.
			for(String s : dto.getGoodsOriginal().split("`")) {
				orgFile.add(s.trim());
			}
				for(String s:dto.getGoodsImages().split("`")) {
					strFile.add(s.trim());
				}
			 
		// file삭제 session이 있다면 데이터베이스에서 dto에서 삭제
				// fi는 session을 이야기한다 
		for (FileInfo fi : list) { // session에 있는 내용과 비교하여 리스트에 있는 파일 정보 삭제
			for(int i =0; i <orgFile.size(); i++) {
				// dto에 있는애랑 셉션에 있는애랑 같은 것이 있다면 dto에 있는 애를 삭제해라 라는 뜻이다.
				if(fi.getOrgFile().equals(orgFile.get(i))&& fi.getKind().trim().equals("img")) {
					orgFile.remove(fi.getOrgFile().trim());
					strFile.remove(fi.getStrFile().trim());
				}
			}
			
		}
		String o = "";
		String s = "";
		//리스트에 있는 내용을 문자열로 변경
		// 삭제했으니 이제 다시 묶어서 저장 
		for(String ostr:orgFile) {
			o +=ostr+"`";
		}
		for(String sstr : strFile){
			s += sstr +"`";
		}
		//문자열을 dto에 저장
		// 묶어서 저장
		dto.setGoodsOriginal(o); //session에 있는 것은 지우고 session에 없는 것만 저장
		dto.setGoodsImages(s);
		}
		
		
		
		
		
		//메인 이미지 저장 같은 이름이 포함되어있으면 문제 다른이름이면 문제가 아님 맨끝의 이름
		if (!goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
			System.out.println("asdasd");
			MultipartFile mf = goodsCommand.getGoodsMain();
			String originalFile = mf.getOriginalFilename();
			String exetension = originalFile.substring(originalFile.lastIndexOf("."));
			String storeName = UUID.randomUUID().toString().replace("-", "");
			String storeFileName = storeName + exetension;
			File file = new File(filePath + "/" + storeFileName);

			try {
				mf.transferTo(file); // 파일을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}

			dto.setGoodsMainOrg(originalFile);
			dto.setGoodsMain(storeFileName);
		}
		/////////
		if (!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()) {
			//storeTotal = lib.getGoodsImages();
			//originalTotal = lib.getGoodsOriginal();을 바꿧다.
			// null인 채로 들어오면 앞에 null이 들어가 때문에
			String storeTotal = "";
			String originalTotal = "";
			if(lib.getGoodsImages() != null) {
			// db에 값이 있으니 값을 초긱ㅄ으로 준다.
			//			  lib라고 하면  lib는 지워진것을 못받아 오기 때문이다..
			 storeTotal = dto.getGoodsImages(); // 널이 아닐때 아니면 앞에 null이 붙늨다.
			 originalTotal = dto.getGoodsOriginal();
			}
			for (MultipartFile mf : goodsCommand.getGoodsImages()) {
				String originalFile = mf.getOriginalFilename();
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				String storeName = UUID.randomUUID().toString().replace("-", "");
				String storeFileName = storeName + extension; // 저장 할 때 사용할 파일명
				storeTotal += storeFileName + "`";
				originalTotal += originalFile + "`";
				File file = new File(filePath + "/" + storeFileName);
				try {
					mf.transferTo(file); // 파일을 저장
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dto.setGoodsOriginal(originalTotal);
			dto.setGoodsImages(storeTotal);

		}

		
		goodsMapper.goodsUpdate(dto);


		// update 된 후에는 session에 있는 파일 삭제
		if (list != null) {
			for (FileInfo fi : list) {
				// 메인 이미지는 메인 이미지를 바꿀때만 삭제 되도록 해주는것.
				if (!goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
					if (fi.getKind().equals("main")) {
						File file = new File(filePath + "/" + fi.getStrFile().replace("`", ""));
						// 존재한다면
						if (file.exists())
							file.delete();
					}
				}
				System.out.println(fi.getStrFile());
				// 셉션에 파일이 있다면 파일을 삭제한다.
				// IMG는 무조건 삭제
				if (fi.getKind().equals("img")) {

					File file = new File(filePath + "/" + fi.getStrFile().replace("`", ""));
					if (file.exists())
						file.delete();
				}
			}
			session.removeAttribute("fileList");

		}
		

	}

}
