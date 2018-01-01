package com.zhiyou100.video.dao;

import java.util.List;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.model.Speaker;

public interface AdminSpeakerDao extends BaseDao<Speaker>{

	int count(String speakerName, String speakerJob);

	List<Speaker> findAllSpeakerByLimit(String speakerName, String speakerJob, int i);

	Speaker findSpeakerByName(String speakerName);

}
