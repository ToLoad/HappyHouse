package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "회원 정보")
public class UserDto {
	@ApiModelProperty(value = "아이디")
	private String userid;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "회원이름")
	private String username;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "전화번호")
	private String phone_num;
	@ApiModelProperty(value = "찜한 목록의 평균 가격")
	private int avg_price;
	@ApiModelProperty(value = "찜한 목록의 평균 크기")
	private int avg_size;
	@ApiModelProperty(value = "찜한 목록의 평균 층수")
	private int avg_floor;
	@ApiModelProperty(value = "가입 날짜")
	private String join_date;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public int getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}
	public int getAvg_size() {
		return avg_size;
	}
	public void setAvg_size(int avg_size) {
		this.avg_size = avg_size;
	}
	public int getAvg_floor() {
		return avg_floor;
	}
	public void setAvg_floor(int avg_floor) {
		this.avg_floor = avg_floor;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [userid=").append(userid).append(", password=").append(password).append(", username=")
				.append(username).append(", email=").append(email).append(", phone_num=").append(phone_num)
				.append(", avg_price=").append(avg_price).append(", avg_size=").append(avg_size).append(", avg_floor=")
				.append(avg_floor).append(", join_date=").append(join_date).append("]");
		return builder.toString();
	}
}
