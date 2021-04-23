package com.mycompany.sabangpalbang.dto;

import java.util.Date;

public class Member {
	private int member_id;
	private String member_email;
	private String member_email2;
	private String member_pw;
	private String member_name;
	
	private String member_phone;
	private String tel_pre;
	private String tel_suf;

	private String member_nickname;
	private int member_enable;
	private String member_authority;
	private Date join_date;
	private String zipcode;
	private String roadaddress;
	private String detailaddress;
	
	
	
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public int getMember_enable() {
		return member_enable;
	}
	public void setMember_enable(int member_enable) {
		this.member_enable = member_enable;
	}
	public String getMember_authority() {
		return member_authority;
	}
	public void setMember_authority(String member_authority) {
		this.member_authority = member_authority;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getRoadaddress() {
		return roadaddress;
	}
	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	
	
	public String getMember_email2() {
		return member_email2;
	}
	public void setMember_email2(String member_email2) {
		this.member_email2 = member_email2;
	}
	public String getTel_pre() {
		return tel_pre;
	}
	public void setTel_pre(String tel_pre) {
		this.tel_pre = tel_pre;
	}
	public String getTel_suf() {
		return tel_suf;
	}
	public void setTel_suf(String tel_suf) {
		this.tel_suf = tel_suf;
	}
	
}
