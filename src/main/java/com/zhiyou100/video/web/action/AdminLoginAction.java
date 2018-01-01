package com.zhiyou100.video.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.Result;
import com.zhiyou100.video.service.AdminService;
import com.zhiyou100.video.utils.MD5Utils;

@Controller
@Scope(scopeName="prototype")
public class AdminLoginAction extends BaseAction<Admin>{
	
	@Autowired
	private AdminService as;
	// Result r = new Result();

	
	public String tzLogin(){
		return "tzLogin";
	}
	
	/*public String ajaxUserLogin(){
		
		// Admin a = as.findAdminByName(ad.getLogin_name());
		
		if(true){
			
			r.setMessage("success");
		}
		
		return "ajaxSuccess";
	}*/
	
	
	public String adminLogin(){
		model.setLogin_pwd(MD5Utils.md5(model.getLogin_pwd()));
		Admin a = as.findAdminByAdmin(model);
		System.out.println(a);
		 if(a!=null){
			return "loginSuccess";
		 }else{
			return null;
		}
	}
	
}
