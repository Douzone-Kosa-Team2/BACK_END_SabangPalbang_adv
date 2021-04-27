package com.mycompany.sabangpalbang.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Palbang;
import com.mycompany.sabangpalbang.dto.Palbang_detail;
import com.mycompany.sabangpalbang.service.PalbangService;

@RestController
@RequestMapping("/palbang_m")
public class PalbangController {
	private static final Logger logger = LoggerFactory.getLogger(PalbangController.class);

	private String IMG_URL = "/Users/homecj/Pictures/SabangPalbang_upload/images/";
	//이종현
	private String IMG_URL_hyun = "C:/Users/ant94/git/SabangPalbang_upload/images/";
	//조민상
	private String IMG_URL_sang = "C:/sabangpalbang_advanced/images/";
	//이채정
	private String IMG_URL_jung = "/Users/homecj/Pictures/SabangPalbang_upload/images/";
	
	@Autowired
	private PalbangService palbangService;

	@GetMapping("") // 게시물 목록을 보여달라고 할 떼
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		logger.info("palbang_list");
		int totalRows = palbangService.getCount();
		Pager pager = new Pager(6, 5, totalRows, pageNo);
		List<Palbang> palbangLikeList = palbangService.getPalbangList_Like(pager);
		List<Palbang> palbangViewList = palbangService.getPalbangList_View(pager);
		List<Palbang> palbangNewList = palbangService.getPalbangList_New(pager);
		List<Palbang> palbangOldList = palbangService.getPalbangList_Old(pager);
		
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("palbangLikeList", palbangLikeList);
		map.put("palbangViewList", palbangViewList);
		map.put("palbangNewList", palbangNewList);
		map.put("palbangOldList", palbangOldList);
		return map;
	}

	// 해당 게시물 출력
	@GetMapping("/{palbang_id}")
	public Map<String, Object> read(@PathVariable int palbang_id) {
		Palbang palbang = palbangService.getPalbang(palbang_id);
		List<Palbang_detail> palbanglist = palbangService.getPalbangDetail(palbang_id);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("palbang", palbang);
		map.put("palbanglist", palbanglist);
		return map;
	}
	
	@GetMapping("/pattach/{palbang_id}")
	public void downloadPalbang(@PathVariable int palbang_id, HttpServletResponse response) {
		try {
			Palbang palbang = palbangService.getPalbang(palbang_id);
			String pattachoname = palbang.getPalbang_imgoname();
			if (pattachoname == null)
				return;
			pattachoname = new String(pattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String pattachsname = palbang.getPalbang_imgsname();
			String pattachspath = IMG_URL + "palbang_post/" + pattachsname;
			String pattachtype = palbang.getPalbang_imgtype();

			response.setHeader("Content-Disposition", "attachment; filename=\"" + pattachoname + "\";");
			response.setContentType(pattachtype);

			InputStream is = new FileInputStream(pattachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/pDattach/{palbang_detailno}")
	public void downloadPalbangDetail(@PathVariable int palbang_detailno, HttpServletResponse response) {
		try {
			Palbang_detail palbang_detail = palbangService.getPalbangDetailByNo(palbang_detailno);
			String pattachoname = palbang_detail.getPalbang_dimgoname();
			if (pattachoname == null)
				return;
			pattachoname = new String(pattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String pattachsname = palbang_detail.getPalbang_dimgsname();
			String pattachspath = IMG_URL + "palbang_detail/" + pattachsname;
			String pattachtype = palbang_detail.getPalbang_dimgtype();

			response.setHeader("Content-Disposition", "attachment; filename=\"" + pattachoname + "\";");
			response.setContentType(pattachtype);

			InputStream is = new FileInputStream(pattachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시물 삭제
	@DeleteMapping("/{palbang_id}")
	public void delete(@PathVariable int palbang_id) {
		palbangService.delete(palbang_id);
	}
}
