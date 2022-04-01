package kosaShoppingMall.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value="wishDTO")
public class WishDTO {
	String goodsNum;
	String memberNum;
	Date wishDate;
	MemberDTO memberDTO;
}
