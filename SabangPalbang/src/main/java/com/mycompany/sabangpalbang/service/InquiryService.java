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
	
	public List<Inquiry> getList(Pager pager, int sid, String ansstate){
		System.out.println("ansstate: " + ansstate);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("inquiry_sabangid", sid);
		map.put("inquiry_ansstate", ansstate);
		List<Inquiry> inquiryList = inquiryDao.selectInquiry(map);
		return inquiryList;
	}

	public int getNoAnsCount(int sid) {
		int row = inquiryDao.countNeedAnswer(sid);
		return row;
	}
	
	public Inquiry getInquiry(int inquiry_id) {
		Inquiry inquiry = inquiryDao.selectInquiryById(inquiry_id);
		return inquiry;
	}
	
	// 관리자가 문의 답변하기 
	public int updateAns(String inquiry_content, int inquiry_id) {
		int row = inquiryDao.updateInquiryAnswerById(inquiry_content, inquiry_id);
		return row;
	}

	// 문의 삭제 
	public void deleteInquiry(int inquiry_id) {
		inquiryDao.deleteInquiryByInquiryId(inquiry_id);
	}
	
	
	
}