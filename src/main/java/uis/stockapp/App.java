package uis.stockapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uis.stockapp.repository.UserRepository;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App extends Application {
 
    private ConfigurableApplicationContext context;
    private Parent rootNode;
 
   
    
    
    public void init() throws Exception {
    	context = SpringApplication.run(App.class);
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uis/stockapp/view/Main.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }
 
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        
 
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
 
    @Override
    public void stop() throws Exception {
        context.close();
    }
    public static void main(String[] args) {
		launch(args);
	}
}