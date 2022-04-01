package kosaShoppingMall.service.goods;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kosaShoppingMall.command.GoodsCommand;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsWriteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, HttpServletRequest request) {
		GoodsDTO dto = new GoodsDTO();
		dto.setDeliveryCost(goodsCommand.getDeliveryCost());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		
		//파일 위치 가져오기
		String fileDir = "/view/goods/upload"; //상대경로로 가져왔음
		String filePath=request.getServletContext().getRealPath(fileDir);
		
		//	Main의 emty가 아니라면
		if(!goodsCommand.getGoodsMain().getOriginalFilename().isEmpty()) {
			//goodsCommand롭 부터 가져와서 mf에 넣어주겠다. 없어도 되긴함.
			MultipartFile mf = goodsCommand.getGoodsMain();
			//갖고와서 오리지널 파일네임을 한다.
			String originalFile =mf.getOriginalFilename();
			//오리지널 파일을 가져왔으니  오리지널 파일의 확장자를 가져온다.
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			//스토어 네임을 만들어서 랜덤uuid를 주고 -를 ""로 바꾸겠다.
			String storeName = UUID.randomUUID().toString().replace("-", "");
			// 
			String storeFileName=storeName + extension;
			//파일을 만든다?
			File file = new File(filePath + "/" + storeFileName);
			
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dto.setGoodsMainOrg(originalFile);
				dto.setGoodsMain(storeFileName);	
			}
		if(!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()) {
			//db에 값이 없으니 초기화 해도 된다.
			String storeTotal ="";
			String originalTotal ="";
			for(MultipartFile mf : goodsCommand.getGoodsImages()) {
				String originalFile =mf.getOriginalFilename();
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				String storeName=UUID.randomUUID().toString().replace("-", "");
				String storeFileName=storeName + extension; // 저장 할 때 사용할 파일명
				storeTotal += storeFileName + "`";
				originalTotal += originalFile + "`";
				File file = new File(filePath + "/" + storeFileName);
				try {
					mf.transferTo(file); //파일을 저장
				}catch(Exception e) {e.printStackTrace();}
			}
			dto.setGoodsOriginal(originalTotal);
			dto.setGoodsImages(storeTotal);
			
		}
		
		
		
		
		
		
		goodsMapper.goodsInsert(dto);
		
	}
	
}
