package com.zhiyou100.video.model;

import java.util.HashSet;
import java.util.Set;

public class Subject {
	private Integer id;

	private String subject_name;

	private Set<Course> courses = new HashSet<>();

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name == null ? null : subject_name.trim();
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subject_name=" + subject_name + "]";
	}

}