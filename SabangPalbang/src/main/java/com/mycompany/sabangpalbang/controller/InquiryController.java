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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.sabangpalbang.dto.Inquiry;
import com.mycompany.sabangpalbang.dto.Pager;
import com.mycompany.sabangpalbang.dto.Sabang;
import com.mycompany.sabangpalbang.service.InquiryService;
import com.mycompany.sabangpalbang.service.SabangService;

@RestController
@RequestMapping("/inquiry_m")
public class InquiryController {
	private static final Logger logger = LoggerFactory.getLogger(PalbangController.class);
	
	
	private String IMG_URL = "C:/Users/ant94/git/SabangPalbang_upload/images/";
	//이종현
	private String IMG_URL_hyun = "C:/Users/ant94/git/SabangPalbang_upload/images/";
	//조민상
	private String IMG_URL_sang = "C:/sabangpalbang_advanced/images/";
	//이채정
	private String IMG_URL_jung = "/Users/homecj/Pictures/SabangPalbang_upload/images/";
	
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private SabangService sabangService;
	
	// 사방 목록
	@GetMapping("") 
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
		int totalRows = sabangService.getCount();
		Pager pager = new Pager(6, 5, totalRows, pageNo);
		
		List<Sabang> sabangBuyList = sabangService.getSabangList_Buy(pager);
		for(int i=0; i<sabangBuyList.size(); i++) {
			int sid = sabangBuyList.get(i).getSabang_id();
			int totalInquiryNum = inquiryService.getCount(sid, null); // 전체 개수 구해야함 
			int noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangBuyList.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangBuyList.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		List<Sabang> sabangViewList = sabangService.getSabangList_View(pager);
		for(int i=0; i<sabangViewList.size(); i++) {
			int sid = sabangViewList.get(i).getSabang_id();
			int totalInquiryNum = inquiryService.getCount(sid, null); // 전체 개수 구해야함 
			int noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangViewList.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangViewList.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		List<Sabang> sabangHighList = sabangService.getSabangList_High(pager);
		for(int i=0; i<sabangHighList.size(); i++) {
			int sid = sabangHighList.get(i).getSabang_id();
			int totalInquiryNum = inquiryService.getCount(sid, null); // 전체 개수 구해야함 
			int noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangHighList.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangHighList.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		List<Sabang> sabangLowList = sabangService.getSabangList_Low(pager);
		for(int i=0; i<sabangLowList.size(); i++) {
			int sid = sabangLowList.get(i).getSabang_id();
			int totalInquiryNum = inquiryService.getCount(sid, null); // 전체 개수 구해야함 
			int noAnsInquiryNum = inquiryService.getNoAnsCount(sid);
			sabangLowList.get(i).setTotalInquiryNum(totalInquiryNum);
			sabangLowList.get(i).setNoAnsInquiryNum(noAnsInquiryNum);
		}
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("sabangBuyList", sabangBuyList);
		map.put("sabangViewList", sabangViewList);
		map.put("sabangHighList", sabangHighList);
		map.put("sabangLowList", sabangLowList);
		return map;
	}
	
	// 사방에 대한 문의 목록
	@GetMapping("/{sid}") 
	public Map<String, Object> inquirylist(@PathVariable int sid,
										   @RequestParam(defaultValue = "1") int pageNo,
										   String ansstate) {
		/* 점체답변이면 널이고, 아니면 그 값을 넣어줘야함 */
		if(ansstate.equals("전체답변")) {
			ansstate = null;
		}
		// 전체 답변,답변중, 답변완료 에 따라서 카운트 개수 달라짐 !!! 
		int totalRows = inquiryService.getCount(sid, ansstate); // 전체 개수 
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		List<Inquiry> inquirylist = inquiryService.getList(pager, sid, ansstate);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("inquirylist", inquirylist);
		return map;
	}
	
	// 문의 내용 읽기 
	@GetMapping("/inquiry/{inquiry_id}") 
	public Inquiry inquiry(@PathVariable int inquiry_id) {
	    Inquiry inquiry = inquiryService.getInquiry(inquiry_id);
		return inquiry;
	}
	
	// 문의 답변 남기기 
	@PutMapping("")
	public int answer(@RequestBody Inquiry inquiry) {
		inquiryService.updateAns(inquiry.getInquiry_anscontent(), inquiry.getInquiry_id());
		return inquiry.getInquiry_id();	
	}
	
	// 문의 삭제 
	@DeleteMapping("/{inquiry_id}")
	public void delete(@PathVariable int inquiry_id) {
		inquiryService.deleteInquiry(inquiry_id);
	}
	
	// 사방 이미지 출력
	@GetMapping("/sattach/{sabang_id}")
	public void download(@PathVariable int sabang_id, HttpServletResponse response) {
		try {
			Sabang sabang = sabangService.getSabang(sabang_id);
			String sattachoname = sabang.getSabang_imgoname();
			if (sattachoname == null)
				return;
			sattachoname = new String(sattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String sattachsname = sabang.getSabang_imgsname();
			String sattachspath = IMG_URL + "sabang_post/" + sattachsname;
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
	
}
