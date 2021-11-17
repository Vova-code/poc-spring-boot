package com.vova.mongodbdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableMongoHttpSession
public class HttpSessionConfig {

    @Bean
    public JacksonMongoSessionConverter jdkMongoSessionConverter(){
      return new JacksonMongoSessionConverter();
    }

    @Bean
    public DefaultCookieSerializer customCookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();

        return cookieSerializer;
    }
}
