package com.example.myapplication.items;

public class CommunityItem {
    private int communityID;
    private String communityName;
    private String communityDesc;

    public CommunityItem(int communityID, String communityName, String communityDesc){
        this.communityID = communityID;
        this.communityName = communityName;
        this.communityDesc = communityDesc;
    }

    public int getCommunityID() {
        return communityID;
    }

    public void setCommunityID(int communityID) {
        this.communityID = communityID;
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
