package com.feishidai.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.feishidai.common.utils.ControllerUtils;
import com.feishidai.manager.dao.bean.User;
import com.feishidai.manager.dao.enums.ChannelEnum;
import com.feishidai.manager.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(HttpServletRequest req, HttpServletResponse res){
		JSONObject params = ControllerUtils.getFormParams(req);
		User user = userService.login(params);
		int status = 500;
		String msg = "账号密码错误";
		if(user != null){
			status = 200;
			msg = null;
			req.getSession().setAttribute("user", user);
		}
		JSONObject detail = new JSONObject();
		detail.put("status", status);
		detail.put("msg", msg);
		ControllerUtils.printWriter(req, res, detail.toString());
	}

	@RequestMapping("/init")
	public void init(HttpServletRequest req, HttpServletResponse res){
		User user = (User) req.getSession().getAttribute("user");
		int status = 200;
		String msg = null;
		if(user == null){
			status = 500;
			msg = "登录超时！请重新登录";
		}
		JSONObject detail = new JSONObject();
		detail.put("status", status);
		detail.put("msg", msg);
		detail.put("channelEnum", ChannelEnum.getChannelEnum());
		ControllerUtils.printWriter(req, res, detail.toString());
	}

	@RequestMapping("/loginout")
	public void loginout(HttpServletRequest req, HttpServletResponse res){
		req.getSession().removeAttribute("user");
		int status = 200;
		String msg = "登出成功";
		JSONObject detail = new JSONObject();
		detail.put("status", status);
		detail.put("msg", msg);
		ControllerUtils.printWriter(req, res, detail.toString());
	}

	@RequestMapping("/modifyPassword")
	public void modifyPassword(HttpServletRequest req, HttpServletResponse res){
		JSONObject params = ControllerUtils.getFormParams(req);
		User user = (User) req.getSession().getAttribute("user");

		JSONObject detail = userService.modifyPassword(user, params);
		int status = 200;
		String msg = "密码修改成功！";
		if(StringUtils.isNotBlank(detail.getString("error"))){
			status = 500;
			msg = detail.getString("error");
		}else{
			req.getSession().setAttribute("user", user);
		}
		detail.put("status", status);
		detail.put("msg", msg);
		ControllerUtils.printWriter(req, res, detail.toString());
	}
	
}
