package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Sabang;

@Mapper
public interface SabangDao {
	public int insertBySabang(Sabang sabang);
	public int selectBySabang(int sabang_id);
	public int updateBySabang(Sabang sabang);
	public String deleteBySabang(int sabang_id);
	public int count();
	// 정렬 4가지 
	public List<Sabang> selectByBuy(Pager pager);
	public List<Sabang> selectByLow(Pager pager);
	public List<Sabang> selectByHigh(Pager pager);
	public List<Sabang> selectByView(Pager pager);
	//사방 아이디에 따른 패키지 가져오기
	public Sabang selectById(int sabang_id);
	public int updateViewCount(int sabang_id);
	public List<Sabang> selectTop3();
	public List<Sabang> selectByPrice(int sabang_saleprice);
	public int updateBuyCount(int sabang_id);
	Sabang selectBySid(int sabang_id);
	int deleteBySid(int sabang_id);
	int insert(Sabang sabang);
	int update(Sabang sabang);
	public Sabang selectBestSabang();
	
}
