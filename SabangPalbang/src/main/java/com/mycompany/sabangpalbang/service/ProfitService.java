package com.mycompany.sabangpalbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.MemberDao;
import com.mycompany.sabangpalbang.dao.OrderDetailDao;
import com.mycompany.sabangpalbang.dao.OrderMainDao;
import com.mycompany.sabangpalbang.dao.PalbangDao;
import com.mycompany.sabangpalbang.dao.PalbangDetailDao;
import com.mycompany.sabangpalbang.dao.ProductDao;
import com.mycompany.sabangpalbang.dao.SabangDao;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;

@Service
public class ProfitService {
	//멤버
	@Autowired
	private MemberDao memberDao;
	//사방
	@Autowired
	private SabangDao sabangDao;
	@Autowired
	private ProductDao productDao;
	//팔방
	@Autowired
	private PalbangDao palbangDao;
	@Autowired
	private PalbangDetailDao palbangDetailDao;
	//주문
	@Autowired
	private OrderMainDao orderMainDao;
	@Autowired
	private OrderDetailDao orderDetailDao;

	
	//채정 - 멤버
	
	//종현 - 사방
	public Sabang getBestSabang() {
		return sabangDao.selectBestSabang();
	}	
	public Product getBestProduct() {
		return productDao.selectBestProduct();
	}
	public Sabang getSabang(int sabang_id) {
		return sabangDao.selectBySid(sabang_id);
	}
	public Product getProduct(int product_id) {
		return productDao.selectByProduct(product_id);
	}
	
	//민상 - 주문




}
