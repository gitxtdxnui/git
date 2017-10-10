package com.feishidai.manager.dao.bean;

public class BaseBean {
	private Integer start;
	private Integer page;
	private Integer rows;

	public Integer getStart() {
		if(page != null && rows != null){
			start = rows * (page - 1);
		}
		return start;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
