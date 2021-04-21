package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Palbang_detail;

@Mapper
public interface PalbangDetailDao {

	List<Palbang_detail> selectAll(int palbang_id);

}
