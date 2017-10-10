package com.feishidai.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.feishidai.manager.dao.bean.User;

public interface UserService {

	User login(JSONObject params);

	JSONObject modifyPassword(User user, JSONObject params);

}
