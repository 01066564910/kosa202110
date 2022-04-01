package kosaShoppingMall.command;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class GoodsInquireCommand {
	Integer inquireNum;
	String hchkQueryType;
	String inquireContent;
	String inquireSubject;
	String email1;
	String email2;
}
