

/*
 * Nat Martin
 * CSC 478 The Stock App
 * GUI for Main Interaction Pane Initial Window
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;

public class MainAppPane extends Application {
	
	//Declare variables for all plant objects' characteristics
		
		//create table to display list of stocks in portfolio and market -- tableview has built-in sorter and remove function can be added without separate method
		TableView<Object> portTable = new TableView<Object>();
		TableView<Object> marketTable = new TableView<Object>();
		
		//placeholder variables for table data (unused)
		String stockName = "stock name";
		double stockPrice = 0.0;
		String stockSect = "stock sector";
		double stockOpen = 0.0;
		double stockClose = 0.0;
		double stockDayCh = 0.0;
		String portUser = "username";
		String portEmail = "email";
		double bankBal = 0.0;
		int numberShares = 0;
	
		//Create main GridPane -- this will hold all other panes and buttons, and will be added to scene
		GridPane stockAppPane = new GridPane();
		
		//create buttons
		Button updateUserInfo = new Button("Update Info");
		Button editPortfolio = new Button("Edit portfolio");
		Button viewStockInfo = new Button("View stock details");
		Button addStock = new Button("Buy stock");
		Button logOut = new Button("Log out");
		
		//create nodes for student info
		Label userInformation = new Label("USER INFORMATION");
		Label portDisplay = new Label("PORTFOLIO");
		Label marketDisplay = new Label("EXCHANGE");
		Label iexAttribution = new Label("Data provided free of charge by IEX Trading Corp.");
		
		//create labels for user data
		TextField userNameField = new TextField(portUser);
		TextField emailField = new TextField(portEmail);
		TextField bankField = new TextField(String.valueOf(bankBal));
		
				
	//override start method
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage mainStockStage) {
		
		
		//**********ROW 0 OF SCENE*********/	
		
		//add user info text field
		stockAppPane.add(userNameField, 0, 0);
		userNameField.setEditable(false);
		stockAppPane.add(emailField, 1, 0);
		emailField.setEditable(false);
		stockAppPane.add(bankField, 2, 0);
		bankField.setEditable(false);
		
		//add update user info button and position in grid
		stockAppPane.add(updateUserInfo, 3, 0);
		GridPane.setHalignment(updateUserInfo, HPos.RIGHT);
		
		//**********ROW 1 OF SCENE************//
		
		//set portfolio table attributes for displaying in pane
		portTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				
		//Set portfolio table column header values
		TableColumn<Object, String> tablePortStockName = new TableColumn<Object, String>("Stock Name");
				
		TableColumn<Object, String> tablePortStockPrice = new TableColumn<Object, String>("Price");
				
		TableColumn<Object, String> tablePortStockShare = new TableColumn<Object, String>("Number of Shares");
				
		//add column headers to table
		portTable.getColumns().setAll(tablePortStockName, tablePortStockPrice, tablePortStockShare);
				
		//place portfolio table in scrollpane
		ScrollPane portScroll = new ScrollPane(portTable);
		
		//add portfolio scrollpane to main gridpane
		stockAppPane.add(portScroll, 0, 1);
		GridPane.setColumnSpan(portScroll, 4);
		portScroll.setFitToWidth(true); //<-- or use setColumnSpan code below
		
		
		//**********ROW 2 OF SCENE************//
		
		//add buttons for update and edit to gridpane
		stockAppPane.add(editPortfolio, 3, 2);
		GridPane.setHalignment(editPortfolio, HPos.LEFT);
		
		
		//**********ROW 3 OF SCENE*************//
		
		//add market label to gridpane
		stockAppPane.add(marketDisplay, 0, 3); //add label to second row
		GridPane.setColumnSpan(marketDisplay, 4);
		GridPane.setHalignment(marketDisplay, HPos.CENTER);
		
		
		//**********ROW 4 OF SCENE************//
		
		//set market table display attributes for displaying in pane
		marketTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//Set market table column header values
		TableColumn<Object, String> tableStockName = new TableColumn<Object, String>("Stock Name");
		
		TableColumn<Object, String> tableStockSector = new TableColumn<Object, String>("Sector");
		
		TableColumn<Object, String> tableStockOpen = new TableColumn<Object, String>("Open Price");
		
		TableColumn<Object, String> tableStockClose = new TableColumn<Object, String>("Close Price");
		
		TableColumn<Object, String> tableStockDayCh = new TableColumn<Object, String>("Day Change");
		
		//add column headers to table
		marketTable.getColumns().setAll(tableStockName, tableStockSector, tableStockOpen, tableStockClose, tableStockDayCh);
		
		//place marketscroll table in scrollpane
		ScrollPane marketScroll = new ScrollPane(marketTable);
		marketScroll.setFitToWidth(true); //<-- or use setColumnSpan code below
		
		//add market scrollpane to bottom of stockapp gridpane
		stockAppPane.add(marketScroll, 0, 4); //add scrollable table to third row
		GridPane.setColumnSpan(marketScroll, 4);
		
		
		//**********ROW 5 OF SCENE************//
		
		//add buttons to view stock or add stock from main interaction window selection
		stockAppPane.add(viewStockInfo, 0, 5);
		GridPane.setHalignment(viewStockInfo, HPos.LEFT);
		stockAppPane.add(addStock, 3, 5);
		GridPane.setHalignment(addStock, HPos.RIGHT);
				
		
		//**********ROW 6 OF SCENE************//
				
		//add attribution and logout button to last row 
		stockAppPane.add(iexAttribution, 0, 6);
		stockAppPane.add(logOut, 3, 6);
		GridPane.setHalignment(logOut, HPos.RIGHT); //add logout button to fourth row
				
		
		//******SET STOCK APP PANE DIMENS*******/
		
		//userInfo.setAlignment(Pos.TOP_LEFT);
		stockAppPane.setPadding(new Insets(12.5,11.5,13.5,14.5));
		stockAppPane.setHgap(6.5);
		stockAppPane.setVgap(6.5);
		
		//********CREATE SCENE TO ADD PANE*******//
		
		//create scene and add logInPane to scene
		Scene mainStockScene = new Scene(stockAppPane);
		
		//********ADD SCENE TO STAGE*******//
		
		//add title to logInStage
		mainStockStage.setTitle("The Stock App - User Portfolio");
		
		//add logInScene to logInStage
		mainStockStage.setScene(mainStockScene);
		
		//display logInStage
		mainStockStage.show();
		
		}
	
	
	//MAIN METHOD
	public static void main(String[] args) {
		
		//Console printout indicates app has launched
		System.out.println("Launch The Stock App");
		Application.launch(args);
		
	}//END MAIN METHOD

}