package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.dao.AdminSpeakerDao;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.AdminSpeakerService;
import com.zhiyou100.video.service.base.impl.BaseServiceImpl;
import com.zhiyou100.video.utils.Page;

@Service
public class AdminSpeakerServiceImpl extends BaseServiceImpl<Speaker> implements AdminSpeakerService{

	@Autowired
	private AdminSpeakerDao asd;
	
	@Override
	public Page<Speaker> findAllSpeaker(String speakerName, String speakerJob, String page) {
		int count = asd.count(speakerName,speakerJob);
		List<Speaker> list = asd.findAllSpeakerByLimit(speakerName,speakerJob,(Integer.parseInt(page)-1)*5);
		Page<Speaker> pages = new Page<>();
		pages.setTotal(count);
		pages.setPage(Integer.parseInt(page));
		pages.setSize(5);
		pages.setRows(list);
		return pages;
	}

}
