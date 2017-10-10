package com.feishidai.manager.dao.bean;

import java.util.Date;

public class Content extends BaseBean {
	private Integer id;
	private String title;
	private String content;
	private Integer channel;
	private Integer orderBy;
	private Integer status;
	private Date publishTime;
	private String homeFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getHomeFile() {
		return homeFile;
	}

	public void setHomeFile(String homeFile) {
		this.homeFile = homeFile;
	}

}
