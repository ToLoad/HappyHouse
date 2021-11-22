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

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParamDto;
import com.ssafy.happyhouse.model.service.AnswerService;
import com.ssafy.happyhouse.model.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("QnA 관리 API")
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	
	// 질문 게시판 부분
	@ApiOperation(value = "질문 등록", notes = "질문을 등록", response = String.class)
	@PostMapping("/question")
	public ResponseEntity<String> registerQuestion(@RequestBody @ApiParam(value = "질문을 등록(title, content, writer_id)", required = true) QuestionDto questionDto) throws Exception {
		logger.info("registerQuestion - 호출");
		questionService.registerQuestion(questionDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문 조회", notes = "해당 번호의 질문을 조회", response = QuestionDto.class)
	@GetMapping("/question/{num}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("num") @ApiParam(value = "질문 글번호", example = "1", required = true) int num) throws Exception {
		logger.info("getQuestion - 호출");
		return new ResponseEntity<QuestionDto>(questionService.getQuestion(num), HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문 전체 조회", notes = "조건에 맞는 질문 전체와 질문 수, 현재 페이지, 페이지당 질문 수를 반환", response = Map.class)
	@GetMapping("/question")
	public ResponseEntity<Map<String, Object>> listQuestion(@ApiParam(value = "질문을 알기위한 부가정보", required = true) QuestionParamDto questionParamDto) throws Exception {
		logger.info("listQuestion - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		
		List<QuestionDto> list = questionService.listQuestion(questionParamDto);
		int totalCount = questionService.getTotalCount(questionParamDto);
		
		resultMap.put("page", questionParamDto.getPg());
		resultMap.put("perPage", questionParamDto.getSpp());
		resultMap.put("totalCount", totalCount);
		resultMap.put("data", list);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문 수정", notes = "질문을 수정", response = String.class)
	@PutMapping("/question")
	public ResponseEntity<String> updateQuestion(@RequestBody @ApiParam(value = "질문 수정에 필요한 정보(num, title, content)") QuestionDto questionDto) throws Exception {
		logger.info("updateQuestion - 호출");
		questionService.updateQuestion(questionDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문 삭제", notes = "질문 글번호에 해당되는 질문을 삭제", response = String.class)
	@DeleteMapping("/question/{num}")
	public ResponseEntity<String> removeQuestion(@PathVariable("num") @ApiParam(value ="질문 글번호", example = "1", required = true) int num) throws Exception {
		logger.info("removeQuestion - 호출");
		questionService.removeQuestion(num);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 답변 부분
	@ApiOperation(value = "답변 등록", notes = "해당 글에 대한 답변을 등록", response = String.class)
	@PostMapping("/answer")
	public ResponseEntity<String> registerAnswer(@RequestBody @ApiParam(value = "답변 등록에 필요한 정보(question_num, title, content, writer_id)", required = true) AnswerDto answerDto) throws Exception {
		logger.info("registerAnswer - 호출");
		answerService.registerAnswer(answerDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "답변 조회", notes = "해당 번호의 답변을 조회", response = AnswerDto.class)
	@GetMapping("/answer/{num}")
	public ResponseEntity<AnswerDto> getAnswer(@PathVariable("num") @ApiParam(value = "답변 글번호", example = "1", required = true) int num) throws Exception {
		logger.info("getAnswer - 호출");
		return new ResponseEntity<AnswerDto>(answerService.getAnswer(num), HttpStatus.OK);
	}
	
	@ApiOperation(value = "답변 전체 조회", notes = "해당 질문의 답변 전체를 조회", response = List.class)
	@GetMapping("/answer/list/{question_num}")
	public ResponseEntity<List<AnswerDto>> listAnswer(@PathVariable("question_num") @ApiParam(value = "질문 글번호", example = "1", required = true) int question_num) throws Exception {
		logger.info("listAnswer - 호출");
		return new ResponseEntity<List<AnswerDto>>(answerService.listAnswer(question_num), HttpStatus.OK);
	}
	
	@ApiOperation(value = "답변 수정", notes = "답변을 수정", response = String.class)
	@PutMapping("/answer")
	public ResponseEntity<String> updateAnswer(@RequestBody @ApiParam(value = "답변 수정에 필요한 정보(num, title, content)", required = true) AnswerDto answerDto) throws Exception {
		logger.info("updateAnswer - 호출");
		answerService.updateAnswer(answerDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "답변 삭제", notes = "답변을 삭제", response = String.class)
	@DeleteMapping("/answer/{num}")
	public ResponseEntity<String> removeAnswer(@PathVariable("num") @ApiParam(value = "답변 글번호", example = "1", required = true) int num) throws Exception {
		answerService.removeAnswer(num);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
