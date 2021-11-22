package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParamDto;
import com.ssafy.happyhouse.model.mapper.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void registerQuestion(QuestionDto questionDto) throws Exception {
		sqlSession.getMapper(QuestionMapper.class).registerQuestion(questionDto);
	}

	@Override
	public QuestionDto getQuestion(int num) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getQuestion(num);
	}

	@Override
	public List<QuestionDto> listQuestion(QuestionParamDto questionParamDto) throws Exception {
		int start = questionParamDto.getPg() == 0 ? 0 : (questionParamDto.getPg() - 1) * questionParamDto.getSpp();
		questionParamDto.setStart(start);
		return sqlSession.getMapper(QuestionMapper.class).listQuestion(questionParamDto);
	}

	@Override
	public void updateQuestion(QuestionDto questionDto) throws Exception {
		sqlSession.getMapper(QuestionMapper.class).updateQuestion(questionDto);
	}

	@Override
	public void removeQuestion(int num) throws Exception {
		sqlSession.getMapper(QuestionMapper.class).removeQuestion(num);
	}

	@Override
	public int getTotalCount(QuestionParamDto questionParamDto) throws Exception {
		return sqlSession.getMapper(QuestionMapper.class).getTotalCount(questionParamDto);
	}

}
