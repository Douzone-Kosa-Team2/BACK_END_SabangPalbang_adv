package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Product;

@Mapper
public interface ProductDao {
	List<Product> selectAll(int sabang_id);
	int insertProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(int product_id);

}
