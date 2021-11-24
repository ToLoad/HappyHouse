package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.WishlistDto;

@Mapper
public interface WishlistMapper {
	void registerWishlist(WishlistDto wishlistDto) throws SQLException;
	void removeWishlist(WishlistDto wishlistDto) throws SQLException;
	int checkDuplicate(WishlistDto wishlistDto) throws SQLException;
	
	List<HouseInfoDto> getWishlist(String userid) throws SQLException;
	int getWishlistCount(String userid) throws SQLException;
}
