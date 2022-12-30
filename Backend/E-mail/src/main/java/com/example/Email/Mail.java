package com.example.Email;
import java.util.ArrayList;

class Mail{
    private String subject;
    private String sender;
    private String[] receivers;
    private String date; // date in "YYYY/MM/DD, TT:TT"
    private String body;
    private ArrayList<String> attachments;
    private int importance;
    private Boolean inbox;
    private Boolean starred;
    private Boolean sent;
    private Boolean draft;
    private Boolean trash;

    public Mail(String s, String send, String[] rec, String d, String b, ArrayList<String> a){
        this.subject = s;
        this.sender = send;
        this.receivers = rec;
        this.date = d;
        this.body = b;
        this.attachments = a;
        // Email file organization is dependent of its creation.
        this.inbox = this.starred = this.sent = this.draft = this.trash = false;
    }

    // Getters
    public String getSubject() {return subject;}
    public String getSender() {return sender;}
    public String[] getReceivers() {return receivers;}
    public String getDate() {return date;}
    public String getBody() {return body;}
    public ArrayList<String> getAttachments() {return attachments;}
    public int getImportance() {return importance;}
    // Checkers for files
    public Boolean isInbox() {return inbox;}
    public Boolean isStarred() {return starred;}
    public Boolean isSent() {return sent;}
    public Boolean isDraft() {return draft;}
    public Boolean isTrash() {return trash;}

    // Organization functions
    public void toTrash(){
        this.inbox = this.starred = this.sent = this.draft = false;
        this.trash = true;
    }

    public void toDraft(){
        this.inbox = this.starred = this.sent = this.trash = false;
        this.draft = true;
    }

    public void toStarred(){
        this.starred = true;
    }

    public void toInbox(){
        this.inbox = true;
    }

    public void toSentAndInbox(){
        this.sent = true;
        this.inbox = true;
    }

}


