package com.zhiyou100.video.dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	void addEntity(T entity);

	void deleteEntity(T entity);

	void updateEntity(T entity);

	T findEntityById(Serializable id);

	List<T> findAllEntity();

}
