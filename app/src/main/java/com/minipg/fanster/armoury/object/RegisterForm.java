package com.minipg.fanster.armoury.object;

/**
 * Created by Knot on 8/17/2017.
 */

public class RegisterForm {
    String name;
    String username;
    String password;

    public RegisterForm(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
