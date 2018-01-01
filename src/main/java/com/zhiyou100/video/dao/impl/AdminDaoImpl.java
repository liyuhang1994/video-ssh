package com.zhiyou100.video.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyou100.video.dao.AdminDao;
import com.zhiyou100.video.dao.base.impl.BaseDaoImpl;
import com.zhiyou100.video.model.Admin;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public Admin findAdminByAdmin(Admin a) {
		List<Admin> list = (List<Admin>) this.getHibernateTemplate().find("from Admin where login_name=? and login_pwd=?", a.getLogin_name(),a.getLogin_pwd());
		if(list.size()>0){
			
			return list.get(0);
		}else{
			return null;
		}
	}

}
