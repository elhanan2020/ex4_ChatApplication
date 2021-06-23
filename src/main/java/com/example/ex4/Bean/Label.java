package com.example.ex4.Bean;



import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * in this class i  initialize a claas label that store any field that i want to store in session,context...
 */
@Component
public class Label  implements Serializable {
    private boolean connected ;

    public boolean isSearchByUser() {
        return searchByUser;
    }
    private boolean searchByUser ;
    private String userName="";
    private String toSearch="";

    /**
     * store witch research this session want to search
     * @param searchByUser get true if i want to research by user
     */
    public void setSearchByUser(boolean searchByUser) {
        this.searchByUser = searchByUser;
    }


    /**
     * getter of the boollian how to research by user or by message
     * @return boolian
     */
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


    /**
     * initialize the variable connected in true if connect or false if disconnected
     *
     */
    public void setConnected(boolean l) {
        this.connected = l;
    }

    /**
     * initialize username
     * @param name name of user
     */
    public void setUserName(String name) {
    this.userName = name;
    }

    /**
     * return if is connect or no
     * @return this.connected
     */
    public boolean getConnected() {
        return this.connected;
    }

    /**
     * return the user name
     * @return this.userName
     */
    public String getUserName() {
        return this.userName;
    }

}