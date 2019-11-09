package uis.stockapp.model;
/*
 * CSC 478 Capstone
 * HedgeFundHackers
 * Bank Class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

	private String user = "";
	private double purchPrice = 0.0;
	//private double openPrice = 0.0;
	private double currPrice = 0.0;
	//private double closePrice = 0.0;
	private double balance = 50.0;
	private int transactions = 0;
	private double adjust = 50.0;
	boolean account = false;
	ArrayList<Bank> bankInfo = new ArrayList<Bank>();
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	//Default constructor for the Bank class with attributes of a Stock object
	public Bank(String user, double purchPrice, double balance, int transactions) {
		//create attributes for Stock
		super();
		this.user = user;
		this.purchPrice = purchPrice;
		this.balance = balance;
		this.transactions = transactions;
		} //constructor
	
	//Accessors and mutators for bank object traits.
	
	//USER ATTRIBUTE
	public String getUser() {
		return user;
	}//accessor for user
	
	public void setUser(String newUser) {
		this.user = newUser;
	}//mutator for user
	 
	
	//PURCHASE PRICE ATTRIBUTE
	public double getPurchPrice() {
		return Double.parseDouble(df.format(purchPrice));
	}//accessor for purchase price
	
	
	//GET BALANCE ATTRIBUTE
	public double getBalance() {
		return Double.parseDouble(df.format(balance));
	}//accessor for get balance
	
	
	//INCREASE BALANCE
	public void incrBalance(double newBalance) {
		
		this.balance = Double.parseDouble(df.format(newBalance + adjust));
		
	}//mutator for balance increase in increments of $50
	
	public void decrBalance(double newBalance) {
						
		this.balance = Double.parseDouble(df.format(newBalance - adjust));
	}//mutator for balance decrease in increments of $50
	
	
	//TRANSACTIONS ATTRIBUTE
	public int getTransactions() {
		return transactions;
	}//accessor for transactions
	
	public void setTransactions(int newTransactions) {
		this.transactions = newTransactions;
	}//mutator for transactions
	
	
	//CURRENT PRICE ATTRIBUTE
	public double getCurrPrice() {
		return Double.parseDouble(df.format(getCurrPrice()));
		//return currPrice;
	}//accessor for current price
	
	
	//BUY STOCK
	public double buyStock() {
		
		return balance = Double.parseDouble(df.format(balance - purchPrice));
	}
	
	
	//SELL STOCK
	public double sellStock() {
			
		return balance = Double.parseDouble(df.format(balance + currPrice));
	}
	
	//SAVE TO FILE METHOD
	//this method saves the bank balance info to a text file
		private void writeToFile(ArrayList<Bank> bankInfo) {
			
			//begin for loop to print unique item details
			//WORKS WITH SCANNER
			try {
				
				//printwriter variable created to accept values of element and write to file item
			
				File saveFile = new File("BankBalance.txt");
				//FileOutputStream fos = new FileOutputStream(saveFile);
				FileWriter fos = new FileWriter(saveFile, true);
				PrintWriter pw = new PrintWriter(fos);
				
				//int items = cargoHold.size();
				
				for (Bank bal : bankInfo) {
					
					pw.println(bal);
				}

				
				pw.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to save file.");
				account = true;
				//e.printStackTrace();
			}//WORKS WITH SCANNER
			

			
		} //END WRITE TO FILE METHOD
		
		//ADD FROM FILE INPUT METHOD
		private void addFromFile(ArrayList<Bank> bankInfo) {
		
			
			//create file to pull in external data
			File file = new File("BankBalance.txt");
			
			//scanner reads in external file data and assigns to file item

			try {
				Scanner scan = new Scanner(file);
				

				ArrayList<String> bank = new ArrayList<String>(bankInfo.size());
				
				//create Bank container to hold scanned data
				Bank bk = new Bank(user, purchPrice, balance, transactions);
				
				//while loop continues to scan in data from file as long as there's another line
				while (bankInfo.isEmpty() && scan.hasNextLine()) {
					
					//add scanned item to Bank container
					bk = new Bank(scan.next(), scan.nextDouble(), scan.nextDouble(), scan.nextInt());
					
					//ArrayList string adds next line of data to bank
					bank.add(scan.next().toString());
									
					if (bankInfo.isEmpty()) {
						
						//scanned data is added to Bank ArrayList
						bankInfo.add(bk);

					}
					
					System.out.println(bk.toString() + ", ");
					
				}
				
				scan.close();
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("Existing file not found.");
				//e1.printStackTrace();
			}
			//END BEST WORKING CODE
			 
			
		} //END ADD FROM FILE INPUT METHOD
	
	//TO STRING METHOD
	public String toString() {
		
		return String.format(",", user , purchPrice,
				balance, transactions);
		
		
		
	}
	
}
