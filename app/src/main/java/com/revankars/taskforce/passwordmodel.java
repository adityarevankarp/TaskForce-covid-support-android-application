package com.revankars.taskforce;

public class passwordmodel {

    passwordmodel(){

    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String Password;

    public passwordmodel(String password) {
        Password = password;
    }
}
