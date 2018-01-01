package com.zhiyou100.video.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.service.AdminStatisticsService;

@Controller
@Scope(scopeName = "prototype")
public class AdminStatisticsAction {

	@Autowired
	private AdminStatisticsService ass;
	
	public String statisticsList(){
		List<Course> list = ass.findAllCourse();
		ActionContext.getContext().put("list", list);
		return "statisticsList";
	}
	
}
