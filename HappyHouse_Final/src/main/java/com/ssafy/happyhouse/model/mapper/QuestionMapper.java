package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParamDto;

public interface QuestionMapper {
	void registerQuestion(QuestionDto questionDto) throws SQLException;
	QuestionDto getQuestion(int num) throws SQLException;
	List<QuestionDto> listQuestion(QuestionParamDto questionParamDto) throws SQLException;
	void updateQuestion(QuestionDto questionDto) throws SQLException;
	void removeQuestion(int num) throws SQLException;
	int getTotalCount(QuestionParamDto questionParamDto) throws SQLException;
}
