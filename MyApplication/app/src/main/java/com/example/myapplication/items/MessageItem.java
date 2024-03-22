package com.example.myapplication.items;

import com.example.myapplication.items.InvitationItem;

public class MessageItem {

    public InvitationItem mentor;
    public String message;

    public MessageItem(InvitationItem mentor, String message){
        this.mentor = mentor;
        this.message = message;
    }

    public InvitationItem getMentor() {
        return mentor;
    }

    public void setMentor(InvitationItem mentor) {
        this.mentor = mentor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
