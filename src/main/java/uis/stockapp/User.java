package uis.stockapp;

import java.util.*;
import java.io.*;
//import java.text.DecimalFormat;

public class User {
	
	// Declare instance variables here
				private String firstName = "";
				private String lastName = "";
				private double userBank = 0;
                private double bankBalance = 0;
                String userName = "";
                Portfolio userPortfolio = null;

				//DecimalFormat df = new DecimalFormat("#.00");
				
		//Default constructor for the Stock class with attributes of a Stock object
				public User(String first, String last, ArrayList<Bank> bank) {
					//create attributes for Stock
					super();
					this.firstName = first;
					this.lastName = last;
					this.userName = firstName + lastName;
					//this.userBank = bank;
					this.userPortfolio = new Portfolio(userName, 0);
				} //constructor
				
				//Accessors and mutators for stock object traits.
				
				//FIRST NAME ATTRIBUTE
				public String getFirstName() {
					return firstName;
				}//accessor for user first name
				
				public void setFirstName(String newFirstName) {
					this.firstName = newFirstName;
				}//mutator for user last name
				 
				//LAST NAME ATTRIBUTE
				public String getLastName() {
					return lastName;
				}//accessor for user last name
				
				public void setLastName(String newLastName) {
					this.lastName = newLastName;
				}//mutator for user last name
				
				//USERNAME ATTRIBUTE
				public String getUserName() {
					return userName;
				}//accessor for username
				
				public void setUserName(String newUserName) {
					this.userName = newUserName;
				}//mutator for username
				
				
				private static PrintWriter openWriter(String userData) {
					
					try {
						File userFile = new File(userData);
						
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(userFile)), true);
						
						return out;
					}
					catch (IOException e) {
						
						System.out.println("File error detected: ");
						System.exit(0);
					}
					
					return null;
					
				}
				
				//WRITE TO FILE METHOD
				private void writeUserData(ArrayList<String> userData, PrintWriter out) {
					
					//WORKS WITH SCANNER
					try {
						
						//printwriter variable created to accept values of element and write to file item
					
						File saveFile = new File("hh_username.txt");
						FileWriter fos = new FileWriter(saveFile, true);
						out = new PrintWriter(fos);
						
						//int items = cargoHold.size();
						
						for (String al : userData) {
							
							out.println(al);
						}

						
						out.close();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Unable to save file.");
					}
						//e.printStackTrace();
				}//WORKS WITH SCANNER
				
				
				
				
				//TO STRING METHOD
				public String toString() {
					
					return String.format(" ", firstName , 
							lastName,
			    	    	userBank);
					
					
					
				}

}
