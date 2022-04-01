package kosaShoppingMall.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias(value="memberDTO")
//모든 생성장
@AllArgsConstructor
// 기본 생성자
@NoArgsConstructor

public class MemberDTO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberName;
	String memberAddr;
	Date memberRegist;
	String gender;
	String memberPhone;
	Date memberBirth;
	String memberOk;
	String memberEmail;
	
	GoodsDTO goodsDTO;

	public MemberDTO(String memberNum, String memberId, String memberPw, String memberName, String memberAddr,
			Date memberRegist, String gender, String memberPhone, Date memberBirth, String memberOk,
			String memberEmail) {
		super();
		this.memberNum = memberNum;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAddr = memberAddr;
		this.memberRegist = memberRegist;
		this.gender = gender;
		this.memberPhone = memberPhone;
		this.memberBirth = memberBirth;
		this.memberOk = memberOk;
		this.memberEmail = memberEmail;
	}
	
}
