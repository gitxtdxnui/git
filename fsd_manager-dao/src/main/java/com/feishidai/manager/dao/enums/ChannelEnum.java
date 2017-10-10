package com.feishidai.manager.dao.enums;

import com.alibaba.fastjson.JSONObject;

public enum ChannelEnum {
	news(1, "新闻"), note(2, "公告");

	public static JSONObject getChannelEnum(){
		JSONObject result = new JSONObject();
		for (ChannelEnum e: ChannelEnum.values()) {
			result.put(e.getCode() + "", e.getMsg());
		}
		return result;
	}
	
	private int code;
	private String msg;

	ChannelEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
