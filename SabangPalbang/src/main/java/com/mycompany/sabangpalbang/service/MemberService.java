package com.mycompany.sabangpalbang.service;

import java.util.ArrayList;
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

	public List<Member> getMemberBySearch(String target) {
		// 숫자인지 문자열인지 판별해서 다른 dao로 요청하기 
		char tmp;
		boolean output = true; //숫자 
		for(int i=0; i< target.length(); i++) {
			tmp = target.charAt(i);
			if(Character.isDigit(tmp) == false) {
				output = false; // 문자열이다 
			}
		}
		List<Member> members = new ArrayList<>();
		// 숫자이면 
		if(output) {
			members = memberDao.selectMemberById(Integer.parseInt(target));
		}else {
			members = memberDao.selectMemberByName(target);
		}
		return members;
	}

}
