package com.feishidai.manager.dao;

import java.util.List;

import com.feishidai.manager.dao.bean.Content;

public interface ContentDao {
	List<Content> select(Content content);

	Integer count(Content content);
	
	Content selectOne(Integer id);

	void save(Content content);

	void modify(Content content);

	void delete(Integer integer);

}
