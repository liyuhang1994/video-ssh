package com.zhiyou100.video.web.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected T model;

	@Override
	public T getModel() {
		return model;
	}

	public BaseAction() {
		ParameterizedType superClass =  (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<T> classzz = (Class<T>) superClass.getActualTypeArguments()[0];
		try {
			model = classzz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
