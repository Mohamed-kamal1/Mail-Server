package com.example.Email;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/back")
public class Controller {

	UserManager userManager;

    @GetMapping("/signUp")
    public void signUp(@RequestParam String Fname,@RequestParam String Lname,@RequestParam String email,@RequestParam String password) 
	{
		userManager = new UserManager();
		userManager.signup(Fname, Lname, email, password);
	}
}
