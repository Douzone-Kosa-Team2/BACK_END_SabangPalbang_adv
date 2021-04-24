package com.mycompany.sabangpalbang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.service.MemberService;

@RestController
@RequestMapping("/member_m")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		int totalRows = memberService.getCount();
		Pager pager = new Pager(6, 5, totalRows, pageNo);
		List<Member> members = memberService.getList(pager);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("members", members);
		return map;
	}

	// 게시물 삭제
	@DeleteMapping("/{member_id}")
	public void delete(@PathVariable int member_id) {
		memberService.deleteMember(member_id);
	}
	
	// 회원 조회 : 아이디(int) 또는 이름(string)
	@GetMapping("/search/{target}")
	public List<Member> searchMember(@PathVariable String target) {
		List<Member> members = memberService.getMemberBySearch(target);
		logger.info("mem size : " + members.size());
		return members;
	}
	
}
