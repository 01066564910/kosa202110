package kosaShoppingMall.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("orderListDTO")
public class OrderListDTO {
	String goodsNum;
	String confirmNumber;
	String purchaseStatus;
	String goodsName;
	String goodsMain;
	String goodsContent;
	String goodsPrice;
	String deliveryCost;
	// purchase
	String purchaseNum;
	Date purchaseDate;
	String totalPrice;
	String purchaseAddr;
	String purchasePhone;
	String message;
	String recieveName;
	// purchaseList
	String purchaseQty;
	String purchasePrice;
	// payment
	String paymentKind;
	String paymentPrice;
	Date paymentDate;
	String cardNumber;
	// deliveryNumber
	String deliveryNumber;
	String deliveryCompany;
	// 리뷰
	String reviewContent;
	
	
	
}
