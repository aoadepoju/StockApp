package uis.stockapp.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uis.stockapp.apiCall.StockAPICall;
import uis.stockapp.dto.PortfolioDTO;
import uis.stockapp.dto.SingleStockDTO;
import uis.stockapp.dto.StockSymbolDTO;
import uis.stockapp.model.Portfolio;
import uis.stockapp.repository.PortfolioRepository;
import uis.stockapp.util.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainConsoleController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorRoot;
    
    @FXML
    private TableView<StockSymbolDTO> symbolTable;
    
    @FXML
    private TableColumn<StockSymbolDTO, String> stockSymbol;

    @FXML
    private TableColumn<StockSymbolDTO, String> stockName;

    @FXML
    private TableColumn<StockSymbolDTO, String> stockExchange;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private TextField accountBalance;
    
    @FXML 
    private ChoiceBox<String> column;
    
    @FXML
    private Label change;

    @FXML
    private Label highLow;

    @FXML
    private Label infoHeader;

    @FXML
    private Label latestPrice;

    @FXML
    private Label openClose;

    @FXML
    private Label primaryExchange;

    @FXML
    private Label week52;
    
    @FXML
    private TableColumn<?, ?> pChange;

    @FXML
    private TableColumn<?, ?> pCurrentPrice;

    @FXML
    private TableColumn<?, ?> pDayChange;

    @FXML
    private TableColumn<?, ?> pExchange;

    @FXML
    private TableColumn<?, ?> pHigh;

    @FXML
    private TableColumn<?, ?> pLow;

    @FXML
    private TableColumn<?, ?> pPorfit;

    @FXML
    private TableColumn<?, ?> pPurchasePrice;

    @FXML
    private TableColumn<?, ?> pShares;

    @FXML
    private TableColumn<?, ?> pStockName;

    @FXML
    private TableColumn<?, ?> pSymbol;
    
    @FXML
    private TableView<PortfolioDTO> portfolioTable;
    
    @FXML
    private TextField mProfit;

    @FXML
    private TextField mShareValue;
    
    public static StockSymbolDTO symbol;
    
    public static SingleStockDTO stockInfo;
    
    @Autowired
    private ConfigurableApplicationContext springContext ;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    public static List<StockSymbolDTO> symbolRecords;
    
    final Integer REFRESH_TIME=15;
    
    StockAPICall stockAPI=new StockAPICall();
    
    @FXML
    void loginUser(MouseEvent event) {
    }

    @FXML
    void initialize() {
    	
    	refreshPortfolioTable();
    	
    	//Portfolio Table
    	pStockName.setCellValueFactory(new PropertyValueFactory<>("StockName"));
    	pSymbol.setCellValueFactory(new PropertyValueFactory<>("Symbol"));
    	pExchange.setCellValueFactory(new PropertyValueFactory<>("Exchange"));
    	pPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("PurchasePrice"));
    	pShares.setCellValueFactory(new PropertyValueFactory<>("Shares"));
    	pCurrentPrice.setCellValueFactory(new PropertyValueFactory<>("CurrentPrice"));
    	pDayChange.setCellValueFactory(new PropertyValueFactory<>("DayChange"));
    	pChange.setCellValueFactory(new PropertyValueFactory<>("Change"));
    	pHigh.setCellValueFactory(new PropertyValueFactory<>("ytdHigh"));
    	pLow.setCellValueFactory(new PropertyValueFactory<>("ytdLow"));
    	pPorfit.setCellValueFactory(new PropertyValueFactory<>("myProfit"));
    	stockSymbol.setCellValueFactory(new PropertyValueFactory<>("Symbol"));
    	stockName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	stockExchange.setCellValueFactory(new PropertyValueFactory<>("Exchange"));
    	//Adding Records Of Symbols
    	symbolTable.setItems(FXCollections.observableList(symbolRecords));
    	UserDetails.refreshTime=LocalDateTime.now();
    

    }
    
    public void refreshStockList() {
    	
    	
    	long minutes = UserDetails.refreshTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
    	 
    	if(minutes>=REFRESH_TIME) {
//	    	symbolRecords=stockAPI.fetchSymbolFromAPI();
//	    	stockSymbol.setCellValueFactory(new PropertyValueFactory<>("Symbol"));
//	    	stockName.setCellValueFactory(new PropertyValueFactory<>("Name"));
//	    	stockExchange.setCellValueFactory(new PropertyValueFactory<>("Exchange"));
//	    	//Adding Records Of Symbols
//	    	symbolTable.setItems(FXCollections.observableList(symbolRecords));
	    	
	    	refreshPortfolioTable();
	    	UserDetails.refreshTime=LocalDateTime.now();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Stock World");
    		alert.setHeaderText("You have to wait before refreshing stock : Time Remaining "+(REFRESH_TIME-minutes)+" Min");
    		alert.showAndWait();
    	}
		
	}

	private void refreshPortfolioTable() {
		List<PortfolioDTO> tableData=new ArrayList<>();
		String symbols="";
		List<Portfolio> portfolio=portfolioRepository.findByUserDetailsAndActive(UserDetails.currentUser,1);
		
		double totalProfit=0,shares=0;
		
		for(Portfolio data : portfolio) 
			symbols+=(symbols.equals("") ? data.getStockSymbol() : ","+data.getStockSymbol());
		
		
		if(!symbols.equals("")) {
			Map<String, SingleStockDTO> records=stockAPI.fetchMultipleStockInfo(symbols);
			
			
			for(Portfolio data : portfolio) {
				PortfolioDTO row=new PortfolioDTO();
				SingleStockDTO stock= records.get(data.getStockSymbol());
				shares+=(data.getTotalShares()*stock.getLatestPrice());
				row.setPortfolioId(data.getId());
				row.setStockName(data.getStockName());
				row.setSymbol(data.getStockSymbol());
				row.setCurrentPrice(stock.getLatestPrice());
				row.setExchange(data.getExchange());
				row.setChange(stock.getChange());
				row.setPurchasePrice(data.getPricePurchasedFor());
				row.setShares(data.getTotalShares());
				row.setDayChange(LocalDateTime.now().toString());
				row.setYtdHigh(stock.getHigh());
				row.setYtdLow(stock.getLow());
				double old=data.getPricePurchasedFor()*data.getTotalShares();
				Double newPrice=stock.getLatestPrice()*data.getTotalShares();
				row.setMyProfit(Double.parseDouble(new DecimalFormat("#0.00").format(newPrice-old)));
				totalProfit+=row.getMyProfit();
				tableData.add(row);
				
				
			}
		}
		portfolioTable.setItems(FXCollections.observableList(tableData));
		mShareValue.setText("$"+new DecimalFormat("#0.00").format(shares));
		mProfit.setText("$"+new DecimalFormat("#0.00").format(totalProfit));
		accountBalance.setText("$"+new DecimalFormat("#0.00").format(UserDetails.currentUser.getCurrentBalance()));
		
	}

	public void filterRecord() {
    	FilteredList<StockSymbolDTO> filteredData = new FilteredList<>(FXCollections.observableList(symbolRecords), p -> true);
    	searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all records.
                if (newValue == null || newValue.isEmpty()) return true;
             
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getSymbol().toLowerCase().contains(lowerCaseFilter)) 		return true; 
                else if (person.getName().toLowerCase().contains(lowerCaseFilter)) 		return true; 
                else if (person.getExchange().toLowerCase().contains(lowerCaseFilter)) 	return true; 
                
                return false; // Does not match.
            });
        });
        SortedList<StockSymbolDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(symbolTable.comparatorProperty());
        symbolTable.setItems(sortedData);
    	
    }
    public void moreInfo() {
    	symbol = symbolTable.getSelectionModel().getSelectedItem();
    	stockInfo = stockAPI.fetchStockInfo(symbol.getSymbol());
    	
    	infoHeader.setText(symbol.getName()+"( "+symbol.getSymbol()+" )");
    	latestPrice.setText(stockInfo.getLatestPrice()+"");
    	openClose.setText((stockInfo.getOpen()==null?"0":stockInfo.getOpen())+" - "+(stockInfo.getClose()==null?"0":stockInfo.getClose()));
    	change.setText(stockInfo.getChange()+"");
    	highLow.setText((stockInfo.getHigh()==null?"0":stockInfo.getHigh())+" - "+(stockInfo.getLow()==null?"0":stockInfo.getLow()));
    	week52.setText(stockInfo.getWeek52High()+" - "+stockInfo.getWeek52Low());
    	primaryExchange.setText(stockInfo.getPrimaryExchange());
    	
    }
    public void logout() throws IOException {
    	Stage mStage = (Stage) anchorRoot.getScene().getWindow();
		mStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/Login.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
	    Parent root1 = (Parent) fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setResizable(false);
    	stage.initStyle(StageStyle.UNDECORATED);
	    stage.setScene(new Scene(root1));  
	    stage.show();
    }
    public void updateInfo() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/UpdateInfo.fxml"));
    	fxmlLoader.setControllerFactory(springContext::getBean);
    	Parent root = fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setOpacity(1);
    	stage.setResizable(false);
    	stage.initStyle(StageStyle.UNDECORATED);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();
    	if(UserDetails.logout) {
    		UserDetails.logout=false;
    		logout();
    	}
    	else{
    		accountBalance.setText("$"+UserDetails.currentUser.getCurrentBalance());
    	}
    }
    public void buyStock() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/BuyStock.fxml"));
    	fxmlLoader.setControllerFactory(springContext::getBean);
    	Parent root = fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setOpacity(1);
    	stage.setResizable(false);
    	stage.initStyle(StageStyle.UNDECORATED);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();
    	refreshPortfolioTable();
    }
    public void sellStock() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/SellStock.fxml"));
    	SellStockController.selectedStock=portfolioTable.getSelectionModel().getSelectedItem();
    	fxmlLoader.setControllerFactory(springContext::getBean);
    	Parent root = fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setOpacity(1);
    	stage.setResizable(false);
    	stage.initStyle(StageStyle.UNDECORATED);
    	stage.setScene(new Scene(root));
    	stage.showAndWait();
    	
    	refreshPortfolioTable();
    }
    public void exit() {
		System.exit(1);
	}
    
    public void minimize() {
    	Stage stage = (Stage) portfolioTable.getScene().getWindow();
    	stage.setIconified(true);
    }
}
