package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/house")
@Api("House 컨트롤러  API V1")
public class HouseController {
	private final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	@Autowired
	private HouseService houseService;
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> getSido() throws Exception {
		logger.info("getSido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "해당 시도의 구군을 반환", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> getGugunInSido(@RequestParam("sido") @ApiParam(value = "시도코드", required = true) String sido) throws Exception {
		logger.info("getGugunInSido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보", notes = "해당 구군의 동을 반환", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> getDongInGugun(@RequestParam("gugun") @ApiParam(value = "구군코드", required = true) String gugun) throws Exception {
		logger.info("getDongInGugun - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 리스트", notes = "해당 동의 아파트 전체를 반환", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> getAptInDong(@RequestParam("dong") @ApiParam(value = "동코드", required = true) String dong) throws Exception {
		logger.info("getAptInDong - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 상세정보", notes = "해당 번호의 아파트를 반환", response = List.class)
	@GetMapping("/apt/{no}")
	public ResponseEntity<HouseInfoDto> getAptInfo(@PathVariable("no") @ApiParam(value = "아파트 식별번호", required = true, example = "1") int no) throws Exception {
		logger.info("getAptInfo - 호출");
		return new ResponseEntity<HouseInfoDto>(houseService.getApt(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원별 추천 아파트", notes = "해당 아이디의 회원에게 맞는 5개의 추천 아파트 반환", response = List.class)
	@GetMapping("/recommend/{userid}")
	public ResponseEntity<List<HouseInfoDto>> getRecommendList(@PathVariable("userid")  @ApiParam(value = "회원 아이디", required = true) String userid) throws Exception {
		logger.info("getRecommendList - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getRecommendList(userid), HttpStatus.OK);
	}
}
