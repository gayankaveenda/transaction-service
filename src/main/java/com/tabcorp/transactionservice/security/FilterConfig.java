package com.tabcorp.transactionservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Value("${api.key}")  // ✅ Inject API key in Config class instead
    private String validApiKey;

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> loggingFilter() {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiKeyFilter(validApiKey));
        registrationBean.addUrlPatterns("/transactions/*", "/products/*");
        return registrationBean;
    }
}
