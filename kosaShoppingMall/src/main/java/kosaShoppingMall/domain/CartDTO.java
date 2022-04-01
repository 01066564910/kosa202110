package kosaShoppingMall.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartDTO")
public class CartDTO {
	String goodsNum;
	String memberNum;
	//대문자는 null을 받고 소문자 long은 null을 못받는다.
	Long cartQty;
	Long totalPrice;
}
