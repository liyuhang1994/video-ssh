package com.zhiyou100.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.AdminDao;
import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.service.AdminService;
import com.zhiyou100.video.service.base.impl.BaseServiceImpl;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

	@Autowired
	private AdminDao ad;
	
	public AdminDao getAd(){
		return ad;
	}
	
	@Autowired
	public void setAd(AdminDao ad){
		this.ad = ad;
		super.setBd(ad);
	}

	@Override
	public Admin findAdminByAdmin(Admin a) {
		return ad.findAdminByAdmin(a);
	}
	
	
	
	
}
