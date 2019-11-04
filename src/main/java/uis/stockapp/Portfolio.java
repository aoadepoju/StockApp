/*
 * CSC 478 Capstone
 * HedgeFundHackers
 * Porfolio class
 */

package uis.stockapp;

import java.util.ArrayList;

public class Portfolio{
    //Declare instance variables here
    private String name = "";
    private double balance = 0.0;
    Stock s = new Stock();
    ArrayList<Stock> stockList = new ArrayList<Stock>();

    //Default constructor for the Portfolio class with attributes of a Portfolio Object
    public Portfolio(String name, double balance){
        //create attributes for Portfolio
        super();
        this.name = name;
        this.balance = balance;
    }//constructor
    public Portfolio() {

    }//no argument constructor

    //Methods to save and load portfolio file
    public void savePortfolio(){
        //TODO
    }
    public void loadPortfolio(){
        //TODO
    }

    //Methods to add and remove stock from stockList
    public void addStock(Stock purchasedStock){			//NOTE bank balance needs to be adjusted with call from driver not from this method
        stockList.add(purchasedStock);
        adjBalance();
    }
    public void removeStock(Stock soldStock){
        stockList.remove(soldStock);
        adjBalance();
    }

    //Method to adjust portfolio balance
    public void adjBalance() {							//NOTE this will be used with add/remove Stock as well as to update as stock prices rise and fall(calls to the API)
        if(!stockList.isEmpty()) {						//Check to skip balance update if portfolio is empty(new user during an API update call)
            balance = 0;
            for(Stock stock : stockList) {
                balance += (stock.getCurrPrice() * stock.getNumOfShares());
            }
        }
    }

    //Accessors and mutators for Portfolio object traits
    //NAME ATTRIBUTE
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //BALANCE ATTRIBUTE
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
