package com.zhiyou100.video.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.dao.AdminVideoDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.AdminCourseService;
import com.zhiyou100.video.service.AdminSpeakerService;
import com.zhiyou100.video.service.AdminSubjectService;
import com.zhiyou100.video.service.AdminVideoService;
import com.zhiyou100.video.utils.SecondFormat;

@Controller
@Scope(scopeName = "prototype")
public class FrontVideoAction {

	@Autowired
	private AdminVideoService avs;
	@Autowired
	private AdminCourseService acs;
	@Autowired
	private AdminSpeakerService ass;
	@Autowired
	private AdminVideoDao avd;
	@Autowired
	private AdminSubjectService as;
	
	private Integer videoId;
	private Integer subjectId;
	
	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String videoIndex(){
		
		Video v = avs.findEntityById(videoId);
		v.setVideo_play_times(v.getVideo_play_times()+1);
		avs.updateEntity(v);
		Course c = acs.findEntityById(v.getCourse_id());
		Speaker s = ass.findEntityById(v.getSpeaker_id());
		List<Video> li = avd.findVideoByCid(c.getId());
		for (Video video : li) {
			video.setVideoLength(SecondFormat.secondFormat(video.getVideo_length()));
		}
		Subject sub = as.findEntityById(subjectId);
		
		ActionContext.getContext().put("video", v);
		ActionContext.getContext().put("speaker", s);
		ActionContext.getContext().put("course", c);
		ActionContext.getContext().put("videoList", li);
		ActionContext.getContext().put("subject", sub);
		
		return "videoIndex";
	}
	
	
}
