package uis.stockapp.dto;

public class SingleStockDTO {

	 private String open = null;
	 private String close = null;
	 private String symbol;
	 private Double change;
	 private String low = null;
	 private String high = null;
	 private Double latestPrice;
	 private float changePercent;
	 private float week52High;
	 private float week52Low;
	 private String primaryExchange;


	 // Getter Methods 

	 public String getOpen() {
	  return open;
	 }

	 public String getClose() {
	  return close;
	 }

	 public String getSymbol() {
	  return symbol;
	 }

	 public Double getChange() {
	  return change;
	 }

	 public String getLow() {
	  return low;
	 }

	 public String getHigh() {
	  return high;
	 }

	 public Double getLatestPrice() {
	  return latestPrice;
	 }

	 public float getChangePercent() {
	  return changePercent;
	 }

	 public float getWeek52High() {
	  return week52High;
	 }

	 public float getWeek52Low() {
	  return week52Low;
	 }

	 public String getPrimaryExchange() {
	  return primaryExchange;
	 }

	 // Setter Methods 

	 public void setOpen(String open) {
	  this.open = open;
	 }

	 public void setClose(String close) {
	  this.close = close;
	 }

	 public void setSymbol(String symbol) {
	  this.symbol = symbol;
	 }

	 public void setChange(Double change) {
	  this.change = change;
	 }

	 public void setLow(String low) {
	  this.low = low;
	 }

	 public void setHigh(String high) {
	  this.high = high;
	 }

	 public void setLatestPrice(Double latestPrice) {
	  this.latestPrice = latestPrice;
	 }

	 public void setChangePercent(float changePercent) {
	  this.changePercent = changePercent;
	 }

	 public void setWeek52High(float week52High) {
	  this.week52High = week52High;
	 }

	 public void setWeek52Low(float week52Low) {
	  this.week52Low = week52Low;
	 }

	 public void setPrimaryExchange(String primaryExchange) {
	  this.primaryExchange = primaryExchange;
	 }
	
}
