package com.mycompany.sabangpalbang.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;
import com.mycompany.sabangpalbang.service.SabangService;

@RestController
@RequestMapping("/sabang_m")
public class SabangController {
	private static final Logger logger = LoggerFactory.getLogger(SabangController.class);

	@Autowired
	private SabangService sabangService;

	@GetMapping("") // 사방 목록을 보여달라고 할 떼
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		logger.info("palbang_list");
		int totalRows = sabangService.getCount();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Sabang> sabangs = sabangService.getList(pager);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("sabang", sabangs);
		return map;
	}

	// 사방 패키지 출력
	@GetMapping("/{sabang_id}")
	public Map<String, Object> read(@PathVariable int sabang_id) {
		Sabang sabang = sabangService.getSabang(sabang_id);
		List<Product> productlist = sabangService.getProducts(sabang_id);

		Map<String, Object> map = new HashMap<>();

		map.put("sabang", sabang);
		map.put("productlist", productlist);
		return map;
	}
	
	// 사방 등록
	
	// 사방 수정
	@PutMapping("")
	public Sabang update(Sabang sabang) {
		if (sabang.getSattach() != null && !sabang.getSattach().isEmpty()) {
			MultipartFile mf = sabang.getSattach();
			sabang.setSabang_imgoname(mf.getOriginalFilename());
			sabang.setSabang_imgsname(new Date().getTime() + "-" + mf.getOriginalFilename());
			sabang.setSabang_imgtype(mf.getContentType());
			try {
				File file = new File("C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_post/" + sabang.getSabang_imgsname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sabangService.update(sabang);
		sabang.setSattach(null);
		return sabang;
	}

	// 게시물 삭제
	@DeleteMapping("/{sabang_id}")
	public void delete(@PathVariable int sabang_id) {
		sabangService.delete(sabang_id);
	}
}
