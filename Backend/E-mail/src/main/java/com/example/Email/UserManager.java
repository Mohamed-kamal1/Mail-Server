package com.example.Email;
import com.google.gson.Gson;
import java.io.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UserManager {
    String currentUserFile;
    User currentUser;
    ArrayList<String> userEmails; // List of emails of users in a file

    // _____________________ Utility functions _____________________

    // Returns the name of the user's file if it exists, and null if it doesn't
    public String getUserFile(String email){
        int target = userEmails.indexOf(email);
        if (target == -1)
            return null;
        else
            return target + ".json";
    }

    // Gets user object from a file
    public User fileToUser(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            User user = new Gson().fromJson(reader, User.class);
            reader.close();
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Saves user to an existing file
    public void userToFile(User user, String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(new Gson().toJson(user));
            writer.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // Returns the index of an email in the current user's mail list
    public int indexAtCurrentUser(EmailID id){
        // Check if the current user is the sender or receiver then use the appropriate index
        if (id.getSenderID().equals(currentUserFile.replace(".json","")))
            return Integer.parseInt(id.getSenderIndex());
        else
            return Integer.parseInt(id.getReceiverIndex());
    }

    // __________________ End of utility functions __________________


    // Called to load user emails from the users file at the beginning of every usage
    public void readUsers(){
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.json"));
            Type usersListType = new TypeToken<ArrayList<String>>(){}.getType();
            userEmails = gson.fromJson(reader, usersListType);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Registers a new user, returns false if the user is already registered,
    // and true if the user hasn't been registered before.
    // Registration creates a new file with the ID of the user,
    // and updates the user list json file.
    public Boolean signUp(User user){
        //If the email already exists, return false
        readUsers();
        if(userEmails.contains(user.getEmail())) return false;
        Gson gson = new Gson();

        try {
            // Create new file for user and write json object
            userToFile(user, userEmails.size()+".json");
            // Add user to user email list and update its json file
            userEmails.add(user.getEmail());
            BufferedWriter writer2 = new BufferedWriter(new FileWriter("users.json"));
            writer2.write(gson.toJson(userEmails));
            writer2.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns true or false to allow user's access to their account, sets current user file and user object
    public boolean login(String email, String password){
        readUsers();
        currentUserFile = getUserFile(email);
        if (currentUserFile == null) return false;
        this.currentUser = fileToUser(currentUserFile);
        return (password.equals(currentUser.getPassword()));
    }

    // Adds sent mail to current user's sent and inbox and the inbox of all receivers
    public boolean processMail(Mail m){
        readUsers();
        // Put receivers' email array in a queue
        Queue<String> queue = new LinkedList<>();
        for(String email : m.getReceivers()){
            String filename = getUserFile(email);
            if (filename != null)
                queue.add(filename);
            else
                return false;
        }
        m.setSender(currentUser.getEmail());
        // ID and add mail to sent and inbox folders in the sender's User object
        m.ID.setSenderID(currentUserFile.replace(".json",""));
        m.ID.setSenderIndex(currentUser.nextMailID());
        m.ID.setReceiverID(this.getUserFile(m.getReceivers()[0]).replace(".json",""));
        m.ID.setReceiverIndex("");
        currentUser.sendMail(m);
        // Rewrite the sender's file
        this.userToFile(currentUser, currentUserFile);

        // Process queue of receivers
        while(!queue.isEmpty()){
            // Open file of each receiver and parse to User object
            String receiverFile = queue.remove();
            User recUser = this.fileToUser(receiverFile);
            // ID and add mail to inbox in the receiver's User object
            m.ID.setReceiverID(receiverFile.replace(".json",""));
            m.ID.setReceiverIndex(recUser.nextMailID());
            recUser.receiveMail(m);
            // Rewrite the receiver's file
            this.userToFile(recUser,receiverFile);
        }
        return true;
    }

    // Gets a mail's ID and adds/removes from starred folder
    public void starEmail(EmailID id){
        currentUser.getMailAt(indexAtCurrentUser(id)).toggleStarred();
        userToFile(currentUser,currentUserFile);
    }

    // Gets a mail's ID and adds/removes from trash folder
    public void trashEmail(EmailID id){
        currentUser.getMailAt(indexAtCurrentUser(id)).toggleTrash();
        userToFile(currentUser,currentUserFile);
    }

    // Gets a mail's ID and deletes it hiding it from all folders
    public void deleteEmail(EmailID id){
        currentUser.getMailAt(indexAtCurrentUser(id)).deleteForever();
        userToFile(currentUser,currentUserFile);
    }

    public ArrayList<Mail> getCustomFolder(int i){
        ArrayList<Mail> list = new ArrayList<>();
        Mail temp;
        for(EmailID id: currentUser.getCustomFolders().get(i)) {
            temp = currentUser.getMailAt(indexAtCurrentUser(id));
            if (temp.isTrash()) continue;
            list.add(temp);
        }
        return list;
    }

    public static void main (String[] args){
        UserManager manager = new UserManager();
        manager.readUsers();
        manager.login("dave@blabla.com","something123");
        Mail mail = new Mail(
                "testtwo",
                new String[]{"kamal@blabla.com"},
                "2023/3/1, 12:30",
                "where will this go too?",
                new ArrayList<>()
        );
        manager.processMail(mail);
        manager.login("kamal@blabla.com","password321");
        manager.starEmail(new EmailID("0","1","2","1"));
        ArrayList<Mail> mail1 = manager.currentUser.getFolder("starred", "date");
        for (Mail m : mail1) System.out.println(m.getBody());
    }
}