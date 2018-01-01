package com.zhiyou100.video.dao;

import java.util.List;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.utils.Page;

public interface AdminCourseDao extends BaseDao<Course>{


	int allCourseCount();

	List<Course> findCourseByLimit(int i);

	Course findCourseByName(String courseName);
}
