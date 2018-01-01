package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.AdminCourseDao;
import com.zhiyou100.video.dao.AdminVideoDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.AdminStatisticsService;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService{

	@Autowired
	private AdminCourseDao acd;
	@Autowired
	private AdminVideoDao avd;
	
	int getAverageTimes(List<Video> list){
		int total = 0;
		for (Video video : list) {
			total += video.getVideo_play_times();
		}
		if(list.size()!=0){
			int AverageTimes = total/list.size();
			return AverageTimes;
		}else{
			return 0;
		}
	}

	@Override
	public List<Course> findAllCourse() {
		List<Course> list = acd.findAllEntity();
		for (Course course : list) {
			course.setAverage_times(getAverageTimes(avd.findVideoByCid(course.getId())));
		}
		return list;
	}
	
}
