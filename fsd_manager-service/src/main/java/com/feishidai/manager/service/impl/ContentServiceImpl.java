package com.feishidai.manager.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.feishidai.common.utils.FileUtils;
import com.feishidai.common.utils.PropertiesUtils;
import com.feishidai.manager.dao.ContentDao;
import com.feishidai.manager.dao.bean.Content;
import com.feishidai.manager.dao.enums.ChannelEnum;
import com.feishidai.manager.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao contentDao;
	
	@Override
	public JSONArray select(JSONObject formParams) {
		Content content = JSON.parseObject(formParams.toJSONString(), Content.class);
		List<Content> list = contentDao.select(content);
		return JSON.parseArray(JSON.toJSONString(list));
	}
	
	@Override
	public JSONObject selectOne(Integer id) {
		Content content = contentDao.selectOne(id);
		return JSON.parseObject(JSON.toJSONString(content));
	}
	
	@Override
	public Integer count(JSONObject formParams) {
		Content content = JSON.parseObject(formParams.toJSONString(), Content.class);
		return contentDao.count(content);
	}

	@Override
	public JSONObject save(JSONObject formParams) {
		String error = null;
		Content content = JSON.parseObject(formParams.toJSONString(), Content.class);
		contentDao.save(content);
		
		JSONObject result = new JSONObject();
		result.put("error", error);
		return result;
	}

	@Override
	public JSONObject modify(JSONObject formParams) {
		String error = null;
		Content content = JSON.parseObject(formParams.toJSONString(), Content.class);
		contentDao.modify(content);
		JSONObject result = new JSONObject();
		result.put("error", error);
		return result;
	}
	
	@Override
	public JSONObject delete(JSONObject formParams) {
		String error = null;
		contentDao.delete(formParams.getInteger("id"));
		JSONObject result = new JSONObject();
		result.put("error", error);
		return result;
	}

	@Override
	public JSONObject view(JSONObject formParams) {
		String error = null;
		JSONObject result = new JSONObject();
		result.put("error", error);
		return result;
	}

	private void flushPublishFile() throws Exception{
		Content content = new Content();
		content.setStatus(1);
		content.setPage(1);
		content.setRows(50);
		JSONObject cfJson = ChannelEnum.getChannelEnum();
		Iterator<String> it = cfJson.keySet().iterator();
		
		String homecontentpath = PropertiesUtils.get("manager", "homepagepath");
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter("title", "homeFile", "publishTime", "orderBy"); 
		JSONObject json = new JSONObject();
		while(it.hasNext()){
			String code = it.next();
			content.setChannel(Integer.parseInt(code));
			List<Content> list = contentDao.select(content);// 获取所有已发布信息
			
			json.put("channel_" + code, JSON.parseArray(JSON.toJSONString(list, filter)));
		}
		File file = new File(homecontentpath + "/upload/data/list.json");
		FileUtils.writeStringToFile(file, json.toString());
	}
	
	@Override
	public void publish(Integer id) throws Exception {
		Content content = contentDao.selectOne(id);
		content.setStatus(1);
		content.setPublishTime(new Date());
		if(StringUtils.isBlank(content.getHomeFile())){
			content.setHomeFile(new Date().getTime() + ".json");
		}
		contentDao.modify(content);
		
		flushPublishFile();
		
		String homecontentpath = PropertiesUtils.get("manager", "homepagepath");
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter("title", "content", "publishTime");  
		File file = new File(homecontentpath + "/upload/data/" + content.getHomeFile());
		FileUtils.writeStringToFile(file, JSON.toJSONString(content, filter));
	}
	
	@Override
	public void offline(Integer id) throws Exception {
		Content content = contentDao.selectOne(id);
		content.setStatus(0);
		contentDao.modify(content);
		
		flushPublishFile();
	}
}
