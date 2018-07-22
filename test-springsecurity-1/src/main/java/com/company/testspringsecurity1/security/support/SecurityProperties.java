package com.company.testspringsecurity1.security.support;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component("securityProperties")
@ConfigurationProperties(prefix = "project.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
