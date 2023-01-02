package com.example.Email;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// import java.lang.reflect.Array;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/back")
public class Controller {

	UserManager userManager;

	Controller()
	{
		userManager = new UserManager();
	}
    @GetMapping("/signUp")
    public String signUp(@RequestParam String name,@RequestParam String email,@RequestParam String password)
	{
		ArrayList<Mail> mail = new ArrayList<Mail>(10);
		User user = new User(name,email,password,mail );
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
		userManager.processMail(mail);
		return "true or false";
	}
	@GetMapping("/inbox")
	public String inbox(){

		return "json file"; //{email,recipient, subject ,content, starred}
	}
	@GetMapping("/starred")
	public String starred(){

		return "json file";
	}
	@GetMapping("/sent")
	public String sent(){

		return "json file";
	}
	@GetMapping("/draft")
	public String draft(){

		return "json file";
	}
	@GetMapping("/trash")
	public String trash(){

		return "json file";
	}

	@GetMapping("/totrash")
	public String addToTrash(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
		return "done";
	}
	@GetMapping("/fromtrash")
	public String removeFromTrash(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
		return "done";
	}
	@GetMapping("/tostarred")
	public String addToStarred(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
		return "done";
	}
	@GetMapping("/fromstarred")
	public String removeFromStarred(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
		return "done";
	}
	@GetMapping("/deleteforever")
	public String deleteForever(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
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
