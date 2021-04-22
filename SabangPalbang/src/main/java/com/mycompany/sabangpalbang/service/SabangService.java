package com.mycompany.sabangpalbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.ProductDao;
import com.mycompany.sabangpalbang.dao.SabangDao;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;

@Service
public class SabangService {

	@Autowired
	private SabangDao sabangDao;
	@Autowired
	private ProductDao productDao;
	
	//사방 게시판
	public List<Sabang> getList(Pager pager) {
		return sabangDao.selectByBuy(pager);
	}

	public int getCount() {
		return sabangDao.count();
	}

	//사방 안에 상품 출력
	public Sabang getSabang(int sabang_id) {
		return sabangDao.selectBySid(sabang_id);
	}

	public List<Product> getProducts(int sabang_id) {
		List<Product> productlist = productDao.selectAll(sabang_id);
		return productlist;
	}

	
	//사방 게시물 삭제
	public int delete(int sabang_id) {
		return sabangDao.deleteBySid(sabang_id);		
	}

	public void update(Sabang sabang) {
		// TODO Auto-generated method stub
		
	}

}
