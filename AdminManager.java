package com.tourism;

import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminManager {

	public static void operations() throws FileNotFoundException, IOException, ClassNotFoundException {
		String placename;
		String locationname;
		String placespeciality;
		String landmark;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		ArrayList<Place> list = new ArrayList<Place>();
		File file = new File("Beautifulplaces.txt");// initialising file object and passing filename as argument
		ObjectOutputStream out = null;// initially writing the object as null
		ObjectInputStream in = null;
		ListIterator<Place> iterator = null;
		File file1 = new File("User.txt");
		User user = new User();
		ArrayList<User> list1 = new ArrayList<User>();
		ObjectOutputStream out1 = null;
		ObjectInputStream in1 = null;
		ListIterator<User> iterator1 = null;
		char ch = 0;
		do {
			System.out.println("***************************************************");
			System.out.println("****** HII ADMIN!!WELCOME TO SMART TOURISM MANAGEMENT******");
			System.out.println("***************************************************");
			System.out.println("1. ADD PLACES");
			System.out.println("2. DELETE PLACES");
			System.out.println("3. VIEW ALL USERS");
			System.out.println("***************************************************");
			System.out.println();
			System.out.println("Enter your choice ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Welcome Admin!!you can add differen places");
				System.out.println("Please Enter the no of places that you are goin to add");
				int no_of_places = scanner.nextInt();
				for (int i = 0; i < no_of_places; i++) {
					System.out.println("Enter the Beautiful name of the place");
					placename = scanner1.nextLine();
					System.out.println("Enter the location of that place");
					locationname = scanner1.nextLine();
					System.out.println("Enter any landmark near that place");
					landmark = scanner1.nextLine();
					System.out.println("Enter the place speciality");
					placespeciality = scanner1.nextLine();
					// adding place object to list
					list.add(new Place(placename, locationname, placespeciality, landmark));
				}
				// fileoutputstream writes the file"Beautifulplaces.txt and it will be used by
				// objectoutputstream
				out = new ObjectOutputStream(new FileOutputStream(file));
            	out.writeObject(list);// writes the object available in list into the file.
				out.close();
				break;
			case 2:
				System.out.println("-------DELETION--------");
				boolean found1 = false;
				System.out.println("Enter the PLACE to be removed");
				String place_delete = scanner1.nextLine();
				in = new ObjectInputStream(new FileInputStream(file));
				list = (ArrayList<Place>) in.readObject();
				in.close();
				iterator = list.listIterator();
				while (iterator.hasNext()) {
					Place pl = (Place) iterator.next();
					if (pl.placename.equals(place_delete)) {
						iterator.remove();
						found1 = true;
					}
				}
				if (found1) {
					out = new ObjectOutputStream(new FileOutputStream(file));
					out.writeObject(list);
					out.close();
					System.out.println(place_delete + " is removed sucessfully");
				} else {
					System.out.println(place_delete + " is not in a list");
				}
				break;
			case 3:
				System.out.println("-------viewallusers-------");
				in1 = new ObjectInputStream(new FileInputStream(file1));
				list1 = (ArrayList<User>) in1.readObject();
				in1.close();
				iterator1 = list1.listIterator();
				while (iterator1.hasNext()) {
					System.out.println(iterator1.next());
				}
				break;
			}
			System.out.println("Do u want to continue press y");
			ch = scanner.next().charAt(0);
		} while (ch == 'y');

	}
}
