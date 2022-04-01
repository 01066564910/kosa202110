
package kosaShoppingMall.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.CartDTO;
import kosaShoppingMall.domain.GoodsBuy;
import kosaShoppingMall.domain.GoodsCartDTO;
import kosaShoppingMall.domain.GoodsReviewDTO;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.domain.OrderListDTO;
import kosaShoppingMall.domain.PaymentDTO;
import kosaShoppingMall.domain.PurchaseDTO;
import kosaShoppingMall.domain.PurchaseListDTO;
import kosaShoppingMall.domain.ReviewDTO;
import kosaShoppingMall.domain.StartEndPageDTO;

@Component
@Repository(value="kosaShoppingMall.mapper.MemberMapper")
public interface MemberMapper {
	// member
	public String memberMaxNum();
	public Integer memberInsert(MemberDTO dto);
	public List<MemberDTO> memberSelectAll(StartEndPageDTO dto);
	public MemberDTO memberSelectOne(String memberNum);
	public Integer memberUpdate(MemberDTO dto);
	public Integer memberDelete( String memberNum);
	// memberJoin , myPage 
	public Integer memberJoinInsert(MemberDTO dto);
	public MemberDTO myMemberSelectOne(String userId);
	public Integer myPageMemberUpdate(MemberDTO dto);
	public Integer myPageMemberPw(MemberDTO dto);
	public Integer memberDeletes(String userId);
	public Integer memberDel(HashMap<String, Object> condition);
	public int memberCount(String memberSearch);
	// 비밀번호 찾기.
	public Integer changePw(AuthInfo authInfo);
	//myPage
	public Integer joinOkUpdate(MemberDTO dto);
	public List<GoodsCartDTO> cartList(String memberNum);
	public MemberDTO memberSelectOneId(String userId);
	public Integer goodsCartQtyDown(CartDTO dto);
	public List<GoodsCartDTO> goodsOrder(GoodsBuy goodsBuy);
	
	//구매 

	public Integer cartDel(GoodsBuy goodsBuy);
	public Integer purchase(PurchaseDTO dto);
	public Integer purchaseList(PurchaseListDTO purchaseListDTO);
	public Integer cartGoodsDel(GoodsBuy goodsBuy);
	
	public List<OrderListDTO> orderList(String memberNum);
	public Integer payment(PaymentDTO paymentDTO);
	public Integer purchaseStatus(String purchaseNum);
	
	public Integer paymentDelete(String purchaseNum);
	public Integer purchaseStatusBack(String purchaseNum);
	public Integer PurchaseDel(String purchaseNum);
	public Integer puchaseDels(String[] purchaseNum);
	public List<OrderListDTO> purchaseDetail(String purchaseNum);
	
	public GoodsReviewDTO reviewSelect(ReviewDTO reviewDTO);
	public Integer reviewUpdate(ReviewDTO reviewDTO);
	public Integer reviewDelete(ReviewDTO dto);
	public Integer goodsInquireDelete(String inquireNum);

}
