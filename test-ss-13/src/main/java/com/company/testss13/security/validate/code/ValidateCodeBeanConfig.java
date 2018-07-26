/**
 * 
 */
package com.company.testss13.security.validate.code;


import com.company.testss13.security.CustomSecurityProperties;
import com.company.testss13.security.validate.code.image.ImageCodeGenerator;
import com.company.testss13.security.validate.code.sms.DefaultSmsCodeSender;
import com.company.testss13.security.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author starmoon1994
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private CustomSecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
