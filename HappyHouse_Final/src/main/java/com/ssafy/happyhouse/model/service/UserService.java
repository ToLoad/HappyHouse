package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserParamDto;
import com.ssafy.happyhouse.model.WishlistDto;

public interface UserService {
	// 기본 유저 기능
	public boolean registerUser(UserDto userDto) throws Exception;
	public UserDto getUser(String id) throws Exception;
	public void updateUser(UserDto userDto) throws Exception;
	public void removeUser(String id) throws Exception;
	
	public UserDto login(UserDto userDto) throws Exception;
	
	// 어드민 유저 기능
	public List<UserDto> listUser(UserParamDto userParamDto) throws Exception;
	public int getTotalCount(UserParamDto userParamDto) throws Exception;

	// 위시리스트 가져오기
	public List<WishlistDto> getWishlist(String userid) throws Exception;
}
