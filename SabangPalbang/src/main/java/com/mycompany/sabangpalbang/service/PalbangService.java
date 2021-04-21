package com.mycompany.sabangpalbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.PalbangDao;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;

@Service
public class PalbangService {

	@Autowired
	private PalbangDao palbangDao;
	
	public int getCount() {
		return palbangDao.count();
	}

	public List<Palbang> getList(Pager pager) {
		
		return palbangDao.selectByLike(pager);
	}

	
}
