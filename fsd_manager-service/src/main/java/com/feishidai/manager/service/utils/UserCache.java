package com.feishidai.manager.service.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

public class UserCache {
	public static Map<String, JSONObject> userMap = new ConcurrentHashMap<>();

}
