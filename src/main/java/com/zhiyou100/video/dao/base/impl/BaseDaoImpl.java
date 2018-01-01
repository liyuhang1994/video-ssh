package com.zhiyou100.video.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.dao.base.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> classz;
	
	public BaseDaoImpl(){
		ParameterizedType superClass = (ParameterizedType)this.getClass().getGenericSuperclass();
		classz = (Class<T>)superClass.getActualTypeArguments()[0];
	}
	
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	

	@Override
	public void addEntity(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void deleteEntity(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void updateEntity(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findEntityById(Serializable id) {
		return this.getHibernateTemplate().get(classz, id);
	}

	@Override
	public List<T> findAllEntity() {
		String hql = "from "+classz.getSimpleName();
		List<T> list = (List<T>) this.getHibernateTemplate().find(hql);
		return list;
	}

}
