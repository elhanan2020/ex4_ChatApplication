package com.example.ex4.Listener;

import com.example.ex4.Bean.Label;
import com.example.ex4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@WebListener
public class SessionListener implements HttpSessionListener {

    @Autowired
    private UserRepository repository;

    private final AtomicInteger activeSessions ;

    public SessionListener() {
        this.activeSessions = new AtomicInteger(0);

    }


    public void sessionCreated(final HttpSessionEvent event) {

             activeSessions.incrementAndGet();
         }

         public void sessionDestroyed(final HttpSessionEvent event) {
             Label temp = (Label) event.getSession().getAttribute("scopedTarget.sessionBean");
             if(repository!=null)
                repository.delete(repository.findByUserName(temp.getUserName()));
             System.out.println("sessionDestroyed");
             activeSessions.decrementAndGet();
         }

}
