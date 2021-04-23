package com.mycompany.sabangpalbang.service;

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

}
