package com.notif.service.notif.services;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.utils.enums.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptorService implements HandlerInterceptor {

    @Value("${jwt_secret_key}")
    private String authkey;

    Logger logger = LoggerFactory.getLogger(HeaderInterceptorService.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");
        boolean flag = false;

        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        System.out.println(request.getContextPath());
        if(authorization.contentEquals(authkey)){
            flag = true;
            return  true;
        }
        throw new InvalidRequestException("Authorization token invalid",ErrorCodes.FORBIDDEN_ERROR);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
         HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
