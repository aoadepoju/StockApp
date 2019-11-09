package uis.stockapp.model;
/*
 * CSC 478 Capstone
 * HedgeFundHackers
 * Stock Class
 */

import java.text.DecimalFormat;

public class Stock {
	
	// Declare instance variables here
			private String stockName = "";
			private String stockSymbol = "";
			private String stockDescript = "";		//Maybe too long to keep
			private String stockExch = "";
			private double currPrice = 0.0;
			private double purchPrice = 0.0;
			private double openPrice = 0.0;
			private double closePrice = 0.0;
			private double yearHigh = 0.0;
			private double yearLow = 0.0;
			private double dayChange = 0.0;
			private double ytdChange = 0.0;
			private double profitLoss = 0.0;
			private String stockSector = "";
			private int numOfShares = 0;
			
			DecimalFormat df = new DecimalFormat("#.00");
			
	//Default constructor for the Stock class with attributes of a Stock object
			public Stock(String stockName, String stockSymbol, String stockDescript, String stockExch, double currPrice, double openPrice,
					double closePrice, double yearHigh, double yearLow, double dayChange, double ytdChange, double profitLoss, String stockSector) {
				//create attributes for Stock
				super();
				this.stockName = stockName;
				this.stockSymbol = stockSymbol;
				this.stockDescript = stockDescript;
				this.stockExch = stockExch;
				this.currPrice = currPrice;
				this.openPrice = openPrice;
				this.closePrice = closePrice;
				this.yearHigh = yearHigh;
				this.yearLow = yearLow;
				this.dayChange = dayChange;
				this.ytdChange = ytdChange;
				this.profitLoss = profitLoss;
				this.stockSector = stockSector;
				} //constructor

	public Stock() {

	}	//No argument constructor

	//Accessors and mutators for stock object traits.
			
			//NAME ATTRIBUTE
			public String getStockName() {
				return stockName;
			}//accessor for stock name
			
			public void setStockName(String newStockName) {
				this.stockName = newStockName;
			}//mutator for stock name
			 
			
			//SYMBOL ATTRIBUTE
			public String getStockSymbol() {
				return stockSymbol;
			}//accessor for symbol
			
			public void setStockSymbol(String newStockSymbol) {
				this.stockSymbol = newStockSymbol;
			}//mutator for symbol
			
			
			//DESCRIPTION ATTRIBUTE
			public String getStockDescript() {
				return stockDescript;
			}//accessor for stock description
			
			public void setStockDescript(String newStockDescript) {
				this.stockDescript = newStockDescript;
			}//mutator for stock description
			
			
			//EXCHANGE ATTRIBUTE
			public void setStockExch(String newStockExch) {
				this.stockExch = newStockExch;
			}//mutator for exchange
			
			public String getStockExch() {
				return stockExch;
			}//accessor for exchange
			
			
			//CURRENT PRICE ATTRIBUTE
			public double getCurrPrice() {
				return Double.parseDouble(df.format(currPrice));
			}//accessor for current price
			
			public void setCurrPrice(double newCurrPrice) {
				this.currPrice = Double.parseDouble(df.format(newCurrPrice));
			}//mutator for current price
			

			//PURCHASE PRICE ATTRIBUTE
			public double getPurchPrice(){
				return Double.parseDouble(df.format(purchPrice));
			}//accessor for purchase price

			public void setPurchPrice(double purchPrice){
				this.purchPrice = Double.parseDouble(df.format(purchPrice));
			}//mutator for purchase price


			//OPEN PRICE ATTRIBUTE
			public double getOpenPrice() {
				return Double.parseDouble(df.format(openPrice));
			}//accessor for open price
			
			public void setOpenPrice(double newOpenPrice) {
				this.openPrice = Double.parseDouble(df.format(newOpenPrice));
			}//mutator for open price
			
			
			//CLOSE PRICE ATTRIBUTE
			public double getClosePrice() {
				return Double.parseDouble(df.format(closePrice));
			}//accessor for close price
			
			public void setClosePrice(double newClosePrice) {
				this.closePrice = Double.parseDouble(df.format(newClosePrice));
			}//mutator for close price
			
			
			//YEAR HIGH ATTRIBUTE
			public double getYearHigh() {
				return Double.parseDouble(df.format(yearHigh));
			}//accessor for year high
			
			public void setYearHigh(double newYearHigh) {
				this.yearHigh = Double.parseDouble(df.format(newYearHigh));
			}//mutator for year high
			
			
			//YEAR LOW ATTRIBUTE
			public double getYearLow() {
				return Double.parseDouble(df.format(yearLow));
			}//accessor for year low
			
			public void setYearLow(double newYearLow) {
				this.yearLow = Double.parseDouble(df.format(newYearLow));
			}//mutator for year low
			
			
			//DAY CHANGE ATTRIBUTE
			public double getDayChange() {
				return Double.parseDouble(df.format(dayChange));
			}//accessor for day change
			
			public void setDayChange(double newDayChange) {
				this.dayChange = Double.parseDouble(df.format(newDayChange));
			}//mutator for day change
			
			
			//YEAR-TO-DATE CHANGE ATTRIBUTE
			public double getYtdChange() {
				return Double.parseDouble(df.format(ytdChange));
			}//accessor for ytd change
			
			public void setYtdChange(double newYtdChange) {
				this.ytdChange = Double.parseDouble(df.format(newYtdChange));
			}//mutator for ytd change
			
			
			//PROFIT LOSS ATTRIBUTE
			public double getProfitLoss() {
				return Double.parseDouble(df.format(profitLoss));
			}//accessor for profit loss
			
			public void setProfitLoss(double newProfitLoss) {
				this.profitLoss = Double.parseDouble(df.format(newProfitLoss));
			}//mutator for profit loss
			
			
			//STOCK SECTOR ATTRIBUTE
			public String getStockSector() {
				return stockSector;
			}//accessor for stock sector
			
			public void setStockSector(String newStockSector) {
				this.stockSector = newStockSector;
			}//mutator for stock sector


			//NUMBER OF SHARES ATTRIBUTE
			public int getNumOfShares(){
				return numOfShares;
			}//accessor for number of shares

			public void setNumOfShares(int numOfShares){
				this.numOfShares = numOfShares;
			}//mutator for number of shares
			
			
			//TO STRING METHOD
			public String toString() {
				
				return String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s", stockName , 
						stockSymbol,
		    	    	stockDescript,
						stockExch,
						currPrice,
						purchPrice,
						openPrice,
						closePrice,
						yearHigh,
						yearLow,
						dayChange,
						ytdChange,
						profitLoss,
						stockSector,
                        numOfShares);
				
				
				
			}

}
