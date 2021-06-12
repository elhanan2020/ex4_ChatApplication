package com.example.ex4.Bean;



import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Component(value="CouterBean")
public class CounterMess   {
    private final AtomicInteger messageCounter ;
    public CounterMess() {
        super();
        messageCounter = new AtomicInteger (0);;
    }
    public void incCounter() {
        messageCounter.incrementAndGet();
    }
    public int getCounter() {return messageCounter.get();}


    @ApplicationScope
    public CounterMess CouterBean() {
        CounterMess l =  new CounterMess();
        //  l.setLabel("I'm APPLICATION Label bean");
        return l;
    }
}