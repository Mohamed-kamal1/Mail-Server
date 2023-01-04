package com.example.Email;

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
		return Boolean.toString(userManager.processMail(mail));
	}
	@GetMapping("/inbox")
	public ArrayList<Mail> inbox(@RequestParam String sortby){


		return userManager.currentUser.getFolder("inbox",sortby); //{email,recipient, subject ,content, starred}

	}
	@GetMapping("/starred")
	public ArrayList<Mail> starred(@RequestParam String sortby){

		return userManager.currentUser.getFolder("starred",sortby);
	}
	@GetMapping("/sent")
	public ArrayList<Mail> sent(@RequestParam String sortby){
		System.out.println(sortby);
		ArrayList<Mail>m = userManager.currentUser.getFolder("sent",sortby);
		for(int i=0;i<m.size();i++)
		System.out.println(m.get(i).getSubject());
		return userManager.currentUser.getFolder("sent",sortby);
	}
	@GetMapping("/draft")
	public ArrayList<Mail> draft(@RequestParam String sortby){

		return userManager.currentUser.getFolder("draft",sortby);
	}
	@GetMapping("/trash")
	public ArrayList<Mail> trash(@RequestParam String sortby){

		return userManager.currentUser.getFolder("trash",sortby);
	}

	@GetMapping("/totrash")
	public String addToTrash(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		userManager.trashEmail(ids);
		return "done";
	}
	@GetMapping("/fromtrash")
	public String removeFromTrash(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		userManager.trashEmail(ids);
		return "done";
	}
	@GetMapping("/tostarred")
	public String addToStarred(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		userManager.starEmail(ids);
		return "done";
	}
	@GetMapping("/fromstarred")
	public String removeFromStarred(@RequestParam String sID,@RequestParam String sIndex,@RequestParam String rID,@RequestParam String rIndex){
		EmailID ids = new EmailID(sID,sIndex,rID,rIndex) ;
		userManager.starEmail(ids);
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
		return "done";
	}
	@GetMapping("/deletecontact")
	public String deleteContact(@RequestParam String name,@RequestParam String email)
	{
		return "done";
	}
	@GetMapping("/contacts")
	public String getContacts()
	{
		return "Json file";//{first name,last name,email}
	}

}
