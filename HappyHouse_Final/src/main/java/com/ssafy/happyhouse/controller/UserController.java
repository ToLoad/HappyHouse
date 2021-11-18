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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserParamDto;
import com.ssafy.happyhouse.model.WishlistDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("회원 관리 API")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "회원 등록", notes = "새로운 회원을 등록 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody @ApiParam(value = "회원 정보", required = true) UserDto userDto) throws Exception {
		logger.info("registerUser - 호출");
		
		// 등록이 되면 true를 리턴함
		if (userService.registerUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 목록", notes = "모든 회원 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserDto>> listUser(@ApiParam(value = "회원 정보를 얻기 위한 부가정보", required = true) UserParamDto userParamDto) throws Exception {
		logger.info("listUser - 호출");
		return new ResponseEntity<List<UserDto>>(userService.listUser(userParamDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "등록된 회원의 수", notes = "등록된 모든 회원의 수를 반환", response = Integer.class)
	@GetMapping("/totalcount")
	public ResponseEntity<Integer> getTotalCount(@ApiParam(value = "회원 정보를 얻기 위한 부가정보", required = true) UserParamDto userParamDto) throws Exception {
		logger.info("getTotalCount - 호출");
		return new ResponseEntity<Integer>(userService.getTotalCount(userParamDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 수정", notes = "회원의 정보를 수정", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody @ApiParam(value = "회원 정보", required = true) UserDto userDto) throws Exception {
		logger.info("updateUser - 호출");
		userService.updateUser(userDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 삭제", notes = "회원을 삭제", response = String.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> removeUser(@PathVariable("userid") @ApiParam(value = "삭제할 회원의 아이디", required = true) String id) throws Exception {
		logger.info("removeUser - 호출");
		userService.removeUser(id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원의 찜목록", notes = "회원이 찜한 아파트 번호를 가져옴", response = List.class)
	@GetMapping("/wishlist/{userid}")
	public ResponseEntity<List<WishlistDto>> getWishlist(@PathVariable("userid") @ApiParam(value = "찜목록을 조회할 회원아이디", required = true) String userid) throws Exception {
		logger.info("getWishlist - 호출");
		return new ResponseEntity<List<WishlistDto>>(userService.getWishlist(userid), HttpStatus.OK);
	}
	
}
