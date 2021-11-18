package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "회원의 찜목록")
public class WishlistDto {
	@ApiModelProperty(value = "식별 번호")
	private int num;
	@ApiModelProperty(value = "찜한 회원의 아이디")
	private String userid;
	@ApiModelProperty(value = "찜한 아파트의 번호")
	private String apt_no;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getApt_no() {
		return apt_no;
	}
	public void setApt_no(String apt_no) {
		this.apt_no = apt_no;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WishlistDto [num=").append(num).append(", userid=").append(userid).append(", apt_no=")
				.append(apt_no).append("]");
		return builder.toString();
	}
}
