package uis.stockapp.dto;

public class PortfolioDTO {
	private Integer portfolioId;
	private String stockName;
	private String symbol;
	private String exchange;
	private Double purchasePrice;
	private Integer shares;
	private Double currentPrice;
	private String dayChange;
	private Double change;
	private String ytdHigh;
	private String ytdLow;
	private Double myProfit;

	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Integer getShares() {
		return shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getDayChange() {
		return dayChange;
	}
	public void setDayChange(String dayChange) {
		this.dayChange = dayChange;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public String getYtdHigh() {
		return ytdHigh;
	}
	public void setYtdHigh(String ytdHigh) {
		this.ytdHigh = ytdHigh;
	}
	public String getYtdLow() {
		return ytdLow;
	}
	public void setYtdLow(String ytdLow) {
		this.ytdLow = ytdLow;
	}
	public Double getMyProfit() {
		return myProfit;
	}
	public void setMyProfit(Double myProfit) {
		this.myProfit = myProfit;
	}
	
	
	
	
}
