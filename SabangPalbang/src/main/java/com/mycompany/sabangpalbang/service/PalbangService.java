package com.mycompany.sabangpalbang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.sabangpalbang.dao.PalbangDao;
import com.mycompany.sabangpalbang.dao.PalbangDetailDao;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;
import com.mycompany.sabangpalbang.dto.Palbang_detail;

@Service
public class PalbangService {

	@Autowired
	private PalbangDao palbangDao;
	@Autowired
	private PalbangDetailDao palbangDetailDao;
	
	public int getCount() {
		return palbangDao.count();
	}

	public List<Palbang> getList(Pager pager) {
		
		return palbangDao.selectByLike(pager);
	}

	public int delete(int palbang_id) {
		return palbangDao.deleteByPid(palbang_id);
	}

	public Palbang getPalbang(int palbang_id) {
		return palbangDao.selectByPid(palbang_id);
	}

	public List<Palbang_detail> getPalbangDetail(int palbang_id) {
		List<Palbang_detail> palbangdetail = palbangDetailDao.selectAll(palbang_id);
		return palbangdetail;
	}

	
}
