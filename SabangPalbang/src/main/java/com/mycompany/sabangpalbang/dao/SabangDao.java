package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Sabang;

@Mapper
public interface SabangDao {
	List<Sabang> selectByBuy(Pager pager);
	int count();
	Sabang selectBySid(int sabang_id);
	int deleteBySid(int sabang_id);
	int insert(Sabang sabang);
	int update(Sabang sabang);
}
