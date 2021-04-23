package com.mycompany.sabangpalbang.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Sabang;


@Mapper
public interface OrderMainDao {

	
	public int countView();
	public List<OrderMain> selectOrderByList(Pager pager);
	public OrderMain selectByOid(int order_id);
	public int deleteByOid(int order_id);
	public Sabang selectById(int sabangID);
	public int update(OrderMain order);
	public List<OrderMain> selectOrderByDateUpList(Pager pager);
	public List<OrderMain> selectOrderByDateDownList(Pager pager);
	
	
}
