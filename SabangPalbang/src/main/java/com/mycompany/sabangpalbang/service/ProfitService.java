package com.mycompany.sabangpalbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.MemberDao;
import com.mycompany.sabangpalbang.dao.OrderDetailDao;
import com.mycompany.sabangpalbang.dao.OrderMainDao;
import com.mycompany.sabangpalbang.dao.PalbangDao;
import com.mycompany.sabangpalbang.dao.PalbangDetailDao;
import com.mycompany.sabangpalbang.dao.ProductDao;
import com.mycompany.sabangpalbang.dao.SabangDao;
import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.dto.Palbang;
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
	public int getMemberCount() {
		return memberDao.selectMemberCount();
	}
	public int getRecentJoinCount() {
		return memberDao.selectJoinCount();
	}
	public int getBuyMemberCount() {
		return memberDao.selectBuyMemCount();
	}
	public List<Member> getVipMembers() {
		return memberDao.selectVipMemers();
	}
	public List<Member> getInfluencers() {
		return memberDao.selectInfluencers();
	}
	
	//종현 - 사방
	public Sabang getBestSabang() {
		return sabangDao.selectBestSabang();
	}	
	public Product getBestProduct() {
		return productDao.selectBestProduct();
	}
	public Palbang getBestPalbang() {
		return palbangDao.selectBestPalbang();
	}
	public Sabang getSabang(int sabang_id) {
		return sabangDao.selectBySid(sabang_id);
	}
	public Product getProduct(int product_id) {
		return productDao.selectByProduct(product_id);
	}
	public Palbang getPalbang(int palbang_id) {
		return palbangDao.selectByPid(palbang_id);
	}
	
	
	//민상 - 주문
	public List<OrderMain> getMonthJanuary() {
		return orderMainDao.selectByMonthJanuaryBuy();
	}
	public int getTotalCount() {
		return orderMainDao.countView();
	}

	public int getCardPaycount() {
		return orderMainDao.cardpaycount();
	}
	
	public int getDepositPayCount() {
		return orderMainDao.depositpaycount();
	}
	
	public int getPhonePayCount() {
		return orderMainDao.phonepaycount();
	}
}
