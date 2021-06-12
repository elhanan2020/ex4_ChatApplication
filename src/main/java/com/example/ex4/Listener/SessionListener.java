package com.example.ex4.Listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
@Component
public class SessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions ;


    public SessionListener() {
        this.activeSessions = new AtomicInteger(0);
    }


    public void sessionCreated(final HttpSessionEvent event) {
             System.out.println("i makore");
             activeSessions.incrementAndGet();
         }

         public void sessionDestroyed(final HttpSessionEvent event) {
             System.out.println("sessionDestroyed");
        activeSessions.decrementAndGet();
         }
    public int getCounter() {return activeSessions.get();}


}
