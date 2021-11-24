package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.WishlistDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.mapper.WishlistMapper;

@Service
public class WishlistServiceImpl implements WishlistService {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public boolean registerWishlist(WishlistDto wishlistDto) throws Exception {
		if (sqlSession.getMapper(WishlistMapper.class).checkDuplicate(wishlistDto) == 0) {
			// 등록시 평균값 수정
			setUserAddAvg(wishlistDto);			
			
			sqlSession.getMapper(WishlistMapper.class).registerWishlist(wishlistDto);
			return true;
		} else return false;
		
	}

	@Override
	public void removeWishlist(WishlistDto wishlistDto) throws Exception {
		// 삭제시 평균값 수정
		setUserRemoveAvg(wishlistDto);
		sqlSession.getMapper(WishlistMapper.class).removeWishlist(wishlistDto);
	}

	@Override
	public boolean checkDuplicate(WishlistDto wishlistDto) throws Exception {
		// 중복이면 true를 리턴
		if (sqlSession.getMapper(WishlistMapper.class).checkDuplicate(wishlistDto) != 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<HouseInfoDto> getWishlist(String userid) throws Exception {
		return sqlSession.getMapper(WishlistMapper.class).getWishlist(userid);
	}
	
	@Override
	public int getWishlistCount(String userid) throws Exception {
		return sqlSession.getMapper(WishlistMapper.class).getWishlistCount(userid);
	}
	
	private void setUserAddAvg(WishlistDto wishlistDto) throws Exception {
		// 유저 아이디로 정보 가져옴
		UserDto userDto = sqlSession.getMapper(UserMapper.class).getUser(wishlistDto.getUserid());
		// 추가할 아파트 정보 가져옴
		HouseInfoDto houseInfoDto = sqlSession.getMapper(HouseMapper.class).getApt(wishlistDto.getApt_no());
		
		int curCount = getWishlistCount(wishlistDto.getUserid());
		int money = Integer.parseInt(houseInfoDto.getRecentPrice().replace(",", ""));
		int area = (int) Math.round(Double.parseDouble(houseInfoDto.getArea()));		
		
		int nAvg_price = ((userDto.getAvg_price() * curCount) + money) / (curCount + 1);
		int nAvg_size = ((userDto.getAvg_size() * curCount) + area) / (curCount + 1);
		int nAvg_floor = ((userDto.getAvg_floor() * curCount) + Integer.parseInt(houseInfoDto.getFloor())) / (curCount + 1);
		userDto.setAvg_price(nAvg_price);
		userDto.setAvg_size(nAvg_size);
		userDto.setAvg_floor(nAvg_floor);
		
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
	}
	
	private void setUserRemoveAvg(WishlistDto wishlistDto) throws Exception {
		// 유저 아이디로 정보 가져옴
		UserDto userDto = sqlSession.getMapper(UserMapper.class).getUser(wishlistDto.getUserid());
		// 추가할 아파트 정보 가져옴
		HouseInfoDto houseInfoDto = sqlSession.getMapper(HouseMapper.class).getApt(wishlistDto.getApt_no());
		
		// 제거 후 남은 찜목록이 없다면 0으로 초기화
		int curCount = getWishlistCount(wishlistDto.getUserid());
		if (curCount - 1 == 0) {
			userDto.setAvg_price(0);
			userDto.setAvg_size(0);
			userDto.setAvg_floor(0);
			sqlSession.getMapper(UserMapper.class).updateUser(userDto);
			return;
		}
		
		int money = Integer.parseInt(houseInfoDto.getRecentPrice().replace(",", ""));
		int area = (int) Math.round(Double.parseDouble(houseInfoDto.getArea()));		
		
		int nAvg_price = ((userDto.getAvg_price() * curCount) - money) / (curCount - 1);
		int nAvg_size = ((userDto.getAvg_size() * curCount) - area) / (curCount - 1);
		int nAvg_floor = ((userDto.getAvg_floor() * curCount) - Integer.parseInt(houseInfoDto.getFloor())) / (curCount - 1);
		userDto.setAvg_price(nAvg_price);
		userDto.setAvg_size(nAvg_size);
		userDto.setAvg_floor(nAvg_floor);
		
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
	}

}
