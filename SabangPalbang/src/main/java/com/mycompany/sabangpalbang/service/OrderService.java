package com.mycompany.sabangpalbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.MemberDao;
import com.mycompany.sabangpalbang.dao.OrderDetailDao;
import com.mycompany.sabangpalbang.dao.OrderMainDao;
import com.mycompany.sabangpalbang.dao.ProductDao;
import com.mycompany.sabangpalbang.dao.SabangDao;
import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.dto.Order_detail;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;

@Service
public class OrderService {

	@Autowired
	private OrderMainDao orderMainDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SabangDao sabangDao;
	
	@Autowired
	private MemberDao memberDao;
	
	public int getCount() {
		return orderMainDao.countView();
	}

	public List<OrderMain> getList(Pager pager) {
		return orderMainDao.selectOrderByList(pager);
	}

	public OrderMain getOrder(int order_id) {
		return orderMainDao.selectByOid(order_id);
	}

	public List<Order_detail> getOrderDetail(int order_id) {
		List<Order_detail> orderdetail = orderDetailDao.selectAll(order_id);
		return orderdetail;
	}

	public int delete(int order_id) {
		return orderMainDao.deleteByOid(order_id);
	}

	public Sabang getSabang(int sabang_id) {
		Sabang sabang = sabangDao.selectById(sabang_id);
		return sabang;
		
	}

	public Product getProduct(int product_id) {
		Product product = productDao.selectByProduct(product_id);
		return product;
	}

	public Member getEmail(int order_memberid) {
		Member member = memberDao.selectByMember(order_memberid);
		return member;
	}

	public int update(OrderMain order) {
		return orderMainDao.update(order);
		
	}

	public List<OrderMain> getDateUpList(Pager pager) {
		return orderMainDao.selectOrderByDateUpList(pager);
	}

	public List<OrderMain> getDateDownList(Pager pager) {
		return orderMainDao.selectOrderByDateDownList(pager);
	}

	public List<OrderMain> getWaitForPayList(Pager pager) {
		return orderMainDao.selectOrderByWaitForPayList(pager);
	}

	public List<OrderMain> getPaySuccessList(Pager pager) {
		return orderMainDao.selectOrderByPaySuccessList(pager);
	}
	
	public List<OrderMain> getPostingList(Pager postingpager) {
		return orderMainDao.selectOrderByPostingList(postingpager);
	}
	
	public List<OrderMain> getPostReadyList(Pager postReadypager) {
		return orderMainDao.selectOrderByPostReadyList(postReadypager);
	}
	
	public List<OrderMain> getPostSuccessList(Pager postSuccesspager) {
		return orderMainDao.selectOrderByPostSuccessList(postSuccesspager);
	}

	
	public int getWaitForPayCount() {
		return orderMainDao.waitForPaycount();
	}

	public int getPaySuccessCount() {
		return orderMainDao.paySuccesscount();
	}

	public int getPostReadyCount() {
		return orderMainDao.postReadycount();
	}

	public int getPostingCount() {
		return orderMainDao.postingcount();
	}

	public int getPostSuccessCount() {
		return orderMainDao.postSuccesscount();
	}

	
	

	


	
	
	
}
