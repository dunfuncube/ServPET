package com.servPet.csIssue.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/submission-complete").setViewName("front_end/cs_issues/submissionComplete");
        registry.addViewController("/select").setViewName("back_end/event/select_page");
        registry.addViewController("/member/event/").setViewName("front_end/event/index");
//        registry.addViewController("/listpage").setViewName("front-end/listpage");
//        registry.addViewController("/attend").setViewName("front-end/attendpage");
//        registry.addViewController("/sucessandfail").setViewName("back-end/sucessandfail");

    }
}