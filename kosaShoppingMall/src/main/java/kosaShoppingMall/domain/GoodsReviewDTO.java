package kosaShoppingMall.domain;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Alias("goodsReviewDTO")
public class GoodsReviewDTO {
	GoodsDTO goodsDTO;
	ReviewDTO reviewDTO;
}
