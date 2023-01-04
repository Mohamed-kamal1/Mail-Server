package com.example.Email;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// import java.lang.reflect.Array;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/back")
public class Controller {

    UserManager userManager;
    User user;

    Controller()
    {
        userManager = new UserManager();
    }
    @GetMapping("/signUp")
    public String signUp(@RequestParam String name,@RequestParam String email,@RequestParam String password)
    {
        ArrayList<Mail> mail = new ArrayList<Mail>(10);
        user = new User(name,email,password,mail );
        return Boolean.toString(userManager.signUp(user));
    }

    @GetMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password)
    {
        return Boolean.toString(userManager.login(email, password));
    }

    @GetMapping("/email")
    public String sendEmail(@RequestParam String Subject,@RequestParam String[] Recipient,@RequestParam String date,@RequestParam String Content ){
        Mail mail= new Mail(Subject,Recipient,date,Content, new ArrayList<String>());
        mail.setSender(userManager.currentUser.getEmail());
        boolean flage= userManager.processMail(mail);
        mail.setSent(true);
        System.out.println(mail.isSent());
        return Boolean.toString(flage);
    }
    @GetMapping("/openfolder")
    public ArrayList<Mail> openFolder(@RequestParam String folder, @RequestParam String sortby){
        return userManager.currentUser.getFolder(folder,sortby); //{email,recipient, subject ,content, starred}

    }

    @GetMapping("/addRemove")
    public String add_remove(@RequestParam String folder, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
        EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
        if(folder.equals("trash"))
            userManager.trashEmail(ids);
        else userManager.starEmail(ids);
        return "done";
    }

    @GetMapping("/deleteforever")
    public String deleteForever(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
        EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
        userManager.deleteEmail(ids);
        return "done";
    }
    
    @GetMapping("/todraft")
    public String addToDraft(@RequestParam String Subject,@RequestParam String[] Recipient,@RequestParam String date,@RequestParam String Content ){
        Mail mail= new Mail(Subject,Recipient,date,Content, new ArrayList<String>());
        mail.setSender(userManager.currentUser.getEmail());
        Boolean.toString(userManager.processMail(mail));
        mail.toDraft();
        return "done";
    }

    @GetMapping("/addcontact")
    public String addContact(@RequestParam String name,@RequestParam String email)
    {
        userManager.currentUser.addContact(email, name);
        return "done";
    }

    @GetMapping("/deletecontact")
    public String deleteContact(@RequestParam String email)
    {
        userManager.currentUser.removeContact(email);
        return "done";
    }

    @GetMapping("/contacts")
    public String getContacts()
    {
        Gson gson = new Gson();
        ArrayList<String> emails = userManager.currentUser.getContactEmails();
        ArrayList<String> names = userManager.currentUser.getContactNames();
        return gson.toJson(emails) + gson.toJson(names);
    }

    @GetMapping("/addlable")
    public String addNewLable()
    {
        userManager.currentUser.addCustomFolder();
        userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
        return "done";
    }

    @GetMapping("/removelable")
    public String removeLable(@RequestParam int i)
    {
        userManager.currentUser.deleteCustomFolder(i);
        userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
        return "done";
    }

    @GetMapping("/addtolable")
    public String addToLable(@RequestParam int i, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
        EmailID ids = new EmailID(sID,sIndex,rID,rIndex);
        userManager.currentUser.toCustomFolder(ids,i);
        userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
        return "done";
    }

    @GetMapping("/removefromlable")
    public String removeFromLable(@RequestParam int i, @RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
        EmailID ids = new EmailID(sID,sIndex,rID,rIndex);
        userManager.currentUser.removeFromCustomFolder(ids,i);
        userManager.userToFile(userManager.currentUser, userManager.currentUserFile);
        return "done";
    }
}
