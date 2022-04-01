package kosaShoppingMall.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias(value="goodsDTO")
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	String goodsContent;
	
  
	Integer deliveryCost;     
	Integer visitCount;   
	//컨텐츠 이미지
	String goodsImages; //content 이미지에 대한  storeFileName
	String goodsOriginal; // content 이미지에 대한 originalFileName
	
	String goodsMain; // 대문 이미지에 대한  storeFileName
	String goodsMainOrg; // 대문 이미지에 대한 originalFileName
	
	
	GoodsIpgoDTO goodsIpgoDTO;
	
	public GoodsDTO(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	
}
