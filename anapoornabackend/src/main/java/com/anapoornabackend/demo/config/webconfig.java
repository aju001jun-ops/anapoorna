package com.anapoornabackend.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webconfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:///C:/Users/acer/OneDrive/Desktop/internproject/inteernship/anapoorna/anapoornabackend/uploads/");
    }

}
