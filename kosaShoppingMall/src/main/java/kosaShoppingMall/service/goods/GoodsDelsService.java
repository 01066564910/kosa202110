package kosaShoppingMall.service.goods;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.mapper.GoodsMapper;

@Service
public class GoodsDelsService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(String[] deletes, HttpServletRequest request) {
		
		/*
		 * 4번. //리스트를 map에 저장하는 방법. List<String> cs = new ArrayList<String>();
		 * for(String num:deletes) { cs.add(num); } HashMap<String, Object> condition =
		 * new HashMap<String, Object>(); condition.put("goodsNums", cs); //
		 * goodsMapper.goodsRemove(condition);
		 */

		// 3번
		// HashMap<String, Object> condition = new HashMap<String, Object>();
		// 배열을 map에 저장하여 전달하는 방법
		// condition.put("goodsNums", deletes);
		// goodsMapper.goodsRemove(condition);

		// 2번
		// 배열이 있어야 리스트를 만드는 것은 아니다. 그냥 배열을 이용해서 리스트를 만드는 것이다.
		/*
		 * List<String> cs = new ArrayList<String>(); for(String num:deletes) {
		 * cs.add(num); } goodsMapper.goodsDeletes(cs);
		 */

		// 1번
		// goodsMapper.goodsDels(deletes);

		List<String> cs = new ArrayList<String>();
		for (String num : deletes) {
			cs.add(num);
		}

		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("goodsNums", cs);

		List<GoodsDTO> list = goodsMapper.goodsSelect(condition);

		goodsMapper.goodsRemove(condition);
		String fileDir = "/view/goods/upload";
		String filePath = request.getServletContext().getRealPath(fileDir);

		for (GoodsDTO dto : list) {
			GoodsDTO d = goodsMapper.goodsSelectOne(dto.getGoodsNum()); // 삭제 되어있는지 확인
			if (d == null) { // 삭제가 된 상태
				if (dto.getGoodsImages() != null) {
					String[] fileNames = dto.getGoodsImages().split("`");
					for (String fileName : fileNames) {
						File f = new File(filePath + "/" + fileName);
						if (f.exists())
							f.delete();
					}
				}
				String file = dto.getGoodsMain();
				File f = new File(filePath + "/" + file);
				if (f.exists())
					f.delete();
			}
		}

	}

}
