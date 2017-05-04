package com.china.ciic.bookgenerate.model.json;

import java.util.List;

public class PageResult<T> extends BaseResponse {
	private List<T> list;
	private int pageNo;
	private Long total;

	public PageResult() {

	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
