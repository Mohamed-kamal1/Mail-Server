package com.example.Email;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;

class User {
    private String fullname;
    private String email;
    private String password;
    private ArrayList<String> contactEmails;
    private ArrayList<String> contactNames;
    private ArrayList<Mail> mail;
    private ArrayList<ArrayList<EmailID>> customFolders;

    public User(String fn, String em, String pw, ArrayList<Mail> m){
        this.fullname = fn;
        this.email = em;
        this.password = pw;
        this.mail = m;
        this.customFolders = new ArrayList<>();
        this.contactEmails = new ArrayList<>();
        this.contactNames = new ArrayList<>();
    }

    public String getFullname() {return fullname;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public ArrayList<ArrayList<EmailID>> getCustomFolders() {return customFolders;}
    public Mail getMailAt(int i) {return this.mail.get(i);}

    public ArrayList<String> getContactEmails() {return this.contactEmails;}
    public ArrayList<String> getContactNames() {return this.contactNames;}

    public void addContact(String contactEmail, String contactName){
        this.contactEmails.add(contactEmail);
        this.contactNames.add(contactName);
    }

    public void removeContact(String email){
        int i = this.contactEmails.indexOf(email);
        this.contactEmails.remove(i);
        this.contactNames.remove(i);
    }

    // Add mail to user's mail list as a received mail
    public void receiveMail(Mail m){
        m.toInbox();
        this.mail.add(m);
    }

    // Add mail to the user's mail list as a sent mail
    public void sendMail(Mail m){
        m.toInbox();
        m.toSent();
        this.mail.add(m);
    }

    public void draftMail(Mail m){
        m.toDraft();
        this.mail.add(m);
    }

    // Get the next available index in the user's mail list
    public String nextMailID(){
        return String.valueOf(this.mail.size());
    }

    // Add a new list to the custom folders list ----- CALL USERTOFILE AFTER CALL TO SAVE JSON CHANGES
    public void addCustomFolder(){
        this.customFolders.add(new ArrayList<>());
    }

    public void deleteCustomFolder(int i){
        this.customFolders.remove(i);
    }

    // add email by ID to a folder ----- CALL USERTOFILE AFTER CALL TO SAVE CHANGES
    public void toCustomFolder(EmailID id, int i){
        this.customFolders.get(i).add(id);
    }

    public void removeFromCustomFolder(EmailID id, int i){
        this.customFolders.get(i).remove(this.customFolders.indexOf(id));
    }

    // Sort a mail list by some attribute
    public static void sortMailBy(ArrayList<Mail> mail, String field){
        if (field.equals("subject"))
            mail.sort(Comparator.comparing(Mail::getSubject));
        else if (field.equals("sender"))
            mail.sort(Comparator.comparing(Mail::getSender));
        else if (field.equals("date"))
            mail.sort(Comparator.comparing(Mail::getDate));
        else if (field.equals("body"))
            mail.sort(Comparator.comparing(Mail::getBody));
        else if (field.equals("importance"))
            mail.sort(Comparator.comparing(Mail::getImportance));
    }

    // Get some folder and sort it by some attribute
    public ArrayList<Mail> getFolder(String target, String sortBy){
        ArrayList<Mail> list = new ArrayList<>();

        if (target.equals("inbox"))
        {for (Mail i: this.mail) {if(i.isInbox()) list.add(i);}}
        else if (target.equals("starred"))
        {for (Mail s: this.mail) {if(s.isStarred()) list.add(s);}}
        else if (target.equals("sent"))
        {for (Mail s: this.mail) {if(s.isSent()) list.add(s);}}
        else if (target.equals("draft"))
        {for (Mail d: this.mail) {if(d.isDraft()) list.add(d);}}
        else if (target.equals("trash"))
        {for (Mail t: this.mail) {if(t.isTrash()) list.add(t);}}
        else{ // Custom folder
            UserManager manager = new UserManager();
            manager.currentUser = this;
            ArrayList<EmailID> custom = this.customFolders.get(Integer.parseInt(target));
            for(EmailID id: custom)
                list.add(this.mail.get(manager.indexAtCurrentUser(id)));
        }
        sortMailBy(list, sortBy);
        return list;
    }

}