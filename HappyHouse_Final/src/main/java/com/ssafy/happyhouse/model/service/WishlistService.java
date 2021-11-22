package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.WishlistDto;

public interface WishlistService {
	boolean registerWishlist(WishlistDto wishlistDto) throws Exception;
	void removeWishlist(WishlistDto wishlistDto) throws Exception;
	boolean checkDuplicate(WishlistDto wishlistDto) throws Exception;
	
	List<WishlistDto> getWishlist(String userid) throws Exception;
	int getWishlistCount(String userid) throws Exception;
}
