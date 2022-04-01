package kosaShoppingMall.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsInquireCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.GoodsInquireDTO;
import kosaShoppingMall.mapper.GoodsMapper;
import kosaShoppingMall.mapper.MemberMapper;
@Service
public class GoodsinquireWriteService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(String goodssNum, GoodsInquireCommand goodsInquireCommand, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String memberNum = ((memberMapper.myMemberSelectOne(authInfo.getUserId()).getMemberNum()));
		GoodsInquireDTO dto = new GoodsInquireDTO();
		dto.setGoodsNum(goodssNum);
		dto.setInquireContent(goodsInquireCommand.getInquireContent());
		dto.setInquireKind(goodsInquireCommand.getHchkQueryType());
		dto.setInquireSubject(goodsInquireCommand.getInquireSubject());
		dto.setMemberNum(memberNum);
		dto.setAnswerEmail(goodsInquireCommand.getEmail1() + "@" + goodsInquireCommand.getEmail2());
		goodsMapper.goodsinquireWrite(dto);
	}

}
