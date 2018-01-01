package com.zhiyou100.video.web.action;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhiyou100.video.dao.AdminVideoDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.AdminSubjectService;
import com.zhiyou100.video.utils.SecondFormat;

@Controller
@Scope(scopeName = "prototype")
public class FrontCourseAction extends BaseAction<Course>{

	@Autowired
	private AdminSubjectService ass;
	@Autowired
	private AdminVideoDao avd;
	
	private Integer subjectId;
	
	
	public Integer getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}


	// 跳转到课程展示页面
	public String courseIndex(){
		
		/*Set<Course> li = fcs.findCourseBySub(subjectId);
		Subject sub = ass.findEntityById(subjectId);
		ActionContext.getContext().put("courses", li);
		ActionContext.getContext().put("subjectId", subjectId);
		ActionContext.getContext().put("subject", sub);
		return "courseIndex";*/
		Subject s = ass.findEntityById(subjectId);
		Set<Course> courses = s.getCourses();
		for (Course course : courses) {
			List<Video> li = avd.findVideoByCid(course.getId());
			for (Video video : li) {
				video.setVideoLength(SecondFormat.secondFormat(video.getVideo_length()));
			}
			course.setVideoList(li);
		}
		ActionContext.getContext().put("courses", courses);
		ActionContext.getContext().put("subjectId", subjectId);
		ActionContext.getContext().put("subject", s);
		
		
		return  "courseIndex";
	}
}
