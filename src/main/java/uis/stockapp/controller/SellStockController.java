package uis.stockapp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uis.stockapp.dto.PortfolioDTO;
import uis.stockapp.model.Portfolio;
import uis.stockapp.repository.PortfolioRepository;
import uis.stockapp.repository.UserRepository;
import uis.stockapp.util.UserDetails;

@Component
public class SellStockController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField balance;

    @FXML
    private Label buyStock;

    @FXML
    private TextField currentPrice;

    @FXML
    private TextField pricePurchased;

    @FXML
    private TextField sellShare;

    @FXML
    private TextField shares;

    @FXML
    private Label symbol;

    @FXML
    private TextField total;

    @FXML
    private TextField profit;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public static PortfolioDTO selectedStock;
    
    @FXML
    void sellStock(MouseEvent event) {
    	try {
    		Integer share = Integer.parseInt(sellShare.getText());
    		Portfolio myShare=portfolioRepository.findById(selectedStock.getPortfolioId()).get();
    		
			if(share <= myShare.getTotalShares()) {
				
				myShare.setActive(share==myShare.getTotalShares() ? 0 : 1);
				myShare.setModifyOn(LocalDateTime.now());
				myShare.setTotalShares(myShare.getTotalShares()-share);
				
				
				//Adjust Balance
				Double sell = selectedStock.getCurrentPrice()*share;
				UserDetails.currentUser.setCurrentBalance(sell + UserDetails.currentUser.getCurrentBalance());
				UserDetails.currentUser=userRepository.save(UserDetails.currentUser);
				portfolioRepository.save(myShare);
				exit();
			}
			else
				JOptionPane.showMessageDialog(null, "Enter valid number of shares");
    		
		} catch (Exception e) {
			
		}
    	
    }

    @FXML
    public void exit() {
    	Stage stage = (Stage) currentPrice.getScene().getWindow();
        stage.close();
    }

   

    @FXML
    void update(KeyEvent event) {
    	try{
    		Integer share = Integer.parseInt(sellShare.getText());
    		if(share <= selectedStock.getShares()) {
    			buyStock.setDisable(false);
	    		Double purchased=selectedStock.getPurchasePrice() * share;
	    		Double sell = selectedStock.getCurrentPrice()*share;
		    	total.setText("$"+sell);
		    	profit.setText("$"+new DecimalFormat("#0.00").format(sell-purchased));
		    	balance.setText("$"+new DecimalFormat("#0.00").format(sell + UserDetails.currentUser.getCurrentBalance()));
    		}
    		else {
    			buyStock.setDisable(true);
    		}
    	}catch (Exception e) {
    		buyStock.setDisable(false);
		}
    }

    @FXML
    void initialize() {
        assert balance != null : "fx:id=\"balance\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert buyStock != null : "fx:id=\"buyStock\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert currentPrice != null : "fx:id=\"currentPrice\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert pricePurchased != null : "fx:id=\"pricePurchased\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert sellShare != null : "fx:id=\"sellShare\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert shares != null : "fx:id=\"shares\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert symbol != null : "fx:id=\"symbol\" was not injected: check your FXML file 'SellStock.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'SellStock.fxml'.";
        
        shares.setText(selectedStock.getShares()+"");
        pricePurchased.setText("$"+selectedStock.getPurchasePrice());
        currentPrice.setText("$"+selectedStock.getCurrentPrice());
        balance.setText("$"+UserDetails.currentUser.getCurrentBalance());
        
    }

}
