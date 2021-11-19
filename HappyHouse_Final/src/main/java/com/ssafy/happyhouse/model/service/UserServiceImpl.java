package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserParamDto;
import com.ssafy.happyhouse.model.WishlistDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean registerUser(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).registerUser(userDto) == 1;
	}

	@Override
	public UserDto getUser(String id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUser(id);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
	}

	@Override
	public void removeUser(String id) throws Exception {
		sqlSession.getMapper(UserMapper.class).removeUser(id);
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if (userDto.getUserid() == null || userDto.getPassword() == null) return null;
		
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

	@Override
	public List<UserDto> listUser(UserParamDto userParamDto) throws Exception {
		int start = userParamDto.getPg() == 0 ? 0 : (userParamDto.getPg() - 1) * userParamDto.getSpp();
		userParamDto.setStart(start);
		return sqlSession.getMapper(UserMapper.class).listUser(userParamDto);
	}

	@Override
	public List<WishlistDto> getWishlist(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getWishlist(userid);
	}

	@Override
	public int getTotalCount(UserParamDto userParamDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getTotalCount(userParamDto);
	}

}
