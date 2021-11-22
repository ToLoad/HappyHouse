package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParamDto;

@Mapper
public interface NoticeMapper {
	void registerNotice(NoticeDto noticeDto) throws SQLException;
	NoticeDto getNotice(int num) throws SQLException;
	List<NoticeDto> listNotice(NoticeParamDto noticeParamDto) throws SQLException;
	void updateNotice(NoticeDto noticeDto) throws SQLException;
	void removeNotice(int num) throws SQLException;
	int getTotalCount(NoticeParamDto noticeParamDto) throws SQLException;
}
