

/*
 * Nat Martin
 * CSC 478 The Stock App
 * GUI for User Info Pane 2nd Window for new user
 * optional window for existing user to update
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

public class UserInfoPane extends Application {
	
	//Declare variables for all plant objects' characteristics
		
		//Create GridPane
		GridPane userInfo = new GridPane();
		
		//create buttons
		Button btnAddMoney = new Button("+");
		Button btnSubMoney = new Button("-");
		Button btnReset = new Button("Reset Form");
		Button btnConfirm = new Button("Confirm");
		Button btnCancel = new Button("Cancel");
		
		//create nodes for student info
		Label firstN = new Label("*First Name:");
		Label lastN = new Label("*Last Name:");
		Label emailLabel = new Label("*Email:");
		Label userN = new Label("*Username:");
		Label passW = new Label("*Password");
		Label acctBalance = new Label("Account Balance:");
		
		//create text fields for data input
		TextField fNameText = new TextField();
		TextField lNameText = new TextField();
		TextField userName = new TextField();
		TextField password = new TextField();
		TextField email = new TextField();
		TextField accountBal = new TextField();
		
				
	//override start method
	@Override
	public void start(Stage userInfoStage) {
		
		
		//userInfo.setAlignment(Pos.TOP_LEFT);
		userInfo.setPadding(new Insets(12.5,11.5,13.5,14.5));
		userInfo.setHgap(5.0);
		userInfo.setVgap(5.5);
		
		//add user info entry nodes
		userInfo.add(firstN, 0, 0);
		userInfo.add(fNameText, 1, 0);
		fNameText.autosize();
		userInfo.add(lastN, 0, 1);
		userInfo.add(lNameText, 1, 1);
		lNameText.autosize();
		userInfo.add(emailLabel, 0, 2);
		userInfo.add(email, 1, 2);
		email.autosize();
		userInfo.add(userN, 0, 3);
		userInfo.add(userName, 1, 3);
		userName.autosize();
		userInfo.add(passW, 0, 4);
		userInfo.add(password, 1, 4);
		password.autosize();
		userInfo.add(acctBalance, 0, 5);
		userInfo.add(accountBal, 1, 5);
		
		//add button nodes
		userInfo.add(btnAddMoney, 2, 5);
		GridPane.setHalignment(btnAddMoney, HPos.RIGHT);
		userInfo.add(btnSubMoney, 3, 5);
		GridPane.setHalignment(btnSubMoney, HPos.LEFT);
		userInfo.add(btnReset, 0, 6);
		userInfo.add(btnConfirm, 2, 6);
		GridPane.setHalignment(btnConfirm, HPos.RIGHT);
		userInfo.add(btnCancel, 2, 7);
		GridPane.setHalignment(btnCancel, HPos.RIGHT);
		
		//create scene and add logInPane to scene
		Scene userInfoScene = new Scene(userInfo);
		
		//add title to logInStage
		userInfoStage.setTitle("The Stock App - User Information");
		
		//add logInScene to logInStage
		userInfoStage.setScene(userInfoScene);
		
		//display logInStage
		userInfoStage.show();
		
		}
	
	
	//MAIN METHOD
	public static void main(String[] args) {
		
		//Console printout indicates app has launched
		System.out.println("Launch The Stock App");
		Application.launch(args);
		
	}//END MAIN METHOD

}