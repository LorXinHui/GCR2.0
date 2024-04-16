package com.example.myapplication.items;

import com.example.myapplication.items.InvitationItem;

public class MessageItem {

    public InvitationItem mentor;
    public String email;
    public String contact;

    public MessageItem(InvitationItem mentor, String email, String contact) {
        this.mentor = mentor;
        this.email = email;
        this.contact = contact;
    }

    public InvitationItem getMentor() {
        return mentor;
    }

    public void setMentor(InvitationItem mentor) {
        this.mentor = mentor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
