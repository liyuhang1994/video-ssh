package com.zhiyou100.video.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.zhiyou100.video.dao.AdminVideoDao;
import com.zhiyou100.video.dao.base.impl.BaseDaoImpl;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;

@Repository
public class AdminVideoDaoImpl extends BaseDaoImpl<Video> implements AdminVideoDao{

	@Override
	public int getAllVideoCountBySearch(String videoName, String speakerName, String courseName) {
		
		Integer c = this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				BigInteger count = (BigInteger) session.createSQLQuery("select count(*) from (video v left join speaker s on v.speaker_id = s.id) "
						+ "left join course c on v.course_id = c.id "
						+ "where v.video_title like '%"+videoName+"%' "
								+ "and s.speaker_name like '%"+speakerName+"%' and c.course_name like '%"+courseName+"%'").uniqueResult();
				return new BigInteger(count+"").intValue();
			}
		});
		
		
		return c;
	}
	
	@Override
	public List<Video> findAllVideoByLimit(String videoName, String speakerName, String courseName, int i) {
		String str = i+"";
		List<Video> list = this.getHibernateTemplate().execute(new HibernateCallback<List<Video>>() {
   
			@Override
			public List<Video> doInHibernate(Session session) throws HibernateException {
				List<Video> list2 = session.createSQLQuery("select v.*,s.speaker_name,c.course_name from (video v left join speaker s on v.speaker_id = s.id) "
						+ "left join course c on v.course_id = c.id "
						+ "where v.video_title like '%"+videoName+"%' "
								+ "and s.speaker_name like '%"+speakerName+"%' and c.course_name like '%"+courseName+"%' limit "+str+",5").addEntity(Video.class).list();
				return list2;
			}
		});
		System.out.println(list);
		return list;
	}

	@Override
	public List<Video> findVideoByCid(Integer id) {
		String str = id+"";
		List<Video> li = this.getHibernateTemplate().execute(new HibernateCallback<List<Video>>() {

			@Override
			public List<Video> doInHibernate(Session session) throws HibernateException {
				List<Video> list = session.createSQLQuery("select * from video where course_id = "+str).addEntity(Video.class).list();
				return list;
			}
		});
		System.out.println(li);
		return li;
	}

}
