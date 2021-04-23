package com.mycompany.sabangpalbang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
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
		Pager pager = new Pager(6, 5, totalRows, pageNo);
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
	@PostMapping("")
	public Sabang create(Sabang sabang) {
//		logger.info(sabang.getSabang_name());
//		logger.info(""+sabang.getSabang_price());
//		logger.info(""+sabang.getSabang_saleprice());
//		logger.info(""+sabang.getSabang_viewcount());
//		logger.info(""+sabang.getSabang_buycount());
//		logger.info(""+sabang.getSattach());
//		logger.info(""+sabang.getSabang_imgoname());
//		logger.info(""+sabang.getSabang_state());

		if (sabang.getSattach() != null && !sabang.getSattach().isEmpty()) {
			MultipartFile mf = sabang.getSattach();
			sabang.setSabang_imgoname(mf.getOriginalFilename());
			sabang.setSabang_imgsname(new Date().getTime() + "-" + mf.getOriginalFilename());
			sabang.setSabang_imgtype(mf.getContentType());
			try {
				File file = new File("C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_post/"
						+ sabang.getSabang_imgsname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sabangService.insert(sabang);
		// json은 문자열이기 때문에 파일 이름이 들어갈수없다. 그래서 없애버려라
		sabang.setSattach(null);
		return sabang;
	}

	// 사방 수정
	@PutMapping("")
	public Sabang update(Sabang sabang) {
		logger.info("" + sabang.getSabang_id());
		logger.info(sabang.getSabang_name());
		logger.info("" + sabang.getSabang_price());
		logger.info("" + sabang.getSabang_saleprice());
		logger.info("" + sabang.getSabang_viewcount());
		logger.info("" + sabang.getSabang_buycount());
		logger.info("" + sabang.getSattach());
		logger.info("" + sabang.getSabang_imgoname());
		logger.info("" + sabang.getSabang_state());

		if (sabang.getSattach() != null && !sabang.getSattach().isEmpty()) {
			MultipartFile mf = sabang.getSattach();
			sabang.setSabang_imgoname(mf.getOriginalFilename());
			sabang.setSabang_imgsname(new Date().getTime() + "-" + mf.getOriginalFilename());
			sabang.setSabang_imgtype(mf.getContentType());
			try {
				File file = new File("C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_post/"
						+ sabang.getSabang_imgsname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sabangService.update(sabang);
		sabang.setSattach(null);
		return sabang;
	}

	// 첨부다운
	@GetMapping("/sattach/{sabang_id}")
	public void download(@PathVariable int sabang_id, HttpServletResponse response) {
		try {
			Sabang sabang = sabangService.getSabang(sabang_id);
			String sattachoname = sabang.getSabang_imgoname();
			if (sattachoname == null)
				return;
			sattachoname = new String(sattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String sattachsname = sabang.getSabang_imgsname();
			String sattachspath = "C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_post/" + sattachsname;
			String sattachtype = sabang.getSabang_imgtype();

			response.setHeader("Content-Disposition", "attachment; filename=\"" + sattachoname + "\";");
			response.setContentType(sattachtype);

			InputStream is = new FileInputStream(sattachspath);
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
	@DeleteMapping("/{sabang_id}")
	public void delete(@PathVariable int sabang_id) {
		sabangService.delete(sabang_id);
	}
	// 상품 등록

	@PostMapping("/detail")
	public Product createProduct(Product product) {
			logger.info(product.getProduct_name());
			logger.info(""+product.getSabang_id());
			logger.info(""+product.getProduct_price());
			logger.info(""+product.getProduct_buycount());
			logger.info(""+product.getProduct_explain1());
			logger.info(""+product.getProduct_explain2());
			logger.info(""+product.getPattach());

		if (product.getPattach() != null && !product.getPattach().isEmpty()) {
			MultipartFile mf = product.getPattach();
			product.setProduct_imgoname(mf.getOriginalFilename());
			product.setProduct_imgsname(new Date().getTime() + "-" + mf.getOriginalFilename());
			product.setProduct_imgtype(mf.getContentType());
			try {
				File file = new File("C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_detail/"
						+ product.getProduct_imgsname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sabangService.insertProduct(product);
		// json은 문자열이기 때문에 파일 이름이 들어갈수없다. 그래서 없애버려라
		product.setPattach(null);
		return product;
	}

	// 상품 수정

	@PutMapping("/detail")
	public Product updateProduct(Product product) {
			logger.info(""+product.getProduct_id());
			logger.info(product.getProduct_name());
			logger.info(""+product.getSabang_id());
			logger.info(""+product.getProduct_price());
			logger.info(""+product.getProduct_buycount());
			logger.info(""+product.getProduct_explain1());
			logger.info(""+product.getProduct_explain2());
			logger.info(""+product.getPattach());

		if (product.getPattach() != null && !product.getPattach().isEmpty()) {
			MultipartFile mf = product.getPattach();
			product.setProduct_imgoname(mf.getOriginalFilename());
			product.setProduct_imgsname(new Date().getTime() + "-" + mf.getOriginalFilename());
			product.setProduct_imgtype(mf.getContentType());
			try {
				File file = new File("C:/Users/ant94/git/FRONT_END_SabangPalbang_adv/resources/images/sabang_detail/"
						+ product.getProduct_imgsname());
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sabangService.updateProduct(product);
		// json은 문자열이기 때문에 파일 이름이 들어갈수없다. 그래서 없애버려라
		product.setPattach(null);
		return product;
	}
	// 상품 삭제
	@DeleteMapping("/detail/{product_id}")
	public void deleteProduct(@PathVariable int product_id) {
		sabangService.deleteProduct(product_id);
	}
}