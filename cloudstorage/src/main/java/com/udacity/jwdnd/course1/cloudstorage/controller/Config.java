package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer
{
    public void addViewControllers(ViewControllerRegistry regis){
        regis.addViewController("/login").setViewName("login");
        regis.addViewController("/home").setViewName("home");
        regis.addViewController("/result").setViewName("result");
        regis.addViewController("/signup").setViewName("signup");

    }

}
