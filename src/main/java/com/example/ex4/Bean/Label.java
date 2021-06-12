package com.example.ex4.Bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component/*(value="autowiredLabelDependency")*/
public class Label  implements Serializable {
    private boolean connected ;
    private String userName="";
    private String label = "Arbitrary Label";
    public Label() {
        connected=false;
    }
    public String toString() {
        return label;
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