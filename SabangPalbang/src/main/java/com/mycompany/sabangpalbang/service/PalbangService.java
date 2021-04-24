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

	public Palbang_detail getPalbangDetailByNo(int palbang_detailno) {
		return palbangDetailDao.selectPalbangDetail(palbang_detailno);
	}

	public List<Palbang> getPalbangList_Like(Pager pager) { // default
		List<Palbang> list = palbangDao.selectByLike(pager);
		return list;
	}
	
	public List<Palbang> getPalbangList_View(Pager pager) { 
		List<Palbang> list = palbangDao.selectByView(pager);
		return list;
	}
	
	public List<Palbang> getPalbangList_New(Pager pager) { 
		List<Palbang> list = palbangDao.selectByNew(pager);
		return list;
	}
	
	public List<Palbang> getPalbangList_Old(Pager pager) { 
		List<Palbang> list = palbangDao.selectByOld(pager);
		return list;
	}

	
}
