package com.example.Email;
import java.util.ArrayList;

class Mail{
    public EmailID ID;
    private String subject;
    private String sender;
    private String[] receivers;
    private String date; // date in "YYYY/MM/DD, TT:TT"
    private String body;
    private String[] attachments;
    private int importance;
    private Boolean inbox;
    private Boolean starred;
    private Boolean sent;
    private Boolean draft;
    private Boolean trash;

    public Mail(String s,  String[] rec, String d, String b, String[] a){
        this.subject = s;
        // this.sender = send;
        this.receivers = rec;
        this.date = d;
        this.body = b;
        this.attachments = a;
        // Email file organization is dependent of its creation.
        this.inbox = this.starred = this.sent = this.draft = this.trash = false;
        // ID is set only when sent
        this.ID = new EmailID("","","","");
    }

    public void setSender(String sender){this.sender = sender;}
    public void setSent(boolean sent){
        this.sent = sent;
    }
    public void setImportance(int importance) {this.importance=importance;}

    // Getters
    public String getSubject() {return subject;}
    public String getSender() {return sender;}
    public String[] getReceivers() {return receivers;}
    public String getDate() {return date;}
    public String getBody() {return body;}
    public String[] getAttachments() {return attachments;}
    public int getImportance() {return -importance;}
    // Checkers for files
    public Boolean isInbox() {return inbox;}
    public Boolean isStarred() {return starred;}
    public Boolean isSent() {return sent;}
    public Boolean isDraft() {return draft;}
    public Boolean isTrash() {return trash;}

    // ______________ Organization functions ______________

    // Add or remove from trash.
    public void toggleTrash() {
        if (this.trash){ // If already in trash, remove and add to inbox.
            this.inbox = true;
            this.starred = this.sent = this.draft = false;
            this.trash = false;
        }else{ // If not in trash, remove from everything and add to trash.
            this.inbox = this.starred = this.sent = this.draft = false;
            this.trash = true;
        }
    }

    // Add to draft
    public void toDraft(){
        this.inbox = this.starred = this.sent = this.trash = false;
        this.draft = true;
    }

    // Add or remove from starred
    public void toggleStarred(){
        this.starred = !this.starred;
    }

    // Add to inbox upon receiving
    public void toInbox(){
        this.draft = this.starred = this.sent = this.trash = false;
        this.inbox = true;
    }

    // Add to sent folder upon sending
    public void toSent(){
        if (this.draft) this.draft = false;
        this.sent = true;
    }

    public void deleteForever(){
        this.draft = this.trash = this.sent = this.starred = this.inbox = false;
    }

}

