package com.example.Email;
import java.util.ArrayList;

class Mail{
    private String subject;
    private String sender;
    private String[] receivers;
    private String date; // date in "YYYY/MM/DD, TT:TT"
    private String body;
    private ArrayList<String> attachments;
    private Boolean important;

    public Mail(String s, String send, String[] rec, String d, String b, ArrayList<String> a){
        this.subject = s;
        this.sender = send;
        this.receivers = rec;
        this.date = d;
        this.body = b;
        this.attachments = a;
    }

    public String getSubject() {return subject;}
    public String getSender() {return sender;}
    public String[] getReceivers() {return receivers;}
    public String getDate() {return date;}
    public String getBody() {return body;}
    public ArrayList<String> getAttachments() {return attachments;}
    public Boolean getImportant() {return this.important;}

}


