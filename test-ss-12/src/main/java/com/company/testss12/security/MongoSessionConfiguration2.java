//package com.company.testss12.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.mongo.JdkMongoSessionConverter;
//import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
//import org.springframework.session.web.http.CookieHttpSessionStrategy;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
//@Configuration
//@EnableMongoHttpSession(collectionName="tbl_mis_session",maxInactiveIntervalInSeconds=43200)
//public class MongoSessionConfiguration2 {
//
//////    @Value("${project.base.url}")
//////    private String url;
//////
//////    @Value("${project.base.cookie.session}")
//////    private String cookie_name_session;
////
////    @Bean
////    public JdkMongoSessionConverter jdkMongoSessionConverter() {
////        return new JdkMongoSessionConverter();
////    }
////
////    @Bean
////    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
////        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
////        DefaultCookieSerializer cookieSerializer= new DefaultCookieSerializer();
////        cookieSerializer.setCookieName("gs");
////        cookieSerializer.setCookiePath("/");
//////        cookieSerializer.setDomainName(url);
////        cookieSerializer.setCookieMaxAge(60*60*24*30);
////        strategy.setCookieSerializer(cookieSerializer);
////        return strategy;
////    }
//
//    @Bean
//    public JdkMongoSessionConverter jdkMongoSessionConverter() {
//        return new JdkMongoSessionConverter();
//    }
//
//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("JSESSIONID");
//        serializer.setCookiePath("/");
//        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
//        return serializer;
//    }
//
//
//}
