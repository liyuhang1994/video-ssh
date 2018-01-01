package com.zhiyou100.video.service;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.service.base.BaseService;

public interface AdminService extends BaseService<Admin>{

	Admin findAdminByAdmin(Admin ad);

}
