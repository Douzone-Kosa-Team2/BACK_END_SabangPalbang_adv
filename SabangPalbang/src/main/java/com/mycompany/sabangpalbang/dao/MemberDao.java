package com.mycompany.sabangpalbang.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.Pager;

@Mapper
public interface MemberDao {
	// 회원 조회 
	public List<Member> selectMemberByName(HashMap<String, Object> map);
	public List<Member> selectMemberById(HashMap<String, Object> map);
	public int countByID(int member_id);
	public int countByName(String member_name);
	
	public Member selectByPayMember(String member_email);
	public String deleteByMember(String member_email);
	public int updateByMember(Member member);
	
	public String selectEmailByNickname(String palbang_nickname);
	public int selectIdByEmail(String member_email);
	public Member selectMemberByEmail(String member_email);
	public String selectByInquiryNickname(String member_email);
	public Member selectByPostInfo(String member_email);
	public int selectNicknameCheck(String member_nickname);
	public int selectEmailCheck(String member_email);
	public Member selectByMember(int member_id);
	public int count();
	public List<Member> selectMember(Pager pager);
	public int deleteMember(int member_id);
	// 회원 실적 
	public int selectMemberCount();
	public int selectJoinCount();
	public int selectBuyMemCount();
	public List<Member> selectVipMemers();
	public List<Member> selectInfluencers();
	
}
