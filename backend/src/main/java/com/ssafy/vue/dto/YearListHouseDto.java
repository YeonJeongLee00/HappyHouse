package com.ssafy.vue.dto;

import java.math.BigInteger;

public class YearListHouseDto {
	private int avgAmount;
	private int count;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;

	public int getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(int avgAmount) {
		this.avgAmount = avgAmount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
		return "YearListHouseDto [avgAmount=" + avgAmount + ", count=" + count + ", dealYear=" + dealYear
				+ ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + "]";
	}

}
