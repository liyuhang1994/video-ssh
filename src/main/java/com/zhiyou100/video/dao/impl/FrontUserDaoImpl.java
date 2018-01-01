package com.zhiyou100.video.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.zhiyou100.video.dao.FrontUserDao;
import com.zhiyou100.video.dao.base.impl.BaseDaoImpl;
import com.zhiyou100.video.model.User;

@Repository
public class FrontUserDaoImpl extends BaseDaoImpl<User> implements FrontUserDao{

	@Override
	public List<User> findUserByUser(User model) {
		
		List<User> li = this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {
				List<User> list = session.createSQLQuery("select * from user where email= '"+model.getEmail()+"' and password= '"+model.getPassword()+"'").addEntity(User.class).list();
				return list;
			}
		});
		return li;
	}

	@Override
	public void addUser(User model) {
	
		addEntity(model);
		
	}

	@Override
	public User finUserByEmail(String email) {
		
		User user = this.getHibernateTemplate().execute(new HibernateCallback<User>() {

			@Override
			public User doInHibernate(Session session) throws HibernateException {
				List<User> list = session.createSQLQuery("select * from user where email= '"+email+"'").addEntity(User.class).list();
				System.out.println(list);
				if(list.isEmpty()){
					return null;
				}else{
					return list.get(0);
				}
			}
		});
		return user;
	}

}
