package com.company.testss13.security.validate.code.sms;

import com.company.testss13.security.CustomSecurityProperties;
import com.company.testss13.security.validate.code.ValidateCode;
import com.company.testss13.security.validate.code.impl.AbstractValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 * @author starmoon1994
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {

        String paramName = CustomSecurityProperties.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        logger.info("手机号：{}  验证码：{}  过期时间：{}",mobile,validateCode.getCode(),validateCode.getExpireTime().toString());
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
