package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParamDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
@Api("공지사항 관리 API")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "공지사항 등록", notes = "공지사항을 등록", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerNotice(@RequestBody @ApiParam(value = "공지사항 등록시 필요한 정보(title, content, writer_id)", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("registerNotice - 호출");
		noticeService.registerNotice(noticeDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 조회", notes = "해당 번호의 공지사항을 조회", response = NoticeDto.class)
	@GetMapping("/{num}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("num") @ApiParam(value = "공지사항 번호", example = "1", required = true) int num) throws Exception {
		logger.info("getNotice - 호출");
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(num), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 전체 조회", notes = "조건에 맞는 공지사항 전체와 공지사항 수, 현재 페이지, 페이지당 공지사항 수를 반환", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> listNotice(@ApiParam(value = "공지사항을 알기위한 부가정보", required = true) NoticeParamDto noticeParamDto) throws Exception {
		logger.info("listNotice - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		
		List<NoticeDto> list = noticeService.listNotice(noticeParamDto);
		int totalCount = noticeService.getTotalCount(noticeParamDto);
		
		resultMap.put("page", noticeParamDto.getPg());
		resultMap.put("perPage", noticeParamDto.getSpp());
		resultMap.put("totalCount", totalCount);
		resultMap.put("data", list);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 수정", notes = "공지사항을 수정", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateNotice(@RequestBody @ApiParam(value = "공지사항 수정에 필요한 정보(num, title, content)", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("updateNotice - 호출");
		noticeService.updateNotice(noticeDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 삭제", notes = "해당 번호의 공지사항을 삭제", response = String.class)
	@DeleteMapping("/{num}")
	public ResponseEntity<String> removeNotice(@PathVariable("num") @ApiParam(value = "공지사항 번호", example = "1", required = true) int num) throws Exception {
		logger.info("removeNotice - 호출");
		noticeService.removeNotice(num);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
