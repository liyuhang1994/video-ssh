package com.zhiyou100.video.service;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.base.BaseService;
import com.zhiyou100.video.utils.Page;

public interface AdminVideoService extends BaseService<Video>{

	Page<Video> findAllVideo(String videoName, String speakerName, String courseName, String currentPage);

}
