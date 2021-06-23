package com.example.ex4;

import com.example.ex4.Bean.Label;
import com.example.ex4.Filter.LogginAjax;
import com.example.ex4.Filter.LoggingInterceptor;
import com.example.ex4.Listener.SessionListener;
import com.example.ex4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * its the class that is configure the filter to ajax request
 */

@Configuration
@EnableWebMvc
public class MyConfig implements WebMvcConfigurer {


    @Resource(name = "sessionBean")
    public Label sessionObj;

   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor(sessionObj)).addPathPatterns("/showResultOfResearch/**","/addMessages", "/getUserName", "/logOut");
    }

    @Qualifier("another bean")
    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListener> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListener());
        return listenerRegBean;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}