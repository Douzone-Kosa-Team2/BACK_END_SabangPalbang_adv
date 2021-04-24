package com.mycompany.sabangpalbang.dto;

import java.util.Date;
import java.util.List;

public class Ordermonthbuy {
	private int buycount;
	private int buysum;
	private Date buymonth;
	
	public int getBuycount() {
		return buycount;
	}
	public void setBuycount(int buycount) {
		this.buycount = buycount;
	}
	public int getBuysum() {
		return buysum;
	}
	public void setBuysum(int buysum) {
		this.buysum = buysum;
	}
	public Date getBuymonth() {
		return buymonth;
	}
	public void setBuymonth(Date buymonth) {
		this.buymonth = buymonth;
	}
	
	
	
	
}
