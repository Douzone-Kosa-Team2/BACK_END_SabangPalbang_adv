package com.mycompany.sabangpalbang.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sabangpalbang.dto.Member;
import com.mycompany.sabangpalbang.dto.OrderMain;
import com.mycompany.sabangpalbang.dto.Product;
import com.mycompany.sabangpalbang.dto.Sabang;
import com.mycompany.sabangpalbang.service.ProfitService;
@RestController
@RequestMapping("/profit_m")
public class ProfitController {
	@Autowired
	private ProfitService profitService;
	private static final Logger logger = LoggerFactory.getLogger(ProfitController.class);
	
	//채정 - 회원 실적
	@GetMapping("/member")
	public Map<String, Object> showBestMember() {
		logger.info("회원 실적 api");
		int totalMemberNum = profitService.getMemberCount(); // 총 회원수 
		int recentJoinNum = profitService.getRecentJoinCount(); // 최근 한 달간 가입한 회원수 
		int buyMemberNum = profitService.getBuyMemberCount(); // 실구매 회원수 
		List<Member> vipMembers = profitService.getVipMembers(); // 우수 고객 top5 (주문 횟수 기준)
		List<Member> influencers = profitService.getInfluencers(); // 인플루언서 고객 top5 (팔방 작성 횟수 기준)
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalMemberNum", totalMemberNum);
		map.put("recentJoinNum", recentJoinNum);
		map.put("buyMemberNum", buyMemberNum);
		map.put("vipMembers", vipMembers);
		map.put("influencers", influencers);
		return map;
	}
	
	
	//종현 - 사방 실적
	@GetMapping("/sabang")
	public Map<String, Object> showBestSabang() {
		//logger.info("사방 실적");
		
		Sabang sabang = profitService.getBestSabang();
		Product product = profitService.getBestProduct();
				
		//logger.info(""+sabang);
		//logger.info(""+product);
		Map<String, Object> map = new HashMap<>();
		
		map.put("BestSabang", sabang);
		map.put("BestProduct", product);
		return map;
	}
	
	// 사방 이미지 출력
	@GetMapping("/sabang/sattach/{sabang_id}")
	public void sabangImg(@PathVariable int sabang_id, HttpServletResponse response) {
		try {
			Sabang sabang = profitService.getSabang(sabang_id);
			String sattachoname = sabang.getSabang_imgoname();
			if (sattachoname == null)
				return;
			sattachoname = new String(sattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String sattachsname = sabang.getSabang_imgsname();
			String sattachspath = "C:/Users/ant94/git/SabangPalbang_upload/images/sabang_post/" + sattachsname;
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
	
	// 상품 이미지 출력
	@GetMapping("/sabang/pattach/{product_id}")
	public void downloadProduct(@PathVariable int product_id, HttpServletResponse response) {
		try {
			Product product = profitService.getProduct(product_id);
			String pattachoname = product.getProduct_imgoname();
			if (pattachoname == null)
				return;
			pattachoname = new String(pattachoname.getBytes("UTF-8"), "ISO-8859-1");
			String pattachsname = product.getProduct_imgsname();
			String pattachspath = "C:/Users/ant94/git/SabangPalbang_upload/images/sabang_detail/" + pattachsname;
			String pattachtype = product.getProduct_imgtype();

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
	
	
	//민상 - 주문 실적
	@GetMapping("/order")
	public Map<String, Object> orderperformance() {
		logger.info("주문실적");
		int month = (Calendar.MONTH) + 1;

		//모든 날의 order정보와 최근 3달 order정보
		List<OrderMain> totalmonth = profitService.getMonthJanuary();//현재 월 -3 까지의 월들의 주문 정보들
		
		List<OrderMain> month3 = new ArrayList<>();
		List<OrderMain> month2 = new ArrayList<>();
		List<OrderMain> month1 = new ArrayList<>();	//-3 ~ -1 월의 주문 정보들을 각각 저장하려 할 때
		
		List<Integer> totalprice3 = new ArrayList<>();
		List<Integer> totalprice2 = new ArrayList<>();
		List<Integer> totalprice1 = new ArrayList<>(); //각 최근 3개의 월의 총 주문금액들을 담을 List 
		
		
		List<Integer> outputThreeMonth = new ArrayList<>();
		List<Integer> outputTwoMonth = new ArrayList<>();
		List<Integer> outputOneMonth = new ArrayList<>(); //각 최근 3개의 월을 담을 List 
		
		
		for(int i=0; i<totalmonth.size(); i++) {
			if((month+1) == (totalmonth.get(i).getOrder_date().getMonth()+1)) {
				month3.add(totalmonth.get(i));
				
			}
			else if((month) == (totalmonth.get(i).getOrder_date().getMonth()+1)) {
				month2.add(totalmonth.get(i));
				
				
			}
			else if((month-1) == (totalmonth.get(i).getOrder_date().getMonth()+1)) {
				month1.add(totalmonth.get(i));
				
			}
		}
		
		int total = 0;
		int threemonthtotal = 0;
		for(int i=0; i<month3.size(); i++) {
			total += month3.get(i).getOrder_price();
			threemonthtotal += month3.get(i).getOrder_price();
			
		}
		
		totalprice3.add(total);
		total=0;
		
		for(int i=0; i<month2.size(); i++) {
			total += month2.get(i).getOrder_price();
			threemonthtotal += month2.get(i).getOrder_price();
		}
		totalprice2.add(total);
		total=0;
		
		for(int i=0; i<month1.size(); i++) {
			total += month1.get(i).getOrder_price();
			threemonthtotal += month1.get(i).getOrder_price();
		}
		totalprice1.add(total);
		total = 0;
		
		//총 주문건수
		List<Integer> totalCount = new ArrayList<>();
		int totalccount = profitService.getTotalCount();
		totalCount.add(totalccount);
		
		//최근 3개월 판매금액
		List<Integer> threeTotalCount = new ArrayList<>();
		threeTotalCount.add(threemonthtotal);
		
		
		//총 판매금액
		List<Integer> sumtotalprice = new ArrayList<>();
		for(int i=0; i<totalmonth.size(); i++) {
			total += totalmonth.get(i).getOrder_price();
			
		}
		sumtotalprice.add(total);
		
		
		//결제 방법

		int cardcount = profitService.getCardPaycount();
		int depositcount = profitService.getDepositPayCount();
		int phonecount = profitService.getPhonePayCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("month3", month3.size());
		map.put("month2", month2.size());
		map.put("month1", month1.size());
		map.put("sumtotalprice", sumtotalprice);
		map.put("totalprice3", totalprice3);
		map.put("totalprice2", totalprice2);
		map.put("totalprice1", totalprice1);
		map.put("totalCount", totalCount);
		map.put("threeTotalCount", threeTotalCount);
		map.put("cardpaycount", cardcount);
		map.put("depositpaycount", depositcount);
		map.put("phonepaycount", phonecount);
		
		map.put("outputThreeMonth", month3.get(0).getOrder_date().getMonth()+1+"");
		map.put("outputTwoMonth", month2.get(0).getOrder_date().getMonth()+1+"");
		map.put("outputOneMonth", month1.get(0).getOrder_date().getMonth()+1+"");
		return map;
		
	}
}
