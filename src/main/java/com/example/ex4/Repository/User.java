package com.example.ex4.Repository;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@UniqueElements(message = "not allowed")
    @NotEmpty(message = "Name is mandatory")

    private String userName;

    public LocalTime getTime() {
        return time;
    }

    private LocalTime time;

    public void setTime(LocalTime time) {
        this.time= time;
    }

    public User() {}

    public User(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName+ '}';
    }
}

