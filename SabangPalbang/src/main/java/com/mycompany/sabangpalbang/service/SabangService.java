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
	
	//사방 생성
	public int insert(Sabang sabang) {
		 return sabangDao.insert(sabang);
	}
	
	
	//사방 수정
	public int update(Sabang sabang) {
		 return sabangDao.update(sabang);
	}

	//사방 게시물 삭제
	public int delete(int sabang_id) {
		return sabangDao.deleteBySid(sabang_id);		
	}

	public int insertProduct(Product product) {
		return productDao.insertProduct(product);
	}

	public int updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	public int deleteProduct(int product_id) {
		return productDao.deleteProduct(product_id);				
	}

	public Product getSabangId(int product_id) {
		Product result = productDao.selectAllProduct(product_id);
		return result;
	}

	public Product getProduct(int product_id) {
		return productDao.selectByProduct(product_id);
	}

}
