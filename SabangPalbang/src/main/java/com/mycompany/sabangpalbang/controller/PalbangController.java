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

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;
import com.mycompany.sabangpalbang.service.PalbangService;

@RestController
@RequestMapping("/palbang_m")
public class PalbangController {
	private static final Logger logger = LoggerFactory.getLogger(PalbangController.class);

	@Autowired
	private PalbangService palbangService;

	@GetMapping("") // 게시물 목록을 보여달라고 할 떼
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		logger.info("palbang_list");
		int totalRows = palbangService.getCount();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Palbang> list = palbangService.getList(pager);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("palbang", list);
		return map;
	}
}
