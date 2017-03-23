package prk.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/prk/view/mainWindow.fxml"));
			AnchorPane pane = loader.load();
			
			MainWindowController mainWindowController = loader.getController();
			mainWindowController.setStage(primaryStage);
			primaryStage.setMinHeight(560);
			primaryStage.setMinWidth(800);
			primaryStage.setResizable(false);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/prk/view/mainWindow.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}