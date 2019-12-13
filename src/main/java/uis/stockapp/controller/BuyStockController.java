package uis.stockapp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uis.stockapp.dto.SingleStockDTO;
import uis.stockapp.dto.StockSymbolDTO;
import uis.stockapp.model.Portfolio;
import uis.stockapp.model.Stock;
import uis.stockapp.repository.PortfolioRepository;
import uis.stockapp.repository.UserRepository;
import uis.stockapp.util.UserDetails;

@Component
public class BuyStockController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField balance;

    @FXML
    private TextField currentPrice;

    @FXML
    private TextField shares;

    @FXML
    private Label symbol;

    @FXML
    private TextField total;
    
    @FXML
    private Label buyStock;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private UserRepository userRepository; 
    
    private static DecimalFormat format = new DecimalFormat("#.##");
    
    SingleStockDTO stockInfo;
    StockSymbolDTO symbolInfo;
    
    @FXML
    void initialize() {
       stockInfo=MainConsoleController.stockInfo;
       symbolInfo=MainConsoleController.symbol;
       
       currentPrice.setText("$"+format.format(stockInfo.getLatestPrice()));
       balance.setText("$"+format.format(UserDetails.currentUser.getCurrentBalance()));
       symbol.setText(symbolInfo.getName()+" ("+symbolInfo.getSymbol()+")");

    }
    public void update() {
    	Integer share=0;
    	try {
    		share=Integer.parseInt(shares.getText());
    		if(share>=0) {
    			double t=stockInfo.getLatestPrice()*share;
    			total.setText("$"+t);
    			balance.setText("$"+format.format(UserDetails.currentUser.getCurrentBalance()-t));
				buyStock.setDisable(UserDetails.currentUser.getCurrentBalance()-t<1);
    			
    		}
		} catch (Exception e) {
			
		}
    }
    public void exit() {
    	Stage stage = (Stage) currentPrice.getScene().getWindow();
        stage.close();
    }
    public void buyStock() {
    	try {
    		Integer share=Integer.parseInt(shares.getText());
    		
    		double afterPrice=UserDetails.currentUser.getCurrentBalance()-(stockInfo.getLatestPrice()*share);
    		if(afterPrice>0 && !buyStock.isDisable()) {
    		
    			Portfolio portfolio=new Portfolio();
    		
		    	portfolio.setStockName(symbolInfo.getName());
		    	portfolio.setStockSymbol(symbolInfo.getSymbol());
		    	portfolio.setExchange(symbolInfo.getExchange());
		    	portfolio.setTimePurchased(LocalDateTime.now());
		    	portfolio.setTotalShares(share);
		    	portfolio.setPricePurchasedFor(stockInfo.getLatestPrice());
		    	portfolio.setUserDetails(UserDetails.currentUser);
		    	portfolio.setActive(1);
		    	UserDetails.currentUser.setCurrentBalance(afterPrice);
		    	UserDetails.currentUser=userRepository.save(UserDetails.currentUser);
		    	portfolioRepository.save(portfolio);
		    	exit();
    		}
    	}catch (Exception e) {
			
		}
    	
    	
    }
}
