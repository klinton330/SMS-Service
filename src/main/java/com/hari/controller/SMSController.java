package com.hari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.dto.RequestOTPDto;
import com.hari.dto.ResponseOTPDto;
import com.hari.service.OTPService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/sms/")
public class SMSController {

	@Autowired
	private OTPService otpService;

	@PostMapping("/sendotp")
	@ApiOperation(value = "Sends the API to Customer",response = ResponseOTPDto.class)
	private ResponseEntity<ResponseOTPDto> sendSMSOTP(@RequestBody RequestOTPDto requestOTPDto) {
		ResponseOTPDto responseOTPDto = otpService.sendOTP(requestOTPDto);
		return new ResponseEntity<ResponseOTPDto>(responseOTPDto, HttpStatus.OK);
	}

	@PostMapping("/sendtext")
	@ApiOperation(value = "Sends the textmessage to Customer",response = ResponseOTPDto.class)
	private ResponseEntity<ResponseOTPDto> sendSMSText(@RequestBody RequestOTPDto requestOTPDto) {
		ResponseOTPDto responseOTPDto = otpService.sendText(requestOTPDto);
		if (responseOTPDto.getOtpStatus().toString().equalsIgnoreCase("DELIVERED"))
			return new ResponseEntity<ResponseOTPDto>(responseOTPDto, HttpStatus.OK);
		else
			return new ResponseEntity<ResponseOTPDto>(responseOTPDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/verifyotp")
	@ApiOperation(value = "Verify the entered OTP is valid or not",response = ResponseOTPDto.class)
	private ResponseEntity<String> verifyOTP(@RequestBody RequestOTPDto requestOTPDto) {
		String msg = otpService.verifyOTP(requestOTPDto);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
