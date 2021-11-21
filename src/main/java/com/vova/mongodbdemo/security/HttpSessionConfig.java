package com.vova.mongodbdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.time.Duration;

@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 60)
public class HttpSessionConfig {

    @Bean
    public JdkMongoSessionConverter mongoSessionConverter(){
      return new JdkMongoSessionConverter(Duration.ofMinutes(5));
    }

    @Bean
    public DefaultCookieSerializer customCookieSerializer(){
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookieMaxAge(30);
        return defaultCookieSerializer;
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {

        return HeaderHttpSessionIdResolver.xAuthToken();
    }
}
