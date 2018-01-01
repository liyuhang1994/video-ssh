package com.zhiyou100.video.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.AdminSpeakerService;
import com.zhiyou100.video.utils.Page;

@Controller
@Scope(scopeName = "prototype")
public class AdminSpeakerAction extends BaseAction<Speaker> {

	@Autowired
	private AdminSpeakerService ass;

	private String page;
	private String speakerName;
	private String speakerJob;

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getSpeakerJob() {
		return speakerJob;
	}

	public void setSpeakerJob(String speakerJob) {
		this.speakerJob = speakerJob;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// 主讲人列表
	public String speakerList() {
		speakerName = speakerName == null ? "" : speakerName;
		speakerJob = speakerJob == null ? "" : speakerJob;
		page = page == null ? "1" : page;
		Page<Speaker> pages = ass.findAllSpeaker(speakerName,speakerJob,page);
		ActionContext.getContext().put("pages",pages);
		ActionContext.getContext().put("speakerName",speakerName);
		ActionContext.getContext().put("speakerJob",speakerJob);
		return "speakerList";
	}
	
	// 跳转到添加主讲人
	public String speakerAddTZ(){
		return "speakerAddTZ";
	}
	
	// 添加主讲人
	public String speakerAdd(){
		ass.addEntity(model);
		return "speakerAdd";
	}
	
	// 删除主讲人
	public String speakerDelete(){
		ass.deleteEntity(model);
		return "speakerDelete";
	}

	// 跳转到编辑主讲人
	public String speakerEditTZ(){
		Speaker s = ass.findEntityById(model.getId());
		ActionContext.getContext().put("speaker", s);
		return "speakerEditTZ";
	}
	
	// 编辑主讲人
	public String speakerEdit(){
		ass.updateEntity(model);
		return "speakerEdit";
	}
	
	
}
