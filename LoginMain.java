package com.tourism;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		char option;
		LoginMain loginmain = new LoginMain();
		User user=new User();
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("---WELCOME----");
			System.out.println("---1.SIGNUP----");
			System.out.println("---2.LOGIN----");
			int choice = scanner.nextInt();
			File file = new File("User.txt");
			ArrayList<User> list = new ArrayList<User>();
            ObjectOutputStream out = null;
			ListIterator<User>iterator = null;
			switch (choice) {
			
			case 1:
				System.out.println("----------signup------------");
				System.out.println("Enter the Details");
				System.out.println("Enter the role");
				String role = scanner.next();
				System.out.println("Enter the Mobile number");
				String mobilenumber = scanner.next();
				System.out.println("Enter the username");
				String username = scanner.next();
				if (UsernameValidator.userNameValidation(username)) {
					System.out.println("valid username");
					System.out.println("Enter the Password");
					String password = scanner.next();
					if (PassWordValidator.passWordValidation(password)) {
						System.out.println("valid password");
						System.out.println("Enter the confirmpassword");
						String confirmpassword = scanner.next();
						if (password.equals(confirmpassword)) {
                            list.add(new User(username, password, confirmpassword, role, mobilenumber));
							out = new ObjectOutputStream(new FileOutputStream(file));
							out.writeObject(list);
							out.close();
							System.out.println("Access Granted!you can login and go ahead");
						} else
							System.out.println("password doesnot match");
					} else
                           System.out.println("password should be strong");
				} else
					    System.out.println("enter the username again");
				break;
			case 2:
				System.out.println("------Login---------");
				System.out.println("enter the username");
				String username1 = scanner.next();
				System.out.println("enter the password");
				String Password1 = scanner.next();
				do
				{
				ObjectInputStream in = null;
				in = new ObjectInputStream(new FileInputStream(file));
				list = (ArrayList<User>)in.readObject();
				in.close();
				iterator = list.listIterator();
				while(iterator.hasNext())
				{
					user=(User)iterator.next();
				}
                if (username1.equals(user.username) && Password1.equals(user.password))
                {
                System.out.println("Login Success");
                if(user.role.equals("admin"))
                 {
                	 AdminManager.operations();
				 }
                 else if(user.role.equals("user"))
                 {
                	 UserManager.operations();
                 }
                
                }
				break;
             }while(user!=null);
			}
			System.out.println("Do you want to continue press 'y',else press 'N'");
			option = scanner.next().charAt(0);// here charAt(0) returns the character at 0th index to variable "option"

		} while (option == 'y');
	}

}