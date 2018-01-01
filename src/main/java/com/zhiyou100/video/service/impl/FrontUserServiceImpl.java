package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.FrontUserDao;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.FrontUserService;
import com.zhiyou100.video.service.base.impl.BaseServiceImpl;

@Service
public class FrontUserServiceImpl extends BaseServiceImpl<User> implements FrontUserService{

	@Autowired
	private FrontUserDao fud;
	
	@Override
	public List<User> findUserByUser(User model) {
		
		return fud.findUserByUser(model);
	}

	@Override
	public void addUser(User model) {
		fud.addUser(model);
		
	}

	@Override
	public User finUserByEmail(String email) {
		return fud.finUserByEmail(email);
	}

}
