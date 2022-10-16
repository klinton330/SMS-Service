package com.hari.dto;

import com.hari.enums.OTPStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "Response Data for OTP/Message")
public class ResponseOTPDto {
	@ApiModelProperty(notes = "Message status")
	private OTPStatus otpStatus;
	@ApiModelProperty(notes = "OTP/Message")
	private String message;

	public ResponseOTPDto(OTPStatus otpStatus, String message) {
		super();
		this.otpStatus = otpStatus;
		this.message = message;
	}

	public OTPStatus getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(OTPStatus otpStatus) {
		this.otpStatus = otpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
