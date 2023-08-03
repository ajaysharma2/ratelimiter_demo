package com.technicaltransition.ratelimiter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("views/home");
        registry.addViewController("/").setViewName("views/home");
        registry.addViewController("/hello").setViewName("views/hello");
        registry.addViewController("/login").setViewName("views/login");
    }

}
