package com.zhiyou100.video.web.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.dao.AdminCourseDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.AdminSpeakerService;
import com.zhiyou100.video.service.AdminVideoService;
import com.zhiyou100.video.utils.Page;

@Controller
@Scope(scopeName = "prototype")
public class AdminVideoAction extends BaseAction<Video> {

	@Autowired
	private AdminVideoService avs;
	@Autowired
	private AdminSpeakerService ass;
	@Autowired
	private AdminCourseDao acd;

	private Integer[] ids;


	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	private String videoName;
	private String speakerName;
	private String courseName;
	private String page;

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// 视频列表
	public String videoList() {
		videoName = videoName == null ? "" : videoName;
		speakerName = (speakerName == null || speakerName.equals("请选择主讲人")) ? "" : speakerName;
		courseName = (courseName == null || courseName.equals("请选择课程")) ? "" : courseName;
		page = page == null ? "1" : page;

		/*
		 * System.out.println("videoName--"+videoName);
		 * System.out.println("speakerName--"+speakerName);
		 * System.out.println("courseName--"+courseName);
		 */

		Page<Video> pages = avs.findAllVideo(videoName, speakerName, courseName, page);
		List<Speaker> sp = ass.findAllEntity();
		List<Course> co = acd.findAllEntity();

		ActionContext.getContext().put("videoName", videoName);
		ActionContext.getContext().put("speakerName", speakerName);
		ActionContext.getContext().put("courseName", courseName);
		ActionContext.getContext().put("pages", pages);
		ActionContext.getContext().put("sp", sp);
		ActionContext.getContext().put("co", co);

		return "videoList";
	}

	// 跳转到添加视频
	public String videoAddTZ() {
		List<Speaker> sp = ass.findAllEntity();
		List<Course> co = acd.findAllEntity();
		ActionContext.getContext().put("sp", sp);
		ActionContext.getContext().put("co", co);
		return "videoAddTZ";
	}

	// 添加视频
	public String videoAdd() {
		model.setInsert_time(new Date());
		model.setUpdate_time(new Date());
		avs.addEntity(model);
		return "videoAdd";
	}

	// 删除视频
	public String videoDelete() {
		avs.deleteEntity(model);
		return "videoDelete";
	}

	// 删除全部
	public String videoDeleteAll() {
		Video v = new Video();
		if (ids != null) {
			for (Integer integer : ids) {
				v.setId(integer);
				avs.deleteEntity(v);
			}
		}
		return "videoDeleteAll";
	}
	
	// 跳转到修改视频
	public String videoEditTZ(){
		List<Speaker> sp = ass.findAllEntity();
		List<Course> co = acd.findAllEntity();
		Video v = avs.findEntityById(model.getId());
		v.setSpeaker_name(ass.findEntityById(v.getSpeaker_id()).getSpeaker_name());
		v.setCourse_name(acd.findEntityById(v.getCourse_id()).getCourse_name());
		ActionContext.getContext().put("sp", sp);
		ActionContext.getContext().put("co", co);
		ActionContext.getContext().put("video", v);
		return "videoEditTZ";
	}
	
	// 修改视频
	public String videoEdit(){
		model.setUpdate_time(new Date());
		avs.updateEntity(model);
		return "videoEdit";
	}
	
	
	

}
