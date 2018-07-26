package com.company.testss13.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author starmoon1994
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
