package com.feishidai.manager.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feishidai.common.utils.ControllerUtils;
import com.feishidai.common.utils.FileUtils;
import com.feishidai.manager.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/select")
	public void select(HttpServletRequest req, HttpServletResponse res){
		JSONObject result = new JSONObject();
		int status = 200;
		String msg = null;
		try{
			JSONObject params = ControllerUtils.getFormParams(req);
			JSONArray list = contentService.select(params);
			Integer total = contentService.count(params);
			result.put("rows", list);
			result.put("total", total);
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/selectOne")
	public void selectOne(HttpServletRequest req, HttpServletResponse res, Integer id){
		JSONObject result = new JSONObject();
		int status = 200;
		String msg = null;
		try{
			if(id != null){
				JSONObject content = contentService.selectOne(id);
				result.put("content", content);
			}else{
				status = 500;
				msg = "id为空！";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/save")
	public void save(HttpServletRequest req, HttpServletResponse res){
		int status = 200;
		String msg = null;
		try{
			JSONObject detail = contentService.save(ControllerUtils.getFormParams(req));
			if(StringUtils.isNotBlank(detail.getString("error"))){
				status = 500;
				msg = detail.getString("error");
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/modify")
	public void modify(HttpServletRequest req, HttpServletResponse res){
		int status = 200;
		String msg = null;
		try{
			JSONObject detail = contentService.modify(ControllerUtils.getFormParams(req));
			if(StringUtils.isNotBlank(detail.getString("error"))){
				status = 500;
				msg = detail.getString("error");
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletRequest req, HttpServletResponse res){
		int status = 200;
		String msg = null;
		try{
			JSONObject detail = contentService.delete(ControllerUtils.getFormParams(req));
			if(StringUtils.isNotBlank(detail.getString("error"))){
				status = 500;
				msg = detail.getString("error");
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/order")
	public void order(HttpServletRequest req, HttpServletResponse res){
		int status = 200;
		String msg = null;
		try{
			JSONObject detail = contentService.modify(ControllerUtils.getFormParams(req));
			if(StringUtils.isNotBlank(detail.getString("error"))){
				status = 500;
				msg = detail.getString("error");
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/preview")
	public void preview(HttpServletRequest req, HttpServletResponse res,Integer id){
		int status = 200;
		String msg = null;
		JSONObject result = new JSONObject();
		try{
			if(id != null){
				JSONObject content = contentService.selectOne(id);
				content.put("publishTime", new Date().getTime());
				String path = req.getSession().getServletContext().getRealPath("")+"/previewhome/upload/data/data.json";
				File file = new File(path);
				FileUtils.writeStringToFile(file, content.toJSONString());
				result.put("homeFile", "data.json");
			}else{
				status = 500;
				msg = "id为空！";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/publish")
	public void publish(HttpServletRequest req, HttpServletResponse res, Integer id){
		int status = 200;
		String msg = null;
		try{
			if(id != null){
				contentService.publish(id);
			}else{
				status = 500;
				msg = "id为空！";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
	
	@RequestMapping("/offline")
	public void offline(HttpServletRequest req, HttpServletResponse res, Integer id){
		int status = 200;
		String msg = null;
		try{
			if(id != null){
				contentService.offline(id);
			}else{
				status = 500;
				msg = "id为空！";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = 500;
			msg = e.toString();
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		ControllerUtils.printWriter(req, res, result.toJSONString());
	}
}
