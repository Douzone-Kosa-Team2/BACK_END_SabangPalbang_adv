package com.mycompany.sabangpalbang.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
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
import com.mycompany.sabangpalbang.service.SabangService;

@RestController
@RequestMapping("/order_m")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SabangService sabangService;
	
	
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

	// 사방 이미지 출력
	@GetMapping("/sattach/{sabang_id}")
	public void download(@PathVariable int sabang_id, HttpServletResponse response) {
		try {
			Sabang sabang = sabangService.getSabang(sabang_id);
			String sattachoname = sabang.getSabang_imgoname();
			if (sattachoname == null)
				return;
			sattachoname = new String(sattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String sattachsname = sabang.getSabang_imgsname();
			String sattachspath = "C:/Users/ant94/git/SabangPalbang_upload/images/sabang_post/" + sattachsname;
			String sattachtype = sabang.getSabang_imgtype();

			response.setHeader("Content-Disposition", "attachment; filename=\"" + sattachoname + "\";");
			response.setContentType(sattachtype);

			InputStream is = new FileInputStream(sattachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 상품 이미지 출력
	@GetMapping("/pattach/{product_id}")
	public void downloadProduct(@PathVariable int product_id, HttpServletResponse response) {
		try {
			Product product = sabangService.getProduct(product_id);
			String pattachoname = product.getProduct_imgoname();
			if (pattachoname == null)
				return;
			pattachoname = new String(pattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String pattachsname = product.getProduct_imgsname();
			String pattachspath = "C:/Users/ant94/git/SabangPalbang_upload/images/sabang_detail/" + pattachsname;
			String pattachtype = product.getProduct_imgtype();

			response.setHeader("Content-Disposition", "attachment; filename=\"" + pattachoname + "\";");
			response.setContentType(pattachtype);

			InputStream is = new FileInputStream(pattachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
