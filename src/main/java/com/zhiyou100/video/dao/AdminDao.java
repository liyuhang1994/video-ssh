package com.zhiyou100.video.dao;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.model.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin findAdminByAdmin(Admin a);

}
