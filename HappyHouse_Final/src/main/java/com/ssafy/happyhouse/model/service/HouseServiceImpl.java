package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getAptInDong(dong);
	}

	@Override
	public HouseInfoDto getApt(int no) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getApt(no);
	}

	@Override
	public List<HouseInfoDto> getRecommendList(String userid) throws Exception {
		UserDto userDto = sqlSession.getMapper(UserMapper.class).getUser(userid);
		return sqlSession.getMapper(HouseMapper.class).getRecommendList(userDto);
	}

}
