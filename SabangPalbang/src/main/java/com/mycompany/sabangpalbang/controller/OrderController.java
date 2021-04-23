package com.mycompany.sabangpalbang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.dto.Order_detail;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;
import com.mycompany.sabangpalbang.service.OrderService;

@RestController
@RequestMapping("/order_m")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	
	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo){
		logger.info("order_list");
		int totalRows = orderService.getCount();
		Pager pager = new Pager(6, 5, totalRows, pageNo);
		List<OrderMain> orders = orderService.getList(pager);
		
		List<OrderMain> dateUpList = orderService.getDateUpList(pager);
		List<OrderMain> dateDownList = orderService.getDateDownList(pager);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("orders", orders);
		map.put("dateUpList", dateUpList);
		map.put("dateDownList", dateDownList);
		return map;
	}
	
	@GetMapping("/{order_id}")
	public Map<String, Object> read(@PathVariable int order_id) {
		OrderMain order = orderService.getOrder(order_id);
		logger.info(order.getOrder_bankcode()+"");
		
		int sabangID = order.getOrder_sabangid();
		Sabang sabang = orderService.getSabang(sabangID);
		logger.info(sabang.getSabang_id()+"");
		
		List<Order_detail> orderlist = orderService.getOrderDetail(order_id);
		
		List<Product> product = new ArrayList<>();
		for(int i=0; i<orderlist.size(); i++) {
			product.add(orderService.getProduct(orderlist.get(i).getOrder_productid()));	
		}
		int mm = order.getOrder_memberid();
		Member Memail = orderService.getEmail(mm);
		//logger.info(Memail);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("order", order);
		map.put("orderlist", orderlist);
		map.put("product", product);
		map.put("sabang", sabang);
		map.put("Memail", Memail);
		return map;
	}
	
	@PutMapping("") //주문 업데이트
	  public OrderMain update(OrderMain order) {
		logger.info(order.getOrder_zipcode());
		logger.info(order.getOrder_roadaddress());
		logger.info(order.getOrder_detailaddress());
		orderService.update(order);
		
	    return order;
	  }
	
	
	// 주문 삭제
	@DeleteMapping("/{order_id}")
	public void delete(@PathVariable int order_id) {
		orderService.delete(order_id);
	}

}
