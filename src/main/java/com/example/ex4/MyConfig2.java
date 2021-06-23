package com.example.ex4;

import com.example.ex4.Filter.LogginAjax;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * its the class that is configure the filter to ajax request
 */
@EnableWebMvc
@Configuration
public class  MyConfig2 implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern
        registry.addInterceptor(new LogginAjax()).addPathPatterns("/getMessages/*","/getParticipants/*","/searchByMessages/*","/searchByUser/*");
    }
}
