package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.AdminCourseDao;
import com.zhiyou100.video.dao.AdminSpeakerDao;
import com.zhiyou100.video.dao.AdminVideoDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.AdminVideoService;
import com.zhiyou100.video.service.base.impl.BaseServiceImpl;
import com.zhiyou100.video.utils.Page;

@Service
public class AdminVideoServiceImpl extends BaseServiceImpl<Video> implements AdminVideoService {

	@Autowired
	private AdminVideoDao avd;
	@Autowired
	private AdminSpeakerDao asd;
	@Autowired
	private AdminCourseDao acd;
	
	
	
	@Override
	public Page<Video> findAllVideo(String videoName, String speakerName, String courseName, String currentPage) {
		
		int count = avd.getAllVideoCountBySearch(videoName,speakerName,courseName);
		List<Video> list = avd.findAllVideoByLimit(videoName,speakerName,courseName,(Integer.parseInt(currentPage)-1)*5);
		for (Video video : list) {
			video.setSpeaker_name(asd.findEntityById(video.getSpeaker_id()).getSpeaker_name());
			video.setCourse_name(acd.findEntityById(video.getCourse_id()).getCourse_name());
		}
		Page<Video> page = new Page<>();
		page.setTotal(count);
		page.setPage(Integer.parseInt(currentPage));
		page.setSize(5);
		page.setRows(list);
		return page;
	}

}
