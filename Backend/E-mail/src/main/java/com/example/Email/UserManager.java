package com.example.Email;

import java.util.HashMap;

public class UserManager {
	
	HashMap<String, User> users; //A map of the form: key = email, value = User object.

	UserManager()
	{
		users = new HashMap<String, User>();
		//Read users from json file and load them to map;
	}


	public void signup(String fname, String lname, String email, String password)
	{
		User newUser = new User(fname, lname, email, password);
		users.put(newUser.email, newUser);
	}


	public boolean login(String email, String password)
	{
		User user = users.get(email);
		return user.password.equals(password);
	}

	public void saveUsersToJson()
	{

	}
}


class User
{
	String fname;
	String lname;
	String email;
	String password;

	User(String fname, String lname, String email, String password)
	{
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
	}
}
