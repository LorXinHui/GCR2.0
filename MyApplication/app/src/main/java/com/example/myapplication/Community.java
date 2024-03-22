package com.example.myapplication;

public class Community {
    private String communityName;
    private String communityDesc;

    public Community(String communityName, String communityDesc){
        this.communityName = communityName;
        this.communityDesc = communityDesc;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityDesc() {
        return communityDesc;
    }

    public void setCommunityDesc(String communityDesc) {
        this.communityDesc = communityDesc;
    }
}
