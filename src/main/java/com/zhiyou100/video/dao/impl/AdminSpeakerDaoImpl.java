package com.zhiyou100.video.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zhiyou100.video.dao.AdminSpeakerDao;
import com.zhiyou100.video.dao.base.impl.BaseDaoImpl;
import com.zhiyou100.video.model.Speaker;

@Repository
public class AdminSpeakerDaoImpl extends BaseDaoImpl<Speaker> implements AdminSpeakerDao{

	@Override
	public int count(String speakerName, String speakerJob) {
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.like("speaker_name","%"+speakerName+"%")).
		add(Restrictions.like("speaker_job","%"+speakerJob+"%"));
		List<Speaker> list = (List<Speaker>) this.getHibernateTemplate().findByCriteria(dc);
		return list.size();
	}

	@Override
	public List<Speaker> findAllSpeakerByLimit(String speakerName, String speakerJob, int i) {
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.like("speaker_name","%"+speakerName+"%")).add(Restrictions.like("speaker_job","%"+speakerJob+"%"));
		List<Speaker> list = (List<Speaker>) this.getHibernateTemplate().findByCriteria(dc,i,i+5);
		return list;
	}

	@Override
	public Speaker findSpeakerByName(String speakerName) {
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.eq("speaker_name", speakerName));
		List<Speaker> list = (List<Speaker>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}

}
