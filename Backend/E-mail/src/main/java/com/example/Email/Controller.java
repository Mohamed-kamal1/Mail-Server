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
}
