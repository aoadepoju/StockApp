package uis.stockapp.model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "portfolio")
public class Portfolio{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="STOCK_NAME")
	private String stockName;
	@Column(name="STOCK_SYMBOL")
	private String stockSymbol;
	@Column(name="PRICE_PURCHASED_FOR")
	private Double pricePurchasedFor;
	@ManyToOne
	@JoinColumn(name="USER_DETAILS")
	private User userDetails;
   	@Column(name = "TOTAL_SHARES")
   	private Integer totalShares;
   	@Column(name = "TIME_PURCHASED")
   	private LocalDateTime timePurchased;
   	@Column(name="EXCHANGE")
   	private String exchange;
   	@Column(name="MODIFY_ON")
   	private LocalDateTime modifyOn;
   	@Column(name="ACTIVE")
   	private Integer active;
   	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public Double getPricePurchasedFor() {
		return pricePurchasedFor;
	}
	public void setPricePurchasedFor(Double pricePurchasedFor) {
		this.pricePurchasedFor = pricePurchasedFor;
	}
	public User getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	public Integer getTotalShares() {
		return totalShares;
	}
	public void setTotalShares(Integer totalShares) {
		this.totalShares = totalShares;
	}
	public LocalDateTime getTimePurchased() {
		return timePurchased;
	}
	public void setTimePurchased(LocalDateTime timePurchased) {
		this.timePurchased = timePurchased;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public LocalDateTime getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(LocalDateTime modifyOn) {
		this.modifyOn = modifyOn;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
   	
   	
}
