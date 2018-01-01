package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.base.BaseService;

public interface FrontUserService extends BaseService<User>{

	List<User> findUserByUser(User model);

	void addUser(User model);

	User finUserByEmail(String email);

}
