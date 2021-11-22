package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AnswerDto;

@Mapper
public interface AnswerMapper {
	void registerAnswer(AnswerDto answerDto) throws SQLException;
	AnswerDto getAnswer(int num) throws SQLException;
	List<AnswerDto> listAnswer(int question_num) throws SQLException;
	void updateAnswer(AnswerDto answerDto) throws SQLException;
	void removeAnswer(int num) throws SQLException;
}
