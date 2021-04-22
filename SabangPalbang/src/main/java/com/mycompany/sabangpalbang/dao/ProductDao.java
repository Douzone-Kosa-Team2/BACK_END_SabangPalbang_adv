package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Product;

@Mapper
public interface ProductDao {

	List<Product> selectAll(int sabang_id);

}
