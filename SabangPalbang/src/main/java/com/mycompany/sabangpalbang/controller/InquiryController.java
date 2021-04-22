package com.mycompany.sabangpalbang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.Inquiry;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Sabang;
import com.mycompany.sabangpalbang.service.InquiryService;
import com.mycompany.sabangpalbang.service.SabangService;

@RestController
@RequestMapping("inquiry_m")
public class InquiryController {
	
	private static final Logger logger = LoggerFactory.getLogger(PalbangController.class);
	
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private SabangService sabangService;
	
	@GetMapping("") // 사방 목록을 보여달라고 할 떼 
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		int totalRows = sabangService.getCount();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Sabang> sabangs = sabangService.getList(pager);
		
		for(int i=0; i<sabangs.size(); i++) {
			int sid = sabangs.get(i).getSabang_id();
			int totalInquiryNum = inquiryService.getCount(sid);
			int noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangs.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangs.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("sabangs", sabangs);
		return map;
	}
	
	@GetMapping("/{sid}") // 사방의 문의 목록을 보여달라고 할 떼
	public Map<String, Object> inquirylist(@RequestParam(defaultValue = "1") int pageNo, @PathVariable int sid) {
		logger.info("controller - inquiry_list");
		
		int totalRows = inquiryService.getCount(sid);
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		
		List<Inquiry> inquirylist = inquiryService.getList(pager, sid);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("inquirylist", inquirylist);
		
		return map;
	}
	
	// 문의 내용 읽기 
	@GetMapping("/inquiry/{inquiry_id}") 
	public Inquiry inquiry(@PathVariable int inquiry_id) {
		logger.info("controller - 문의내용 읽기");
		
	    Inquiry inquiry = inquiryService.getInquiry(inquiry_id);
	    
	    logger.info("" + inquiry);
	    
		return inquiry;
	}
	
	
	// 문의 답변 남기기 
	@PutMapping("")
	public int answer(@RequestBody Inquiry inquiry) {
		logger.info("controller - 문의 답변 남기기 ");
		
		int row = inquiryService.updateAns(inquiry.getInquiry_anscontent(), inquiry.getInquiry_id());
		 // update 작업을 성공하면 
		return inquiry.getInquiry_id();	
	}
	
	

}
