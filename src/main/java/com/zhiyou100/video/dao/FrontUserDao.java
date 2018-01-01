package com.zhiyou100.video.dao;

import java.util.List;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.model.User;

public interface FrontUserDao extends BaseDao<User>{

	List<User> findUserByUser(User model);

	void addUser(User model);

	User finUserByEmail(String email);

}
