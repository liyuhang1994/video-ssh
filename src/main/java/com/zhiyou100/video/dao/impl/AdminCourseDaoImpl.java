package com.zhiyou100.video.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zhiyou100.video.dao.AdminCourseDao;
import com.zhiyou100.video.dao.base.impl.BaseDaoImpl;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.utils.Page;

@Repository
public class AdminCourseDaoImpl extends BaseDaoImpl<Course> implements AdminCourseDao{


	@Override
	public int allCourseCount() {
		
		List<Course> list = (List<Course>) this.getHibernateTemplate().find("from Course");
		return list.size();
	}

	@Override
	public List<Course> findCourseByLimit(int i) {
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		List<Course> list = (List<Course>) this.getHibernateTemplate().findByCriteria(dc,i,i+5);
		// List<Course> list = (List<Course>) this.getHibernateTemplate().find("from Course ");
		return list;
	}

	@Override
	public Course findCourseByName(String courseName) {
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		dc.add(Restrictions.eq("course_name", courseName));
		List<Course> list = (List<Course>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}

}
