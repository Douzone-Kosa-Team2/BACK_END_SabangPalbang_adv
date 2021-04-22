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
import com.mycompany.sabangpalbang.service.InquiryService;

@RestController
@RequestMapping("inquiry_m")
public class InquiryController {
	
	private static final Logger logger = LoggerFactory.getLogger(PalbangController.class);
	
	@Autowired
	private InquiryService inquiryService;

	@GetMapping("") // 사방 패키지의 문의 목록을 보여달라고 할 떼
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, int sid) {
		logger.info("inquiry_list");
		int totalRows = inquiryService.getCount(sid);
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Inquiry> palbangs = inquiryService.getList(pager, sid);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("palbang", palbangs);
		return map;
	}

	

}
