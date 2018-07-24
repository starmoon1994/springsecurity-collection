
package com.company.testss12.security.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author starmoon1994
 */
public class CustomInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public CustomInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }


    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        onSessionInvalid(request, response);
    }

}
