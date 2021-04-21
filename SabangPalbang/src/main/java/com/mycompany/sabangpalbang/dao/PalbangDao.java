package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;

@Mapper
public interface PalbangDao {
	public int count();
	public List<Palbang> selectByLike(Pager pager);

}
