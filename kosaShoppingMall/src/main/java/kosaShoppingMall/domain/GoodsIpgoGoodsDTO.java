package kosaShoppingMall.domain;


import org.apache.ibatis.type.Alias;

// 임폴트만 하면 클래스 명만 쓸 수있어서 임폴트 하는것이다.
import lombok.Data;

@Data
@Alias(value = "goodsIpgoGoodsMap")
public class GoodsIpgoGoodsDTO {
	GoodsDTO goodsDTO; // 1
	GoodsIpgoDTO goodsIpgoDTO; // 1
	// 일대 다면이런식으로 한다.
	//  List<GoodsIpgoDTO> ipdto;
	// java.util.Date date;
}
