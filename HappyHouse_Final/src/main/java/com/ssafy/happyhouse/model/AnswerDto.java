package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AnswerDto : 답변 정보")
public class AnswerDto {
	@ApiModelProperty(value = "답변 번호", example = "1")
	private int num;
	@ApiModelProperty(value = "해당 답변의 질문 번호", example = "1")
	private int question_num;
	@ApiModelProperty(value = "답변 제목")
	private String title;
	@ApiModelProperty(value = "답변 내용")
	private String content;
	@ApiModelProperty(value = "답변자 아이디")
	private String writer_id;
	@ApiModelProperty(value = "답변 날짜")
	private String write_time;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getQuestion_num() {
		return question_num;
	}
	public void setQuestion_num(int question_num) {
		this.question_num = question_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getWrite_time() {
		return write_time;
	}
	public void setWrite_time(String write_time) {
		this.write_time = write_time;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnswerDto [num=").append(num).append(", question_num=").append(question_num).append(", title=")
				.append(title).append(", content=").append(content).append(", writer_id=").append(writer_id)
				.append(", write_time=").append(write_time).append("]");
		return builder.toString();
	}
}
