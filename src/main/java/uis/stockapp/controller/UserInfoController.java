package uis.stockapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uis.stockapp.model.User;
import uis.stockapp.repository.UserRepository;
import uis.stockapp.util.UserDetails;

@Component
public class UserInfoController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accountBalance;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Label errorMessage;

    @FXML
    private StackPane parentContainer;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ConfigurableApplicationContext springContext ;
   
    
    @FXML
    void initialize() {
       accountBalance.setText(UserDetails.currentUser.getCurrentBalance()+"");

    }
    
    public void updatePassword() {
    	if(UserDetails.currentUser.getPassword().equals(oldPassword.getText()) && newPassword.getText().length()>1) {
    		UserDetails.currentUser.setPassword(newPassword.getText());
    		UserDetails.currentUser = userRepository.save(UserDetails.currentUser);
    		errorMessage.setText("Password Updated");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Stock World");
    		alert.setHeaderText("Password Updated ");
    		alert.setContentText("Your Password is updated you need to login again !!!");
    		alert.showAndWait();
    		UserDetails.logout=true;
    		exit();
    		
    	}
    	else {
    		errorMessage.setText("Password didn't match");
    	}
    }
	public void updateBalance() {
    	Double balance=0.00;
    	try {
    		balance=Double.parseDouble(accountBalance.getText());
    		UserDetails.currentUser.setCurrentBalance(balance);
        	UserDetails.currentUser = userRepository.save(UserDetails.currentUser);
        	accountBalance.setText("$"+UserDetails.currentUser.getCurrentBalance());
        	errorMessage.setText("Balance Updated");
        	
    	}catch (Exception e) {
    		errorMessage.setText("Enter valid value");
		}
    	
    }
	public void exit() {
		Stage stage = (Stage) accountBalance.getScene().getWindow();
	    stage.close();
	}
}
