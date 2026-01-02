package com.hen.house.model;

public class DateTime {
	private String date = "01/01/2020";
	private String time = "00:00";

	public DateTime() {
		super();
	}

	public DateTime(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
