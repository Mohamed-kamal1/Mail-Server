package com.example.Email;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;

class User {
    private String fullname;
    private String email;
    private String password;
    private ArrayList<Mail> mail;

    public User(String fn, String em, String pw, ArrayList<Mail> m){
        this.fullname = fn;
        this.email = em;
        this.password = pw;
        this.mail = m;
    }

    public String getFullname() {return fullname;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}

    public User userFromJson(String jsonfile){
        return new Gson().fromJson(jsonfile, User.class);
    }

    public String jsonFromUser(User user){
        return new Gson().toJson(user);
    }

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

    public ArrayList<Mail> getFolder(String target, String sortBy){
        ArrayList<Mail> list = new ArrayList();

        if (target == "inbox")
            for (Mail i: this.mail) if(i.isInbox()) list.add(i);
        else if (target == "starred")
            for (Mail s: this.mail) if(s.isStarred()) list.add(s);
        else if (target == "sent")
            for (Mail se: this.mail) if(se.isSent()) list.add(se);
        else if (target == "draft")
            for (Mail d: this.mail) if(d.isDraft()) list.add(d);
        else if (target == "trash")
            for (Mail t: this.mail) if(t.isTrash()) list.add(t);

        sortMailBy(list, sortBy);
        return list;
    }

}
