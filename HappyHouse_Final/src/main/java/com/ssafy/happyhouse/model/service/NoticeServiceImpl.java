package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParamDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void registerNotice(NoticeDto noticeDto) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).registerNotice(noticeDto);
	}

	@Override
	public NoticeDto getNotice(int num) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getNotice(num);
	}

	@Override
	public List<NoticeDto> listNotice(NoticeParamDto noticeParamDto) throws Exception {
		int start = noticeParamDto.getPg() == 0 ? 0 : (noticeParamDto.getPg() - 1) * noticeParamDto.getSpp();
		noticeParamDto.setStart(start);
		return sqlSession.getMapper(NoticeMapper.class).listNotice(noticeParamDto);
	}

	@Override
	public void updateNotice(NoticeDto noticeDto) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).updateNotice(noticeDto);
	}

	@Override
	public void removeNotice(int num) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).removeNotice(num);
	}

	@Override
	public int getTotalCount(NoticeParamDto noticeParamDto) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getTotalCount(noticeParamDto);
	}

}
