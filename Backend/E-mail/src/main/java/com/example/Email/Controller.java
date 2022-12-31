package com.example.Email;

import org.springframework.web.bind.annotation.*;

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
    public String signUp(@RequestParam String Fname,@RequestParam String Lname,@RequestParam String email,@RequestParam String password) 
	{
		return Boolean.toString(userManager.signup(Fname, Lname, email, password));
	}

	@GetMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password)
	{
		return Boolean.toString(userManager.login(email, password));
	}

	@GetMapping("/email")
	public String sendEmail(@RequestParam String Recipient,@RequestParam String Subject,@RequestParam String Content){
		return "true or false";
	}
	@GetMapping("/inbox")
	public String inbox(){

		return "json file";
	}
	@GetMapping("/starred")
	public String stared(){

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
	@GetMapping("/contacts")
	public String contacts(){

		return "json file";
	}
}
