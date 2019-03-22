package view;
	
import java.io.IOException;

import application.TrapPacmanController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TrapPacman.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,900,650);
			scene.getStylesheets().add(getClass().getResource("Sheet1.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Trap the Pacman");
			TrapPacmanController pcmctrl = loader.getController();
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	            	pcmctrl.stopApplication();
	            }
	        }));
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
