package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParamDto;

public interface NoticeService {
	void registerNotice(NoticeDto noticeDto) throws Exception;
	NoticeDto getNotice(int num) throws Exception;
	List<NoticeDto> listNotice(NoticeParamDto noticeParamDto) throws Exception;
	void updateNotice(NoticeDto noticeDto) throws Exception;
	void removeNotice(int num) throws Exception;
	int getTotalCount(NoticeParamDto noticeParamDto) throws Exception;
}
