package database;


import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import message.*;
import quiz_system.*;
import user.*;
import database.*;
import com.mysql.jdbc.Connection;



public class PopulateDB {
	
	private QuizManager qm;
	private UserManager um;
	private Mailman mm;
	
	public PopulateDB(UserManager um, QuizManager qm, Mailman mm){
		this.mm=mm;
		this.qm=qm;
		this.um=um;
		try {
			populate();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	private void populate() throws NoSuchAlgorithmException, SQLException {
		
		
		
		ArrayList<String> users = new ArrayList<String>();
		users.add("Andrew");
		users.add("Adam");
		users.add("Jake");
		users.add("James");
		users.add("Rebecca");
		users.add("Sam");
		users.add("Hillary");
		users.add("Heather");
		users.add("Jack");
		users.add("Tammy");
		users.add("Dan");
		users.add("Matt");
		users.add("Alan");
		users.add("Bill");
		users.add("Katie");
		users.add("Andy");
	
		
		for(String n:users){
			um.createUser(n, "1234");
		}
		
		ArrayList<User> people = new ArrayList<User>();
		
		for(int i = 0; i<users.size();i++){
			User user = um.getExistingUser(users.get(i));
			people.add(user);
			
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	

