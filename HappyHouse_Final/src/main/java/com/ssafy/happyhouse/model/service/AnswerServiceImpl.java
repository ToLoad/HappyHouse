package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void registerAnswer(AnswerDto answerDto) throws Exception {
		sqlSession.getMapper(AnswerMapper.class).registerAnswer(answerDto);
	}

	@Override
	public AnswerDto getAnswer(int num) throws Exception {
		return sqlSession.getMapper(AnswerMapper.class).getAnswer(num);
	}

	@Override
	public List<AnswerDto> listAnswer(int question_num) throws Exception {
		return sqlSession.getMapper(AnswerMapper.class).listAnswer(question_num);
	}

	@Override
	public void updateAnswer(AnswerDto answerDto) throws Exception {
		sqlSession.getMapper(AnswerMapper.class).updateAnswer(answerDto);
	}

	@Override
	public void removeAnswer(int num) throws Exception {
		sqlSession.getMapper(AnswerMapper.class).removeAnswer(num);
	}

}
