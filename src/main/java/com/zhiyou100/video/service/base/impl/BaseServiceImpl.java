package com.zhiyou100.video.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.service.base.BaseService;


public class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	private BaseDao<T> bd;
	public void setBd(BaseDao<T> bd){
		this.bd = bd;
	}
	

	@Override
	public void addEntity(T entity) {
		bd.addEntity(entity);
	}

	@Override
	public void deleteEntity(T entity) {
		bd.deleteEntity(entity);
	}

	@Override
	public void updateEntity(T entity) {
		bd.updateEntity(entity);
	}

	@Override
	public T findEntityById(Serializable id) {
		return bd.findEntityById(id);
	}

	@Override
	public List<T> findAllEntity() {
		return bd.findAllEntity();
	}

}
