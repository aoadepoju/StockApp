/*
 * Nat Martin
 * CSC 478 The Stock App
 * GUI for Login Pane Initial Window
 */

import java.util.*;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;

public class LogIn1 extends Application {
	
	//Declare variables for all plant objects' characteristics
		
		//Create variables
		GridPane userLogIn = new GridPane();
		Button btnNew = new Button("Create account");
		Button btnExist = new Button("Log in");
		Button btnForgot = new Button("Forgot username/login");
		
		//create nodes for student info
		Label firstN = new Label("*First Name: ");
		Label lastN = new Label("*Last Name: ");
		Label userN = new Label("*Username: ");
		Label existUser = new Label("EXISTING USER");
		Label newUser = new Label("NEW USER");
		Label passW = new Label("*Net ID: ");
		Label sectSeparator = new Label("\t" );
		
		//create text fields for data input
		TextField fNameText = new TextField();
		TextField lNameText = new TextField();
		TextField userName = new TextField();
		TextField password = new TextField();
		TextField email = new TextField();
		
				
	//override start method
	@Override
	public void start(Stage logInStage) {
		
		
		userLogIn.setAlignment(Pos.CENTER);
		userLogIn.setPadding(new Insets(11.5,12.5,13.5,14.5));
		userLogIn.setHgap(5.5);
		userLogIn.setVgap(5.5);
		
		//add new user entry nodes
		userLogIn.add(newUser, 0, 0);
		userLogIn.add(firstN, 0, 1);
		userLogIn.add(lastN, 1, 1);
		userLogIn.add(fNameText,0, 2);
		userLogIn.add(lNameText,1, 2);
		
		//add separator
		userLogIn.add(sectSeparator, 0, 3);
		GridPane.setHalignment(sectSeparator, HPos.RIGHT);
		
		//add existing user entry nodes
		userLogIn.add(existUser, 0, 4);
		userLogIn.add(userN, 0, 5);
		userLogIn.add(passW, 1, 5);
		userLogIn.add(userName,0, 6);
		userLogIn.add(password,1, 6);
		
		//add button nodes
		userLogIn.add(btnNew, 0, 7);
		userLogIn.add(btnExist, 1, 7);
		GridPane.setHalignment(btnExist, HPos.RIGHT);
		userLogIn.add(btnForgot, 0, 8);
		
		//create scene and add logInPane to scene
		Scene logInScene = new Scene(userLogIn);
		
		//add title to logInStage
		logInStage.setTitle("The Stock App - Log In");
		
		//add logInScene to logInStage
		logInStage.setScene(logInScene);
		
		//display logInStage
		logInStage.show();
		
		}
	
	
	//MAIN METHOD
	public static void main(String[] args) {
		
		//Console printout indicates app has launched
		System.out.println("Launch The Stock App");
		Application.launch(args);
		
	}//END MAIN METHOD

}