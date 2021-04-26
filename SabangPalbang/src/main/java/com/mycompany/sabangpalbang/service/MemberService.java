package com.mycompany.sabangpalbang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.MemberDao;
import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.Pager;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	

	public int getCount() {
		return memberDao.count();
	}


	public List<Member> getList(Pager pager) {
		return memberDao.selectMember(pager);
	}


	public int deleteMember(int member_id) {
		return memberDao.deleteMember(member_id);
		
	}

	// 아이디 검색 
	public int getCountByID(int target) {
		return memberDao.countByID(target);
	}
	public List<Member> getMemberById(Pager pager, int target) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("member_id", target);
		
		List<Member> members = new ArrayList<>();
		members = memberDao.selectMemberById(map);
		return members;
	}

	// 이름 검색 
	public int getCountByName(String target) {
		return memberDao.countByName(target);
	}
	public List<Member> getMemberByName(Pager pager, String target) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("member_name", target);
		
		List<Member> members = new ArrayList<>();
		members = memberDao.selectMemberByName(map);
		return members;
	}

}
