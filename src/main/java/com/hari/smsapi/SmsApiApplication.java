package com.hari.smsapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import com.hari.config.TwiloConfig;
import com.twilio.Twilio;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.hari.")})
@EnableSwagger2
public class SmsApiApplication {
	
	@Autowired
	private TwiloConfig twiloConfig;

	@PostConstruct
	public void initTwilio()
	{
		Twilio.init(twiloConfig.getAccountid(), twiloConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(SmsApiApplication.class, args);
	}

}
