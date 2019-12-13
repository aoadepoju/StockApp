package uis.stockapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import uis.stockapp.model.User;
import uis.stockapp.repository.UserRepository;

@Component
public class RegisterController {

	 @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accountBalance;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Label button;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private StackPane parentContainer;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ConfigurableApplicationContext springContext ;

    @FXML
    void initialize() {


    }
    
    public void fadeIn() throws IOException {
    	
    	FXMLLoader loader = null;
       
	   	Parent root =null;
	   	loader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/Login.fxml"));
	   	
	   	 loader.setControllerFactory(springContext::getBean);
	   	 root=loader.load();
   	 
	   	if(parentContainer == null) {
	   		  parentContainer = (StackPane) button.getScene().getRoot();
	   	}
	   	
	       Scene scene = button.getScene();
	       root.translateYProperty().set(scene.getHeight());
	
	       parentContainer.getChildren().add(root);
	
	       Timeline timeline = new Timeline();
	       KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_OUT);
	       KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
	       timeline.getKeyFrames().add(kf);
	       timeline.setOnFinished(t -> {
	           parentContainer.getChildren().remove(anchorRoot);
	       });
	       timeline.play();
   }
   public void registerUser() {
	   try {
		   	if(!userName.getText().isEmpty() && !password.getText().isEmpty() && !lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !accountBalance.getText().equals("0")) {
		   		if(userRepository.findByUserName(userName.getText()) == null) {
			   		
			   		User user=new User();
	//		   		user.setUserId(0);
			   		user.setFirstName(firstName.getText());
			   		user.setLastName(lastName.getText());
			   		user.setPassword(password.getText());
			   		user.setUserName(userName.getText());
			   		user.setCurrentBalance(Double.parseDouble(accountBalance.getText()));
			   		userRepository.save(user);
			   		errorMessage.setText("User Registered");
		   		}
		   		else errorMessage.setText("Username Already Exists");
		   	}
		   	else errorMessage.setText("Fill all the feilds");
	   }catch (Exception e) {
		   errorMessage.setText("Make sure you fill all feild's correctly");
	   }
   }
   public void exit() {
		System.exit(1);
   }
}
