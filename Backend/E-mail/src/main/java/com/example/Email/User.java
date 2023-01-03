package com.example.Email;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;

class User {
    private String fullname;
    private String email;
    private String password;
    private ArrayList<Mail> mail;
    private ArrayList<ArrayList<EmailID>> customFolders;

    public User(String fn, String em, String pw, ArrayList<Mail> m){
        this.fullname = fn;
        this.email = em;
        this.password = pw;
        this.mail = m;
        this.customFolders = new ArrayList<>();
    }

    public String getFullname() {return fullname;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public ArrayList<ArrayList<EmailID>> getCustomFolders() {return customFolders;}
    public Mail getMailAt(int i) {return this.mail.get(i);}

    // Add mail to user's mail list as a received mail
    public void receiveMail(Mail m){
        m.toInbox();
        this.mail.add(m);
    }

    // Add mail to the user's mail list as a sent mail
    public void sendMail(Mail m){
        m.toSentAndInbox();
        this.mail.add(m);
    }

    // Get the next available index in the user's mail list
    public String nextMailID(){
        return String.valueOf(this.mail.size());
    }

    // Add a new list to the custom folders list ----- CALL USERTOFILE AFTER CALL TO SAVE JSON CHANGES
    public void addFolder(){
        this.customFolders.add(new ArrayList<>());
    }

    // add email by ID to a folder ----- CALL USERTOFILE AFTER CALL TO SAVE CHANGES
    public void toFolder(EmailID id, int i){
        this.customFolders.get(i).add(id);
    }

    // Sort a mail list by some attribute
    public static void sortMailBy(ArrayList<Mail> mail, String field){
        if (field == "subject")
            mail.sort(Comparator.comparing(Mail::getSubject));
        else if (field == "sender")
            mail.sort(Comparator.comparing(Mail::getSender));
        else if (field == "date")
            mail.sort(Comparator.comparing(Mail::getDate));
        else if (field == "body")
            mail.sort(Comparator.comparing(Mail::getBody));
        else if (field == "importance")
            mail.sort(Comparator.comparing(Mail::getImportance));
    }

    // Get some folder and sort it by some attribute
    public ArrayList<Mail> getFolder(String target, String sortBy){
        ArrayList<Mail> list = new ArrayList<>();

        if (target == "inbox")
            {for (Mail i: this.mail) {if(i.isInbox()) list.add(i);}}
        else if (target == "starred")
            {for (Mail s: this.mail) {if(s.isStarred()) list.add(s);}}
        else if (target == "sent")
            {for (Mail se: this.mail) {if(se.isSent()) list.add(se);}}
        else if (target == "draft")
            {for (Mail d: this.mail) {if(d.isDraft()) list.add(d);}}
        else if (target == "trash")
            {for (Mail t: this.mail) {if(t.isTrash()) list.add(t);}}
        sortMailBy(list, sortBy);
        return list;
    }

}
