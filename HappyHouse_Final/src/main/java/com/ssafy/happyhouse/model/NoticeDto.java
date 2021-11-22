package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NoticeDto : 공지사항 정보")
public class NoticeDto {
	@ApiModelProperty(value = "공지사항 번호", example = "1")
	private int num;
	@ApiModelProperty(value = "공지사항 제목")
	private String title;
	@ApiModelProperty(value = "공지사항 내용")
	private String content;
	@ApiModelProperty(value = "공지사항 작성자 아이디")
	private String writer_id;
	@ApiModelProperty(value = "공지사항 작성 날짜")
	private String write_time;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
		builder.append("NoticeDto [num=").append(num).append(", title=").append(title).append(", content=")
				.append(content).append(", writer_id=").append(writer_id).append(", write_time=").append(write_time)
				.append("]");
		return builder.toString();
	}
}
