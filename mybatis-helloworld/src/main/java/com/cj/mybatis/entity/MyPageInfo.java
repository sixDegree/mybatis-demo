package com.cj.mybatis.entity;

public class MyPageInfo {
	private Integer pageNum;
	private Integer pageSize;
	private Integer total;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "MyPageInfo [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + "]";
	}
}
