package com.mycompany.sabangpalbang.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Palbang {
	private int palbang_id;
	private String palbang_title;
	private String palbang_nickname;
	private int palbang_likecount;
	private int palbang_viewcount;
	private Date palbang_date;
	private MultipartFile pattach;
	private String palbang_imgoname;
	private String palbang_imgsname;
	private String palbang_imgtype;
	// 팔방 디테일 
	private List<Palbang_detail> reviews;
	
	public int getPalbang_id() {
		return palbang_id;
	}
	public void setPalbang_id(int palbang_id) {
		this.palbang_id = palbang_id;
	}
	public String getPalbang_title() {
		return palbang_title;
	}
	public void setPalbang_title(String palbang_title) {
		this.palbang_title = palbang_title;
	}
	public String getPalbang_nickname() {
		return palbang_nickname;
	}
	public void setPalbang_nickname(String palbang_nickname) {
		this.palbang_nickname = palbang_nickname;
	}
	public int getPalbang_likecount() {
		return palbang_likecount;
	}
	public void setPalbang_likecount(int palbang_likecount) {
		this.palbang_likecount = palbang_likecount;
	}
	public int getPalbang_viewcount() {
		return palbang_viewcount;
	}
	public void setPalbang_viewcount(int palbang_viewcount) {
		this.palbang_viewcount = palbang_viewcount;
	}
	public Date getPalbang_date() {
		return palbang_date;
	}
	public void setPalbang_date(Date palbang_date) {
		this.palbang_date = palbang_date;
	}
	public String getPalbang_imgoname() {
		return palbang_imgoname;
	}
	public void setPalbang_imgoname(String palbang_imgoname) {
		this.palbang_imgoname = palbang_imgoname;
	}
	public String getPalbang_imgsname() {
		return palbang_imgsname;
	}
	public void setPalbang_imgsname(String palbang_imgsname) {
		this.palbang_imgsname = palbang_imgsname;
	}
	public String getPalbang_imgtype() {
		return palbang_imgtype;
	}
	public void setPalbang_imgtype(String palbang_imgtype) {
		this.palbang_imgtype = palbang_imgtype;
	}
	public MultipartFile getPattach() {
		return pattach;
	}
	public void setPattach(MultipartFile pattach) {
		this.pattach = pattach;
	}
	public List<Palbang_detail> getReviews() {
		return reviews;
	}
	public void setReviews(List<Palbang_detail> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Palbang [palbang_id=" + palbang_id + ", palbang_title=" + palbang_title + ", palbang_nickname="
				+ palbang_nickname + ", palbang_likecount=" + palbang_likecount + ", palbang_viewcount="
				+ palbang_viewcount + ", palbang_date=" + palbang_date + ", pattach=" + pattach + ", palbang_imgoname="
				+ palbang_imgoname + ", palbang_imgsname=" + palbang_imgsname + ", palbang_imgtype=" + palbang_imgtype
				+ ", reviews=" + reviews + "]";
	}

	
	
}
