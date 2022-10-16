package com.hari.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Request Data for OTP/Message")
public class RequestOTPDto {
	@ApiModelProperty(notes = "Customer Mobile Number")
	private String number;
	@ApiModelProperty(notes = "Customer Name")
	private String userName;
	@ApiModelProperty(notes = "Enter OTP if customer received already or else leave it empty")
	private String otp;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	

}
