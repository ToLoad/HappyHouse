package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.WishlistDto;
import com.ssafy.happyhouse.model.service.WishlistService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/wishlist")
@Api("찜목록 관리 API")
public class WishlistController {
	private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	WishlistService wishlistService;
	
	@ApiOperation(value = "찜목록 등록", notes = "현재 아파트를 로그인한 유저의 찜목록에 추가", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerWishlist(@RequestBody @ApiParam(value = "찜을 위해 필요한 정보(userid, apt_no)", required = true) WishlistDto wishlistDto) throws Exception {
		if (wishlistService.registerWishlist(wishlistDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else return new ResponseEntity<String>("Already registered", HttpStatus.OK);
	}
	
	@ApiOperation(value = "찜목록 삭제", notes = "현재 아파트를 로그인한 유저의 찜목록에서 제거", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> removeWishlist(@RequestBody @ApiParam(value = "찜 제거를 위해 필요한 정보(userid, apt_no)", required = true)WishlistDto wishlistDto) throws Exception {
		wishlistService.removeWishlist(wishlistDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "찜여부 확인", notes = "현재 아파트가 로그인한 유저의 찜목록에 있는지 확인, 이미 찜 되어있다면 'yes' 아니면 'no'를 반환", response = String.class)
	@PostMapping("/check")
	public ResponseEntity<String> checkWishlist(@RequestBody @ApiParam(value = "찜 여부 확인을 위해 필요한 정보(userid, apt_no)", required = true) WishlistDto wishlistDto) throws Exception {
		if (wishlistService.checkDuplicate(wishlistDto)) {
			return new ResponseEntity<String>("yes", HttpStatus.OK); 
		} 
		return new ResponseEntity<String>("no", HttpStatus.OK);	
	}
	
	@ApiOperation(value = "회원의 찜목록", notes = "회원이 찜한 아파트 정보를 가져옴", response = List.class)
	@GetMapping("/{userid}")
	public ResponseEntity<List<HouseInfoDto>> getWishlist(@PathVariable("userid") @ApiParam(value = "찜목록을 조회할 회원아이디", required = true) String userid) throws Exception {
		logger.info("getWishlist - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(wishlistService.getWishlist(userid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원의 찜목록 개수", notes = "회원이 찜한 아파트 개수를 가져옴", response = Integer.class)
	@GetMapping("/count/{userid}")
	public ResponseEntity<Integer> getWishlistCount(@PathVariable("userid") @ApiParam(value = "찜목록 개수 조회할 회원아이디", required = true) String userid) throws Exception {
		logger.info("getWishlistCount - 호출");
		return new ResponseEntity<Integer>(wishlistService.getWishlistCount(userid), HttpStatus.OK);
	}
}
