
package com.company.testss13.security.validate.code.sms;


import com.company.testss13.security.validate.code.ValidateCode;
import com.company.testss13.security.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author starmoon1994
 *
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.company.testss13.security.core.validate.code.ValidateCodeGenerator#generate(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		// TODO：需要配置化
		String code = RandomStringUtils.randomNumeric(6);

		return new ValidateCode(code,60);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

}
