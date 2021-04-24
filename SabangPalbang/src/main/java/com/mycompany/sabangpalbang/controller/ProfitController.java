package com.mycompany.sabangpalbang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.service.OrderService;

@RestController
@RequestMapping("/profit_m")
public class ProfitController {
	private static final Logger logger = LoggerFactory.getLogger(ProfitController.class);
	
	
	@Autowired
	private OrderService orderService;
	
	//채정 - 회원 실적
	@GetMapping("/member")
	public void test1() {
		logger.info("test");
		
	}
	
	
	//종현 - 사방 실적
	@GetMapping("/sabang")
	public void test2() {
		logger.info("test2");
		
	}
	
	
	//민상 - 주문 실적
	@GetMapping("/order")
	public Map<String, Object> orderperformance() {
		logger.info("주문실적");
		List<OrderMain> monthbuy1 = orderService.getMonthJanuary();
		
		Map<String, Object> map = new HashMap<>();
		map.put("monthbuy1", monthbuy1);
		return map;
		
	}
}
