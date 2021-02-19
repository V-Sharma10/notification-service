package com.notif.service.notif.config;

import com.notif.service.notif.services.HeaderInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class HeaderInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    HeaderInterceptorService headerInterceptorService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptorService);
    }


}
