package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AnswerDto;

public interface AnswerService {
	void registerAnswer(AnswerDto answerDto) throws Exception;
	AnswerDto getAnswer(int num) throws Exception;
	List<AnswerDto> listAnswer(int question_num) throws Exception;
	void updateAnswer(AnswerDto answerDto) throws Exception;
	void removeAnswer(int num) throws Exception;
}
