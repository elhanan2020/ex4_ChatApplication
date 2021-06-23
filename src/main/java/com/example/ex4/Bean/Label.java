package com.example.ex4.Bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component
public class Label  implements Serializable {
    private boolean connected ;

    public boolean isSearchByUser() {
        return searchByUser;
    }

    public void setSearchByUser(boolean searchByUser) {
        this.searchByUser = searchByUser;
    }

    private boolean searchByUser ;
    private String userName="";
    private String toSearch="";

    public String getToSearch() {
        return toSearch;
    }

    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }

    private String label = "Arbitrary Label";
    public Label() {
        searchByUser = false;
        connected = false;
    }

    @Override
    public String toString() {
        return "Label{" +
                "connected=" + connected +
                ", userName='" + userName + '\'' +
                ", label='" + label + '\'' +
                '}';
    }


    public void setLabel(String l) {
        label = l;
    }
    public void setConnected(boolean l) {
        this.connected = l;
    }
    public void setUserName(String name) {
    this.userName = name;
}
    public boolean getConnected() {
        return this.connected;
    }
    public String getUserName() {
        return this.userName;
    }

}