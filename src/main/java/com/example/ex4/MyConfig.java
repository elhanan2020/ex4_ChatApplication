package com.example.ex4;

import com.example.ex4.Bean.Label;
import com.example.ex4.Filter.LoggingInterceptor;
import com.example.ex4.Listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.concurrent.atomic.AtomicInteger;


/*
  this is a class for configuring SringMVC
  here we register our interceptor class and the session listener
  WebMvcConfigurer allows configuring all of the MVC:
 */
@EnableWebMvc
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // if you want to apply filter only for REST controller: change the "/**" pattern
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/addMessages","/getUserName","/logOut");
    }
    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListener> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListener());
        return listenerRegBean;
    }
    /*
    this shows you how to control the static folder where you should put your CSS/JS/images
    it will be accessible directy, for example  http://localhost:8080/static/some-file-in-static-folder.css
    So in your html file you can reference all static files as
         src="/static/yourfile"

    you may also configure this in the application.properties.
    note: the "/" at the end is required.
     */
    /*@Override
    public void addResourceHandlers( Marshaller.Listener registry) {
        registry.a(new SessionListener(new AtomicInteger());
    }*/
}