package com.feishidai.manager.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ContentService {
	
	JSONArray select(JSONObject formParams);

	Integer count(JSONObject formParams);

	JSONObject save(JSONObject formParams);

	JSONObject modify(JSONObject formParams);
	
	JSONObject delete(JSONObject formParams);

	JSONObject view(JSONObject formParams);

	void publish(Integer id) throws Exception;
	
	void offline(Integer id) throws Exception;

	JSONObject selectOne(Integer id);


}
