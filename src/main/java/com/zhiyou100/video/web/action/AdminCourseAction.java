package com.zhiyou100.video.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.AdminCourseService;
import com.zhiyou100.video.service.AdminSubjectService;
import com.zhiyou100.video.utils.Page;

@Controller
@Scope(scopeName = "prototype")
public class AdminCourseAction extends BaseAction<Course> {

	@Autowired
	private AdminCourseService acs;
	@Autowired
	private AdminSubjectService ass;
	
	private String page;


	public void setPage(String page) {
		this.page = page;
	}

	// 课程列表
	public String courseList() {
		
		page = page == null ? "1" : page;
		Page<Course> pages = acs.findAllCourse(page);
		// ActionContext.getContext().getValueStack().push(pages);
		ActionContext.getContext().put("pages",pages);
		return "courseList";
	}
	
	// 跳转到增加课程
	public String addCourseTZ(){
		// 得到所有学科
		List<Subject> list = ass.findAllEntity();
		ActionContext.getContext().put("sub", list);
		return "addCourseTZ";
	}
	
	// 添加课程
	public String addCourse(){
		Subject s = ass.findEntityById(model.getSubject_id());
		model.setSubject(s);
		acs.addEntity(model);
		return "addCourse";
	}
	
	// 删除课程
	public String courseDelete(){
		acs.deleteEntity(model);
		return "courseDelete";
	}
	
	// 跳转到编辑
	public String courseEditTZ(){
		Course c = acs.findEntityById(model.getId());
		// Subject s = ass.findEntityById(c.getSubject().getId());
		List<Subject> list = ass.findAllEntity();
		ActionContext.getContext().put("course", c);
		ActionContext.getContext().put("sub", list);
		return "courseEditTZ";
	}
	
	// 编辑课程
	public String courseEdit(){
		Subject s = ass.findEntityById(model.getSubject_id());
		model.setSubject(s);
		acs.updateEntity(model);
		return "courseEdit";
	}

}
