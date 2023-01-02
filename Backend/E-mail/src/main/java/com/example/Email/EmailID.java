package com.example.Email;

class EmailID {
    private String senderID; // The sender user ID
    private String senderIndex; // The message index in the sender's list
    private String receiverID; // The receiver user ID
    private String receiverIndex; // The message index in the receiver's list

    public EmailID(String s, String si, String r, String ri){
        senderID = s;
        senderIndex = si;
        receiverID = r;
        receiverIndex = ri;
    }

    public String getSenderID() {return senderID;}
    public String getSenderIndex() {return senderIndex;}
    public String getReceiverID() {return receiverID;}
    public String getReceiverIndex() {return receiverIndex;}

    public void setSenderID(String senderID) {this.senderID = senderID;}
    public void setSenderIndex(String senderIndex) {this.senderIndex = senderIndex;}
    public void setReceiverID(String receiverID) {this.receiverID = receiverID;}
    public void setReceiverIndex(String receiverIndex) {this.receiverIndex = receiverIndex;}
}
