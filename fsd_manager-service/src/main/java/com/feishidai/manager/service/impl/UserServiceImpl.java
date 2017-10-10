package com.feishidai.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feishidai.common.utils.MD5Utils;
import com.feishidai.manager.dao.UserDao;
import com.feishidai.manager.dao.bean.User;
import com.feishidai.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User login(JSONObject params) {
		User user = JSON.parseObject(params.toJSONString(), User.class);
		user.setPassword(MD5Utils.MD5(user.getPassword()));
		return userDao.select(user);
	}

	@Override
	public JSONObject modifyPassword(User user, JSONObject params) {
		String olePassword = MD5Utils.MD5(params.getString("olePassword"));
		String error = null;
		if(user == null){
			error = "用户登录超时，请重新登录后修改密码";
		}else if(StringUtils.equals(user.getPassword(), olePassword)){
			String newPassword = params.getString("newPassword");
			user.setPassword(MD5Utils.MD5(newPassword));
			userDao.modify(user);
		}else{
			error = "原始密码不正确！";
		}
		JSONObject result = new JSONObject();
		result.put("error", error);
		return result;
	}

}
