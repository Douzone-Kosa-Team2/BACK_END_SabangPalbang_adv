package com.mycompany.sabangpalbang.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profit_m")
public class ProfitController {
	private static final Logger logger = LoggerFactory.getLogger(ProfitController.class);
	
	//채정 - 회원 실적
	@RequestMapping("/member")
	public class ProfitMember{
		@GetMapping("")
		public void list() {
		
		}
	}
	
	
	//종현 - 사방 실적
	@RequestMapping("/sabang")
	public class ProfitSabang{
		@GetMapping("")
		public void list() {
		
		}
	}
	
	
	//민상 - 주문 실적
	@RequestMapping("/order")
	public class ProfitOrder{
		@GetMapping("")
		public void list() {
		
		}
	}
}
