package com.hari.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hari.config.TwiloConfig;
import com.hari.dto.RequestOTPDto;
import com.hari.dto.ResponseOTPDto;
import com.hari.enums.OTPStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPService {

	@Value("${app.sms}")
	private String textMessage;

	Map<String, String> map = new HashMap<String, String>();

	@Autowired
	private TwiloConfig twiloConfig;

	private ResponseOTPDto responseOTPDto;

	public ResponseOTPDto sendOTP(RequestOTPDto requestOTPDto) {
		try {
			PhoneNumber to = new PhoneNumber(requestOTPDto.getNumber());
			PhoneNumber from = new PhoneNumber(twiloConfig.getPhoneNumber());
			String otp = generateOTP();

			String otpMessage = "Dear Customer , Your OTP is " + otp
					+ ". Use this Passcode to complete your transaction. Thank You.";
			Message message = Message.creator(to, from, otpMessage).create();
			map.put(requestOTPDto.getNumber(), otp);
			responseOTPDto = new ResponseOTPDto(OTPStatus.DELIVERED, otpMessage);

		} catch (Exception e) {
			responseOTPDto = new ResponseOTPDto(OTPStatus.FAILED, e.getMessage());
		}
		return responseOTPDto;
	}

	public ResponseOTPDto sendText(RequestOTPDto requestOTPDto) {
		try {

			PhoneNumber to = new PhoneNumber(requestOTPDto.getNumber());
			PhoneNumber from = new PhoneNumber(twiloConfig.getPhoneNumber());

			Message message = Message.creator(to, from, textMessage).create();
			responseOTPDto = new ResponseOTPDto(OTPStatus.DELIVERED, textMessage);

		} catch (Exception e) {
			responseOTPDto = new ResponseOTPDto(OTPStatus.FAILED, e.getMessage());
		}
		return responseOTPDto;
	}

	public String verifyOTP(RequestOTPDto requestOTPDto) {
		if (requestOTPDto.getOtp().equalsIgnoreCase(map.get(requestOTPDto.getNumber()).trim())) {
			map.remove(requestOTPDto.getNumber());
			return "OTP Verification Success proceed to next step";
		} else
			return "OTP Verification Failure Please try again";
	}

	// 6 digit otp
	private String generateOTP() {
		return new DecimalFormat("000 000").format(new Random().nextInt(999999));
	}

}
