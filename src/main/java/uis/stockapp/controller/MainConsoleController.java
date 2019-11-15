package uis.stockapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import uis.stockapp.apiCall.StockAPICall;
import uis.stockapp.dto.SingleStockDTO;
import uis.stockapp.dto.StockSymbolDTO;

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
    
    private List<StockSymbolDTO> symbolRecords;
    
    StockAPICall stockAPI=new StockAPICall();
    
    @FXML
    void loginUser(MouseEvent event) {
    }

    @FXML
    void initialize() {
    	
    	
    	this.symbolRecords=stockAPI.fetchSymbolFromAPI();
    	stockSymbol.setCellValueFactory(new PropertyValueFactory<>("Symbol"));
    	stockName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	stockExchange.setCellValueFactory(new PropertyValueFactory<>("Exchange"));
    	
    	symbolTable.setItems(FXCollections.observableList(symbolRecords));

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
    	StockSymbolDTO symbol = symbolTable.getSelectionModel().getSelectedItem();
    	SingleStockDTO stockInfo = stockAPI.fetchStockInfo(symbol.getSymbol());
    	
    	infoHeader.setText(symbol.getName()+"( "+symbol.getSymbol()+" )");
    	latestPrice.setText(stockInfo.getLatestPrice()+"");
    	openClose.setText(stockInfo.getOpen()+" - "+stockInfo.getClose());
    	change.setText(stockInfo.getChange()+"");
    	highLow.setText(stockInfo.getHigh()+" - "+stockInfo.getLow());
    	week52.setText(stockInfo.getWeek52High()+" - "+stockInfo.getWeek52Low());
    	primaryExchange.setText(stockInfo.getPrimaryExchange());
    	
    }
}
