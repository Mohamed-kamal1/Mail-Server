package com.example.Email;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;

public class UserManager {
	
	HashMap<String, User> users; //A map of the form: key = email, value = User object.

	UserManager()
	{
		users = new HashMap<String, User>();
		//Read users from json file and load them to map;
		try
		{
			FileReader reader = new FileReader("Users.json");
			JSONParser parser = new JSONParser();
			JSONArray jsonUsersArr = (JSONArray) parser.parse(reader);
			for (int i=0; i< jsonUsersArr.size(); i++)
			{
				JSONObject jsonUser = (JSONObject) jsonUsersArr.get(i);
				String fname, lname, email, password;
				fname = (String)jsonUser.get("fname");
				lname = (String)jsonUser.get("lname");
				email = (String)jsonUser.get("email");
				password = (String)jsonUser.get("password");
				users.put(email, new User(fname, lname, email, password));
			}
		}
		catch (IOException e)
		{

		}
		catch (org.json.simple.parser.ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public boolean signup(String fname, String lname, String email, String password)
	{
		if (users.containsKey(email)) return false;
		User newUser = new User(fname, lname, email, password);
		users.put(newUser.email, newUser);
		saveUsersToJson();
		return true;
	}


	public boolean login(String email, String password)
	{
		if (!users.containsKey(email)) return false;
		User user = users.get(email);
		return user.password.equals(password);
	}

	public void saveUsersToJson()
	{
		try
		{
			FileWriter writer = new FileWriter("Users.json");
			JSONArray jsonUserArr = new JSONArray();
			for (Map.Entry<String, User> entry : users.entrySet())
			{
				JSONObject jsonUser = new JSONObject();
				User user = entry.getValue();
				jsonUser.put("fname", user.fname);
				jsonUser.put("lname", user.lname);
				jsonUser.put("email", user.email);
				jsonUser.put("password", user.password);
				jsonUserArr.add(jsonUser);
			}
			writer.write(jsonUserArr.toJSONString());
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("IO exception when writing Users.json");
		}
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
