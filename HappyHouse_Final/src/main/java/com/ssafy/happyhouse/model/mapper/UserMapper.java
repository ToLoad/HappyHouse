package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserParamDto;
import com.ssafy.happyhouse.model.WishlistDto;

@Mapper
public interface UserMapper {
	// 기본 유저 기능
	public int registerUser(UserDto userDto) throws SQLException;
	public UserDto getUser(String id) throws SQLException;
	public void updateUser(UserDto userDto) throws SQLException;
	public void removeUser(String id) throws SQLException;
	
	public UserDto login(UserDto userDto) throws SQLException;
	
	// 어드민 유저 기능
	public List<UserDto> listUser(UserParamDto userParamDto) throws SQLException;
	public int getTotalCount(UserParamDto userParamDto) throws SQLException;
	
	// 위시리스트 가져오기
	public List<WishlistDto> getWishlist(String userid) throws SQLException;
}
