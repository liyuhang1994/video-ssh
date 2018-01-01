package com.zhiyou100.video.service;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.service.base.BaseService;
import com.zhiyou100.video.utils.Page;

public interface AdminCourseService extends BaseService<Course>{

	Page<Course> findAllCourse(String page);

}
