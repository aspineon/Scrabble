package prk.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prk.network.NetworkConnection;
import prk.network.Server;

public class ServerApp extends Application {
	
	private MainWindowController mainWindowController;
	
	private Stage primaryStage;
	private NetworkConnection connection = createServer();

	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(ServerApp.class.getResource("/prk/view/mainWindow.fxml"));
		AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainWindowController = loader.getController();
		mainWindowController.setServerApp(this, primaryStage);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/prk/view/mainWindow.css");
		primaryStage.setMinHeight(560);
		primaryStage.setMinWidth(800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void init() throws Exception{
		connection.startConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		mainWindow();
	}
	
	@Override
	public void stop() throws Exception{
		connection.closeConnection();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// potrzbne? public abstract boolean isServer();
	
	private Server createServer(){
		return new Server(55555, data -> {
			Platform.runLater(() -> {
				mainWindowController.getTextarea().appendText(data.toString() + "\n");
			});
		});
	}
	
//	private Client createClient(){
//		return new Client("localhost", 55555, data -> {
//			mainWindowController.getMessages().appendText(data.toString() + "\n");
//		});
//	}

	public boolean isServer() {
		return true;
	}
//
//	public Stage getPrimaryStage() {
//		return primaryStage;
//	}
//
	public NetworkConnection getConnection() {
		return connection;
	}
}
