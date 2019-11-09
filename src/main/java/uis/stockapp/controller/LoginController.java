package uis.stockapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import uis.stockapp.repository.UserRepository;

@Component
public class LoginController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mainButton;
    
    @FXML
    private Label button;
    
    @FXML
    private AnchorPane anchorRoot;
    
    @FXML
    private StackPane parentContainer;

    @FXML
    private Label errorMessage;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ConfigurableApplicationContext springContext ;
    
//    @Autowired
//    public LoginController(UserRepository userRepository) {
//    	this.userRepository = userRepository;
//	}
	  

    @FXML
    void initialize() {
        assert mainButton != null : "fx:id=\"login\" was not injected: check your FXML file 'LoginFrame.fxml'.";
    }
    
    public void fadeIn() throws IOException {
    	
    	FXMLLoader loader = null;
        
    	Parent root =null;
    
    	loader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/Main.fxml"));
    	
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
    public void loginUser() {
    	if(userName != null && password != null) {
    		
    		if(userRepository.findByUserNameAndPassword(userName.getText(), password.getText()) !=null ) {
    			errorMessage.setText("Login Successfully");
    		}
    		else
    			errorMessage.setText("Wrong Username Or Password");
    	}
    }
}
