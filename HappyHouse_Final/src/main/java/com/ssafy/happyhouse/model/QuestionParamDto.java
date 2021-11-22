package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QuestionParamDto : 질문게시판 파라미터 정보", description = "질문 목록을 얻기위한 부가적인 파라미터정보")
public class QuestionParamDto {
	@ApiModelProperty(value = "현재 페이지 번호", example = "1")
	private int pg;
	@ApiModelProperty(value = "페이지당 질문 수", example = "10")
	private int spp;
	@ApiModelProperty(value = "입력 X, 자동으로 값 찾아 넣음", example = "")
	private int start;
	@ApiModelProperty(value = "검색 조건")
	private String key;
	@ApiModelProperty(value = "검색어")
	private String word;
	
	public QuestionParamDto() {
		pg = 1;
		spp = 10;
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		pg = pg == 0 ? 1 : pg;
		this.pg = pg;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
