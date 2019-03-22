package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.GameZone;
import model.LeaderBoard;


public class TrapPacmanController implements Initializable{
	//FXML Relationships
	@FXML
	private BorderPane window;
	//Relationships
	private GameZone gz;
	//Attributes
	private boolean windowOpened;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		windowOpened = true;
		MenuBar bar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load game");
		MenuItem save = new MenuItem("Save game");
		MenuItem exit = new MenuItem("Exit");
		
		file.getItems().addAll(load, save, new SeparatorMenuItem(), exit);
		FileMenuItemSelected handler = new FileMenuItemSelected();
		for (MenuItem item : file.getItems()) {
			item.addEventHandler(ActionEvent.ACTION, handler);
	}
		Menu view = new Menu("View");
		MenuItem leaderBoard = new MenuItem("Leader board");
		MenuItem instructions = new MenuItem("About the game");
		view.getItems().addAll(leaderBoard, new SeparatorMenuItem(), instructions);
		for (MenuItem item : view.getItems()) {
			item.addEventHandler(ActionEvent.ACTION, handler);
	}
		gz = new GameZone();
		window.setCenter(gz);
		bar.getMenus().addAll(file, view);
		window.setTop(bar);

		RefreshInterface ri = new RefreshInterface();
		ri.setDaemon(true);
		ri.start();
	}
	class FileMenuItemSelected implements EventHandler<ActionEvent> {

			@Override  	
			public void handle(ActionEvent event) {
				MenuItem item = (MenuItem)event.getSource();
				switch(item.getText()) {
				case "Load game":
						gz.chooseLevelOrStartSavedGame();
					break;
				case "Save game":
					try {
						gz.getGame().save();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case "Exit":
					stopApplication();
					break;
				case "Leader board":
					try {
						if(gz.getGame() != null && gz.getGame().getLeaders() != null) {
							TableView tview = new TableView();
							TableColumn nicknameColumn = new TableColumn("Nickname");
							nicknameColumn.setCellValueFactory(new PropertyValueFactory<LeaderBoard, String>("nickname"));
					        TableColumn scoreColumn = new TableColumn("Score");
					        scoreColumn.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("score"));
					        
					        ObservableList<LeaderBoard> hm = FXCollections.observableArrayList(gz.getGame().getLeaders());
					        
					        Scene scene = new Scene(new Group());
					        Label label = new Label("Hall of the fame");
					        label.setFont(new Font("Arial", 20));
					        tview.getColumns().addAll(nicknameColumn, scoreColumn);
					        tview.setItems((ObservableList) hm);
					        
					        VBox vbox = new VBox();
					        vbox.setSpacing(5);
					        vbox.setPadding(new Insets(10, 0, 0, 10));
					        vbox.getChildren().addAll(label, tview);
					        
					        ((Group) scene.getRoot()).getChildren().addAll(vbox);
					        Stage stage = new Stage();
					        stage.setScene(scene);
					        stage.show();
						}
						else {
							Alert alert = new Alert(AlertType.INFORMATION, "There are no members in the board yet");
							alert.setTitle("There are no members in the board yet");
							alert.setContentText("Play and catch all the pacmans to be in the board");
							alert.setHeaderText("No members in the board yet");
							alert.showAndWait();
						}
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					break;				
				case "About the game":
					try {
						gz.getGame().save();
					} catch (IOException e) {
						e.printStackTrace();
					}
				break;
				}
			}
			}	
	/* Method that allow to start the motion of the pacmans inside the GameZone (canvas).
	 * 
	 */
	public void movePacmans() {
		try {
			gz.redraw();
		}
		catch(NullPointerException npe) {
			gz.pauseGame();
		}
	}
	/* Method that allow to stop the motion of the pacmans (close their threads) and close the window.
	 * 
	 */
	public void stopApplication() {
		windowOpened  = false;
		Platform.exit();
	}
	/* Method that allow to indicates if the window is opened or not.
	 * @return a boolean that indicates if the window is opened or not.
	 */
	public boolean isOpened() {
		return windowOpened;
	}
	
	public class RefreshInterface extends Thread {
		@Override
		public void run() {
			while (true) {
				if(!gz.getOnPauseSelected()) {
					movePacmans();
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException ex) {
				}
			}
		}
	}
	/* Method that allow to obtain an object of Canvas type.
	 * @return an object of Canvas type.
	 */
	public Canvas getGameZone() {
		return gz;
	}
	/* Method that allows to call the method continueGame() through an event.
	 * @param an event of ActionEvent type.
	 */
	@FXML
	public void continueGame(ActionEvent event) {
		gz.continueGame();
	}
	/* Method that allows to call the method pauseGame() through an event.
	 * @param an event of ActionEvent type.
	 */
	@FXML
	public void pauseGame(ActionEvent event) {
		gz.pauseGame();
	}
	/* Method that allow to set an object of Canvas type.
	 * @param an object of Canvas type.
	 */
	public void setGameZone(GameZone gz) {
		this.gz =  gz;
	}
}
