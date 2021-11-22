package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
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
	@Autowired
	private JwtServiceImpl jwtService;
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호)", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디", required = true) String userid,
			HttpServletRequest request) {
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		logger.info(request.getHeader("access-token"));
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.getUser(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
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
	
	@ApiOperation(value = "회원 목록", notes = "모든 회원 정보, 현재 페이지, 페이지당 회원 수, 전체 회원 수를 반환", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> listUser(@ApiParam(value = "회원 정보를 얻기 위한 부가정보", required = true) UserParamDto userParamDto) throws Exception {
		logger.info("listUser - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		
		List<UserDto> list = userService.listUser(userParamDto);
		int totalCount = userService.getTotalCount(userParamDto);

		resultMap.put("page", userParamDto.getPg());
		resultMap.put("perPage", userParamDto.getSpp());
		resultMap.put("totalCount", totalCount);
		resultMap.put("data", list);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
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
