package com.example.ex4;

import com.example.ex4.Bean.Label;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;


/**
 * create some beans witn various scopes using QUALIFIERS (method names)
 */
@Configuration
public class BeanConfiguration {



    @Bean
    @SessionScope
    public Label sessionBean () {
        return new Label();
    }
}
