package com.mycompany.sabangpalbang.dto;

import java.util.Date;

public class Inquiry {

	private int inquiry_id;
	private int inquiry_sabangid;
	private String inquiry_type;
	private String inquiry_ansstate;
	private String inquiry_title;
	private String inquiry_writer;
	private Date inquiry_date;
	private String inquiry_explain;
	private String inquiry_anscontent;
	
	
	public int getInquiry_id() {
		return inquiry_id;
	}
	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}
	public int getInquiry_sabangid() {
		return inquiry_sabangid;
	}
	public void setInquiry_sabangid(int inquiry_sabangid) {
		this.inquiry_sabangid = inquiry_sabangid;
	}
	public String getInquiry_type() {
		return inquiry_type;
	}
	public void setInquiry_type(String inquiry_type) {
		this.inquiry_type = inquiry_type;
	}
	public String getInquiry_ansstate() {
		return inquiry_ansstate;
	}
	public void setInquiry_ansstate(String inquiry_ansstate) {
		this.inquiry_ansstate = inquiry_ansstate;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}
	public String getInquiry_writer() {
		return inquiry_writer;
	}
	public void setInquiry_writer(String inquiry_writer) {
		this.inquiry_writer = inquiry_writer;
	}
	public Date getInquiry_date() {
		return inquiry_date;
	}
	public void setInquiry_date(Date inquiry_date) {
		this.inquiry_date = inquiry_date;
	}
	public String getInquiry_explain() {
		return inquiry_explain;
	}
	public void setInquiry_explain(String inquiry_explain) {
		this.inquiry_explain = inquiry_explain;
	}
	public String getInquiry_anscontent() {
		return inquiry_anscontent;
	}
	public void setInquiry_anscontent(String inquiry_anscontent) {
		this.inquiry_anscontent = inquiry_anscontent;
	}
	
}