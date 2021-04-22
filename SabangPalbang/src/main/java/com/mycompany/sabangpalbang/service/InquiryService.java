package com.mycompany.sabangpalbang.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.sabangpalbang.dao.InquiryDao;
import com.mycompany.sabangpalbang.dto.Inquiry;
import com.mycompany.sabangpalbang.dto.Pager;

@Service
public class InquiryService {

	@Autowired
	private InquiryDao inquiryDao;
	
	public int getCount(int sid) {
		int row = inquiryDao.count(sid);
		return row;
	}
	
	public List<Inquiry> getList(Pager pager, int sid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("inquiry_sabangid", sid);

		List<Inquiry> inquiryList = inquiryDao.selectInquiry(map);
		return inquiryList;
	}

	public int getNoAnsCount(int sid) {
		int row = inquiryDao.countNeedAnswer(sid);
		return row;
	}
	
	//"내가 쓴 문의글 로그인한 계정의 별명으로 문의글 중에 같은 별명인 문의글을 받으려는 문"
//	public List<Inquiry> getInquiryList(Pager pager, String inquiry_writer) {
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("pager", pager);
//		map.put("inquiry_writer", inquiry_writer);
//
//		List<Inquiry> inquiryListNickname = inquiryDao.selectNicknameInquiry(map);
//		return inquiryListNickname;
//	}
//	
//	public Inquiry getInquiryWriter(String nickname) {
//		Inquiry Ilist = inquiryDao.selectInquiryByNickname(nickname);
//		return Ilist;
//	}
//	public int getTotalMyRows(String inquiry_writer) {
//		int row = inquiryDao.mycount(inquiry_writer);
//		return row;
//	}
//
//	// 팝업에서 문의글 작성
//	public void addInquiry(Inquiry inquiry) {
//		inquiryDao.insertInquiryFirst(inquiry);
//		
//	}
//
//	public Inquiry getInquiry(int inquiry_id) {
//		Inquiry inquiry = inquiryDao.selectInquiryById(inquiry_id);
//		return inquiry;
//	}
//	
}