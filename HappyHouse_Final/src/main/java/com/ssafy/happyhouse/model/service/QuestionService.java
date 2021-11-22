package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParamDto;

public interface QuestionService {
	void registerQuestion(QuestionDto questionDto) throws Exception;
	QuestionDto getQuestion(int num) throws Exception;
	List<QuestionDto> listQuestion(QuestionParamDto questionParamDto) throws Exception;
	void updateQuestion(QuestionDto questionDto) throws Exception;
	void removeQuestion(int num) throws Exception;
	int getTotalCount(QuestionParamDto questionParamDto) throws Exception;
}
