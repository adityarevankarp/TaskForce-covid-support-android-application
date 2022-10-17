package com.revankars.taskforce;

public class communitymodel {


    public communitymodel(String communityName, String password) {
        CommunityName = communityName;
        Password = password;
    }

    String CommunityName;

    public String getCommunityName() {
        return CommunityName;
    }

    public void setCommunityName(String communityName) {
        CommunityName = communityName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String Password;


    communitymodel(){

    }
}
