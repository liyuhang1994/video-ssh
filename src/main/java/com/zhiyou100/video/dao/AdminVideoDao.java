package com.zhiyou100.video.dao;

import java.util.List;

import com.zhiyou100.video.dao.base.BaseDao;
import com.zhiyou100.video.model.Video;

public interface AdminVideoDao extends BaseDao<Video>{

	int getAllVideoCountBySearch(String videoName, String speakerName, String courseName);

	List<Video> findAllVideoByLimit(String videoName, String speakerName, String courseName, int i);

	List<Video> findVideoByCid(Integer id);

}
