package com.company.testss13.security.validate.code.sms;

/**
 * @author starmoon1994
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
