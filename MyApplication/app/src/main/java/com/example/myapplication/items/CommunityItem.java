package com.example.myapplication.items;

public class CommunityItem {
    private String communityName;
    private String communityDesc;

    public CommunityItem(String communityName, String communityDesc){
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
