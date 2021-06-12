package com.example.ex4;

import com.example.ex4.Bean.CounterMess;
import com.example.ex4.Bean.Label;
import com.example.ex4.Listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;


/**
 * create some beans witn various scopes using QUALIFIERS (method names)
 */
@Configuration
public class BeanConfiguration {



    @Bean
    @SessionScope
    public Label sessionBean () {
        //l.setLabel("I'm SESSION Label bean");
        return new Label();
    }
}
