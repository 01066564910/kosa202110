package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias(value="goodsDTO")
public class GoodsCommand {
	@NotBlank(message = "번호를 입력해주세요.")
	String goodsNum;
	@NotBlank(message = "이름을 입력해 주세요.")
	String goodsName;
	@NotNull(message = "가격을 입력해주세요")
	Integer goodsPrice; 
	@NotBlank(message = "내용을 입력해주세요")
	String goodsContent;
	@NotNull(message = "배송비를 입력해주세요")
	Integer deliveryCost;
	///파일을 받을 떄 사용
	//용량도 작고 전송속도도 느려서 회사가서는 안씀 다른 객체를 쓴다.
	
	MultipartFile goodsMain;
	MultipartFile [] goodsImages;
	
	String goodsMainOrg;
	String goodsOriginal;
}
