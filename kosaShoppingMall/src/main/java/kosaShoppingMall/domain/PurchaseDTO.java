package kosaShoppingMall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseDTO {
	String purchaseNum;
	Date purchaseDate;
	Long totalPrice;
	String purchaseAddr;
	String purchasePhone;
	String message;
	String purchaseStatus; // 구매확정이되면 리뷰 버튼이 열립니다.
	String memberNum;
	String recieveName;
}
