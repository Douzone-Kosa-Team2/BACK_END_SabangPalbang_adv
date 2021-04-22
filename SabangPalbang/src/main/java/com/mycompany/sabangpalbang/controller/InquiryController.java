package com.mycompany.sabangpalbang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/sabangs") // 사방 목록을 보여달라고 할 떼 
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		logger.info("api 여기로 들어옴 ");
		
		int totalRows = sabangService.getCount();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Sabang> sabangs = sabangService.getList(pager);
		
		// 서비스에서 문의 수 구해오는거 사방 dto에 담아서 넘겨주기 
		int sid = 0;
		int totalInquiryNum = 0;
		int noAnsInquiryNum = 0;
		for(int i=0; i<sabangs.size(); i++) {
			sid = sabangs.get(i).getSabang_id();
			totalInquiryNum = inquiryService.getCount(sid);
			noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangs.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangs.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("sabangs", sabangs);
		return map;
	}
	
//	@GetMapping("") // 사방 패키지의 문의 목록을 보여달라고 할 떼
//	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, int sid) {
//		logger.info("inquiry_list");
//		int totalRows = inquiryService.getCount(sid);
//		Pager pager = new Pager(5, 5, totalRows, pageNo);
//		List<Inquiry> inquirylist = inquiryService.getList(pager, sid);
//		Map<String, Object> map = new HashMap<>();
//		map.put("pager", pager);
//		map.put("inquiry", inquirylist);
//		
//		//map.put();
//		return map;
//	}


	

}
