package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;

@Mapper
public interface PalbangDao {
	public int count();
	public int deleteByPid(int palbang_id);
	public Palbang selectByPid(int palbang_id);
	public Palbang selectBestPalbang();
	public List<Palbang> selectByLike(Pager pager);
	public List<Palbang> selectByView(Pager pager);
	public List<Palbang> selectByNew(Pager pager);
	public List<Palbang> selectByOld(Pager pager);
	
}
