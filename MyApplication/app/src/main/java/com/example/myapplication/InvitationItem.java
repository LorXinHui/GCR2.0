package com.example.myapplication;

public class InvitationItem {
    private String name;
    private String jobDescription;

    public InvitationItem(String name, String jobDescription) {
        this.name = name;
        this.jobDescription = jobDescription;
    }

    public String getName() {
        return name;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
