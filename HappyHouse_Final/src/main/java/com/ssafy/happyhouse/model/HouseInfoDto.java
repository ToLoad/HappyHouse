package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "아파트정보", description = "아파트의 상세 정보를 나타낸다")
public class HouseInfoDto {

	@ApiModelProperty(value = "아파트일련번호", example = "1")
	private int aptCode;
	@ApiModelProperty(value = "아파트이름")
	private String aptName;
	@ApiModelProperty(value = "동코드")
	private String dongCode;
	@ApiModelProperty(value = "동이름")
	private String dongName;
	@ApiModelProperty(value = "시도이름")
	private String sidoName;
	@ApiModelProperty(value = "구군이름")
	private String gugunName;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "아파트이미지")
	private String img;
	@ApiModelProperty(value = "매매가격")
	private String recentPrice;
	// 추가
	@ApiModelProperty(value = "아파트 식별번호", example = "1")
	private int no;
	@ApiModelProperty(value = "건축 연도", example = "1")
	private int dealYear;
	@ApiModelProperty(value = "건축 월", example = "1")
	private int dealMonth;
	@ApiModelProperty(value = "건축 일", example = "1")
	private int dealDay;
	@ApiModelProperty(value = "아파트 크기")
	private String area;
	@ApiModelProperty(value = "층수")
	private String floor;

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseInfoDto [aptCode=").append(aptCode).append(", aptName=").append(aptName)
				.append(", dongCode=").append(dongCode).append(", dongName=").append(dongName).append(", sidoName=")
				.append(sidoName).append(", gugunName=").append(gugunName).append(", buildYear=").append(buildYear)
				.append(", jibun=").append(jibun).append(", lat=").append(lat).append(", lng=").append(lng)
				.append(", img=").append(img).append(", recentPrice=").append(recentPrice).append(", no=").append(no)
				.append(", dealYear=").append(dealYear).append(", dealMonth=").append(dealMonth).append(", dealDay=")
				.append(dealDay).append(", area=").append(area).append(", floor=").append(floor).append("]");
		return builder.toString();
	}
}
