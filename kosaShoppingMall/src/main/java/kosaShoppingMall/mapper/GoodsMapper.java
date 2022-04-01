package kosaShoppingMall.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.command.DeliveryCommand;
import kosaShoppingMall.domain.CartDTO;
import kosaShoppingMall.domain.DeliveryDTO;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.GoodsInquireDTO;
import kosaShoppingMall.domain.GoodsIpgoDTO;
import kosaShoppingMall.domain.GoodsIpgoGoodsDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.OrderListDTO;
import kosaShoppingMall.domain.ReviewDTO;
import kosaShoppingMall.domain.StartEndPageDTO;
import kosaShoppingMall.domain.WishDTO;

@Component
@Repository(value="kosaShoppingMall.mapper.GoodsMapper")
public interface GoodsMapper {
	public String goodsAutoNum();
	public Integer goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> goodsSelectAll(StartEndPageDTO dto);
	public void goodsVisitCnt(String goodsNum);
	public GoodsDTO goodsSelectOne(String goodsNum);
	public Integer goodsUpdate(GoodsDTO dto);
	public Integer goodsDelete(String goodsNum);
	public List<GoodsDTO> searchGoods(String goodsWord);
	/////        goodsIpgo  /////////////////
	
	public Integer ipgoInsert(GoodsIpgoDTO dto);
	public List<GoodsDTO> goodsItems(StartEndPageDTO dto);
	public List<GoodsIpgoDTO> goodsIpgoSelectAll(StartEndPageDTO dto);
	// 1:1 정보.굿즈 정보 하나에 입고정보 하나 
	public GoodsIpgoGoodsDTO ipgoDetail(GoodsIpgoDTO idto);
	public GoodsDTO getGoodsIpgoInfo(GoodsIpgoDTO idto);
	public Integer goodsIpgoUpdate(GoodsIpgoDTO dto);
	public Integer goodsIpgoDelete(GoodsIpgoDTO dto);
	/// 02-29수업 내용
	// 배열을 이용한 방법
	public Integer goodsDels(String[] deletes);
	// 리스트를 이용한 방법
	public Integer goodsDeletes(List<String> cs);
	// map을 사용하는 방법
	public Integer goodsRemove(HashMap<String, Object> condition);
	//삭제
	public Integer goodsIpgoDels(List<String[]> condition);
	public Integer goodsCount(String goodsWord);
	public int goodsIpgoCount(String goodsWord);
	public int goodsIpgoCount1();
	
	public List<GoodsDTO> goodsSelect(HashMap<String, Object> condition);
	
	
	public Integer wishAdd(WishDTO dto);
	public String wishCount(WishDTO dto);
	public Integer cartAdd(CartDTO cart);
	// 주문내역
	public List<OrderListDTO> purchaseList();
	public List<OrderListDTO> purchaseEmpDetail(String purchaseNum);
	public Integer statusUpdate(String purchaseNum);
	
	public Integer deliveryInsert(DeliveryCommand deliveryCommand);
	public Integer deliveryStatus(String purchaseNum);
	public Integer empDeliveryUpdate(DeliveryDTO dto);
	public Integer deliveryDelete(String purchaseNum);
	// 리뷰
	public Integer reviewWrite(ReviewDTO dto);
	public List<ReviewDTO> reviewList(String goodsNum);
	public Integer goodsinquireWrite(GoodsInquireDTO dto);
	public List<GoodsInquireDTO> goodsInquireList(String goodsNum);
	public List<GoodsInquireDTO> goodsInquire();
	public GoodsInquireDTO goodsInquire(String inquireNum);
	public Integer setInquireAnswer(GoodsInquireDTO goodsInquireDTO);
	public GoodsInquireDTO goodsinquireUpdate(String inquireNum);
	public Integer setGoodsInquireUpdate(GoodsInquireDTO dto);
	public String getWishSelectOne(MemberDTO memberDTO);

	

}
