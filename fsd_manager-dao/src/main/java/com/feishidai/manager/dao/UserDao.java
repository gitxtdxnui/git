package com.feishidai.manager.dao;

import com.feishidai.manager.dao.bean.User;

public interface UserDao {

	User select(User user);

	void modify(User user);

	void delete(Integer integer);
}
