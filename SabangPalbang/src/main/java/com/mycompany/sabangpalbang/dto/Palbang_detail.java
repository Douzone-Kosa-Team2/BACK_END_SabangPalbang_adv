package com.mycompany.sabangpalbang.dto;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Palbang_detail {
	private int palbang_detailno;
	private int palbang_id;
	private MultipartFile pdattach;
	private String palbang_dimgoname;
	private String palbang_dimgsname;
	private String palbang_dimgtype;
	private String palbang_explain;
	
	
	public int getPalbang_detailno() {
		return palbang_detailno;
	}
	public void setPalbang_detailno(int palbang_detailno) {
		this.palbang_detailno = palbang_detailno;
	}
	public int getPalbang_id() {
		return palbang_id;
	}
	public void setPalbang_id(int palbang_id) {
		this.palbang_id = palbang_id;
	}
	public String getPalbang_dimgoname() {
		return palbang_dimgoname;
	}
	public void setPalbang_dimgoname(String palbang_dimgoname) {
		this.palbang_dimgoname = palbang_dimgoname;
	}
	public String getPalbang_dimgsname() {
		return palbang_dimgsname;
	}
	public void setPalbang_dimgsname(String palbang_dimgsname) {
		this.palbang_dimgsname = palbang_dimgsname;
	}
	public String getPalbang_dimgtype() {
		return palbang_dimgtype;
	}
	public void setPalbang_dimgtype(String palbang_dimgtype) {
		this.palbang_dimgtype = palbang_dimgtype;
	}
	public String getPalbang_explain() {
		return palbang_explain;
	}
	public void setPalbang_explain(String palbang_explain) {
		this.palbang_explain = palbang_explain;
	}
	public MultipartFile getPdattach() {
		return pdattach;
	}
	public void setPdattach(MultipartFile pdattach) {
		this.pdattach = pdattach;
	}
	@Override
	public String toString() {
		return "Palbang_detail [palbang_detailno=" + palbang_detailno + ", palbang_id=" + palbang_id + ", pdattach="
				+ pdattach + ", palbang_dimgoname=" + palbang_dimgoname + ", palbang_dimgsname=" + palbang_dimgsname
				+ ", palbang_dimgtype=" + palbang_dimgtype + ", palbang_explain=" + palbang_explain + "]";
	}
	


}