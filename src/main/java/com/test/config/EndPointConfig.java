package com.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taufik.budiyanto
 * @date 09/03/2021
 * com.test.config
 */
@Configuration
public class EndPointConfig {
    @Value("${server.trustedPort:null}")
    private String trustedPort;


    @Bean
    public FilterRegistrationBean<EndpointsFilter> trustedEndpointsFilter() {
        return new FilterRegistrationBean<>(new EndpointsFilter(trustedPort, "/api/save"));
    }
}
