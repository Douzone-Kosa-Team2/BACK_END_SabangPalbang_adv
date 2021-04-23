package com.mycompany.sabangpalbang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.Order_detail;

@Mapper
public interface OrderDetailDao {
	
	public int insert(Order_detail order_detail);
	public List<Order_detail> selectAll();
	public List<Order_detail> selectOrderDetailById(int order_id);
	public int deleteByOrderDetailno(int order_detailno);
	public int insertOrderDetail(Order_detail order_detail);
	public List<Order_detail> selectAll(int order_id);
}
