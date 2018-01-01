package com.zhiyou100.video.web.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.model.Result;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.FrontUserService;
import com.zhiyou100.video.utils.MD5Utils;
import com.zhiyou100.video.utils.MailUtil;

@Controller
@Scope(scopeName = "prototype")
public class FrontUserAction extends BaseAction<User>{

	@Autowired
	private FrontUserService fus;
	private Result re = new Result();
	
	private String oldPassword;
	private String newPassword;

	private File image_file;
	private String image_fileFileName;
	
	
	
	public File getImage_file() {
		return image_file;
	}

	public void setImage_file(File image_file) {
		this.image_file = image_file;
	}

	public String getImage_fileFileName() {
		return image_fileFileName;
	}

	public void setImage_fileFileName(String image_fileFileName) {
		this.image_fileFileName = image_fileFileName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Result getRe() {
		return re;
	}

	public void setRe(Result re) {
		this.re = re;
	}

	// 注册
	public String userRegister(){
		
		List<User> user = fus.findUserByUser(model);
		if (!user.isEmpty()) {
			re.setMessage("该用户已注册");
			re.setSuccess(false);
		} else {
			int num = (int) ((Math.random()*9+1)*10000);  
			String str = num+"";
			model.setInsert_time(new Date());
			model.setUpdate_time(new Date());
			model.setCaptcha(str);
			model.setPassword(MD5Utils.md5(model.getPassword()));
			//fus.addUser(model);
			System.out.println(model);
			fus.addEntity(model);
			re.setSuccess(true);
		}
		return "userRegister";
	}
	
	// 登录
	public String userLogin(){
		model.setPassword(MD5Utils.md5(model.getPassword()));
		List<User> user = fus.findUserByUser(model);
		if (user.isEmpty()) {
			re.setMessage("该用户未注册");
			re.setSuccess(false);
		} else {
			ActionContext.getContext().getSession().put("_front_user", user.get(0));
			// session.setAttribute("_front_user", user.get(0));
			re.setSuccess(true);
		}
		return "userLogin";
	}
	
	// 用户首页
	public String userIndex(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		List<User> user = fus.findUserByUser(u);
		ActionContext.getContext().put("user", user.get(0));
		return "userIndex";
	}
	
	// 跳转修改个人信息
	public String userProfileTZ(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		ActionContext.getContext().put("user", u);
		return "userProfileTZ";
	}
	
	// 修改个人信息
	public String userProfile(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		model.setId(u.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(model.getBirthdayStr());
			model.setBirthday(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.setUpdate_time(new Date());
		model.setEmail(u.getEmail());
		model.setPassword(u.getPassword());
		System.out.println(model);
		fus.updateEntity(model);
		ActionContext.getContext().getSession().put("_front_user",model);
		return "userProfile";
	}
	
	// 跳转到修改密码安全页面
	public String userPasswordTZ(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		ActionContext.getContext().put("user", u);
		return "userPasswordTZ";
	}
	
	// 修改密码
	public String userPassword(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		if (MD5Utils.md5(oldPassword).equals(u.getPassword())) {
			u.setPassword(MD5Utils.md5(newPassword));
			u.setUpdate_time(new Date());
			fus.updateEntity(u);
			ActionContext.getContext().getSession().put("_front_user", u);
			ActionContext.getContext().put("message", "密码修改成功");
		} else {
			ActionContext.getContext().put("message", "密码不正确");
		}
		
		return "userPassword";
	}
	
	// 跳转到修改头像
	public String userAvatarTZ(){
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		ActionContext.getContext().put("user", u);
		return "userAvatarTZ";
	}
	
	// 修改头像
	public String userAvatar() throws IOException{
		if(!image_fileFileName.equals("")){
		// 得到文件后缀名
		String ext = FilenameUtils.getExtension(image_fileFileName);
		// 生成随机文件名
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		String fileName = name+"."+ext;
		
		FileUtils.copyFile(image_file, new File("D:\\pic\\"+name+"."+ext));
		
		User u = (User) ActionContext.getContext().getSession().get("_front_user");
		u.setHead_url("upload/" + fileName);
		fus.updateEntity(u);
		ActionContext.getContext().getSession().put("_front_user",u);
		}
		return "userAvatar";
	}
	
	// 跳转到首页
	public String index(){
		return "index";
	}

	// 退出登录
	public String userLogout(){
		ActionContext.getContext().getSession().remove("_front_user");
		return "userLogout";
	}
	
	// 点击忘记密码
	public String forgetpwdTZ(){
		return "forgetpwdTZ";
	}
	
	// 发送验证码
	public String sendCaptcha() throws Exception{
		
		User u = fus.finUserByEmail(model.getEmail());
		if(u==null){
			re.setSuccess(false);
			re.setMessage("用户不存在");
		}else{
			re.setSuccess(true);
			String captcha = u.getCaptcha();
			MailUtil.send(model.getEmail(), "验证信息", captcha);
		}
		return "sendCaptcha";
	}
	
	// 邮箱验证完成,跳转到重置密码
	public String forgetpwd(){
		
		ActionContext.getContext().put("email", model.getEmail());
		ActionContext.getContext().put("captcha", model.getCaptcha());
		
		return "forgetpwd";
	}
	
	// 重置密码
	public String resetpwd(){
		int num = (int) ((Math.random()*9+1)*10000);  
		String str = num+"";
		User u = fus.finUserByEmail(model.getEmail());
		if(u.getCaptcha().equals(model.getCaptcha())){
			u.setPassword(MD5Utils.md5(model.getPassword()));
			u.setUpdate_time(new Date());
			u.setCaptcha(str);
			fus.updateEntity(u);
		}
		
		return "resetpwd";
	}
	
	
}
