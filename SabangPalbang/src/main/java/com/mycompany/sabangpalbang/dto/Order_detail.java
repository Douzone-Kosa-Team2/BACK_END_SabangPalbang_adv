package com.mycompany.sabangpalbang.dto;

public class Order_detail {
	private int order_detailno;
	private int order_id;
	private int order_productid;
	private String product_name; /*Order_detail 과 Product 테이블을 조인해서 가져온 데이터 */
	
	public Order_detail(int order_productid) {
		this.order_productid = order_productid;
	}
	public int getOrder_detailno() {
		return order_detailno;
	}
	public void setOrder_detailno(int order_detailno) {
		this.order_detailno = order_detailno;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getOrder_productid() {
		return order_productid;
	}
	public void setOrder_productid(int order_productid) {
		this.order_productid = order_productid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
