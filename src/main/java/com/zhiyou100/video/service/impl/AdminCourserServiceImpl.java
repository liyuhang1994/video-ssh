package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.AdminCourseDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.service.AdminCourseService;
import com.zhiyou100.video.service.base.impl.BaseServiceImpl;
import com.zhiyou100.video.utils.Page;

@Service
public class AdminCourserServiceImpl extends BaseServiceImpl<Course> implements AdminCourseService{

	@Autowired
	private AdminCourseDao acd;
	
	@Override
	public Page<Course> findAllCourse(String page) {
		int count = acd.allCourseCount();
		List<Course> list = acd.findCourseByLimit((Integer.parseInt(page)-1)*5);
		Page<Course> pages = new Page<>();
		pages.setTotal(count);
		pages.setPage(Integer.parseInt(page));
		pages.setSize(5);
		pages.setRows(list);
		return pages;
	}

}
