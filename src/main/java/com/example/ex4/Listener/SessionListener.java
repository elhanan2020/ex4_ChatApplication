package com.example.ex4.Listener;

import com.example.ex4.Bean.Label;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;
@EnableWebMvc
@WebListener
@Component
public class SessionListener implements HttpSessionListener {



    private final AtomicInteger activeSessions ;


    public SessionListener() {
        this.activeSessions = new AtomicInteger(0);
    }


    public void sessionCreated(final HttpSessionEvent event) {

             activeSessions.incrementAndGet();
         }

         public void sessionDestroyed(final HttpSessionEvent event) {
         System.out.println("sessionDestroyed");
        activeSessions.decrementAndGet();
         }



}
