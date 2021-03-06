package com.example.ex4.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

/**
 * it s the class that is store data for any message in a database
 */
@Entity
public class  Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String userName;
    @NotEmpty(message = "Name is mandatory")
    private String message;
    private LocalTime time;


    public Messages() {}

    public Messages(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setTime(LocalTime time) {
        this.time= time;
    }

    public long getId() {
        return id;
    }
    public LocalTime getTime() {
        return time;
    }

    public void setUserNames(String userName) {
        this.userName = userName;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public String getUserName() {
        return userName;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName+ '}';
    }
}

