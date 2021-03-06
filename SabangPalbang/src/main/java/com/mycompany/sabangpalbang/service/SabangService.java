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
	
	public int getCount() {
		return sabangDao.count();
	}
	
	public List<Sabang> getSabangList_Buy(Pager pager) {
		List<Sabang> list = sabangDao.selectByBuy(pager);
		return list;	
	}
	
	public List<Sabang> getSabangList_Low(Pager pager) {
		List<Sabang> list = sabangDao.selectByLow(pager);
		return list;	
	}
	
	public List<Sabang> getSabangList_High(Pager pager) {
		List<Sabang> list = sabangDao.selectByHigh(pager);
		return list;	
	}
	
	public List<Sabang> getSabangList_View(Pager pager) {
		List<Sabang> list = sabangDao.selectByView(pager);
		return list;	
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

	public Product getProduct(int product_id) {
		return productDao.selectByProduct(product_id);
	}

	//상품 추가 삭제시 가격 변경
	public int updatePrice(Product product) {
		return sabangDao.updatePrice(product);
	}

	public int getSabangSaleingCount() {
		return sabangDao.saleingcount();
	}

	public List<Sabang> getSabangSaleingList(Pager sabangSaleingPager) {
		return sabangDao.selectBySaleingList(sabangSaleingPager);
	}

	public int getSabangSaleReadyCount() {
		return sabangDao.salereadycount();
	}

	public List<Sabang> getSabangSaleReadyList(Pager sabangSaleReadyPager) {
		return sabangDao.selectBySaleReadyList(sabangSaleReadyPager);
	}

	public int getSabangSaleStopCount() {
		return sabangDao.salestopcount();
	}

	public List<Sabang> getSabangSaleStopList(Pager sabangSaleStopPager) {
		return sabangDao.selectBySaleStopList(sabangSaleStopPager);
	}

}
