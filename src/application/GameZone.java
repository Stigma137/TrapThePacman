package application;


import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.LeaderBoard;
import model.Pacman;
import thread.Thread_Pacman;

public class GameZone extends Canvas{
	//Attributes
	private boolean openMouth;
	private int startAngle;
	private int angleLength;
	private boolean onPauseSelected;
	private int score;
	//Relationships
	private Game game;
	private GraphicsContext gc;
	
	/* Method that allows to create an instance of GameZone type. 
	 * 
	 */
	public GameZone() {
		super(900, 600);
		onPauseSelected = true;
		gc = super.getGraphicsContext2D();
		gc.setLineWidth(3);
		gc.setFill(Color.YELLOW);
		openMouth = false;
		startAngle = 45;
		angleLength = 270;
		super.setOnMouseClicked(new VerifyPacmanCatched());
		score = 0;
	}
	/* Method that allows to obtain an object of Game type.
	 * @return an object of Game type.
	 */
	public Game getGame() {
		return game;
	}
	/* Method that allows to display and choice dialog to choose the level path to play.
	 * 
	 */
	public void chooseLevelOrStartSavedGame() {
		ChoiceDialog<String> cd = new ChoiceDialog<String>(Game.LEVEL1, Game.LEVEL2, Game.LEVEL3, Game.SER_PATH);
		cd.showAndWait();
		if(cd.getResult() != null) {
				try {
					game = new Game(cd.getSelectedItem().toString());
					for(Pacman pac:game.getPacmans()) {
						Thread_Pacman pt = new Thread_Pacman(this, pac);
						pt.setDaemon(true);
						pt.start();
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
		}
	}
	/* Method that allows to set an object of Game type.
	 * @param an object of Game type.
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	/* Method that allows to call the methods that draw the pacman and determinate its motion.
	 * 
	 */
	public void redraw() {
		moveMouth();
		drawPacmans();
		bounceWhenCollide();
		score = 0;
		for(Pacman pac:game.getPacmans()) {
			score += pac.getRebounds();
		}
		gc.setFill(Color.BLACK);
		gc.fillText("Rebounds: " + score, 800, 595);
		gc.setFill(Color.YELLOW);
	}
	/* Method that allows to move the pacman mouth depending on the pacman start angle and length.
	 * 
	 */
	private void moveMouth() {
		gc.clearRect(0, 0,  super.getWidth(), super.getHeight());
		startAngle = openMouth? startAngle+5 : startAngle-5;
		angleLength = openMouth? angleLength-10 : angleLength+10;
		if(startAngle==0 || startAngle==45) {
			openMouth = !openMouth;
		}
	}
	/* Method that allows to draw a pacman depending on its radius, position, start angle, length and direction.
	 * 
	 */
	private void drawPacmans() {
		for(Pacman pac:game.getPacmans()) {
			switch(pac.getDir()) {
			case Pacman.UP:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+90, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+90, angleLength, ArcType.ROUND);
				break;
			case Pacman.DOWN:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+270, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+270, angleLength, ArcType.ROUND);
				break;
			case Pacman.RIGHT:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle, angleLength, ArcType.ROUND);
				break;
			case Pacman.LEFT:
				gc.fillArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+180, angleLength, ArcType.ROUND);
				gc.strokeArc(pac.getPosX(), pac.getPosY(), pac.getRadius(), pac.getRadius(), startAngle+180, angleLength, ArcType.ROUND);
				break;
			}
		}
	}
	/* Method that allows to collide pacmans with the canvas bounds and pacmans with themselves.
	 * 
	 */
	private void bounceWhenCollide() {
		for(int i = 0; i < game.getPacmans().size(); i++) {
			for(int j = i+1; j < game.getPacmans().size(); j++) {
				Rectangle r1 = new Rectangle(game.getPacmans().get(i).getPosX(), game.getPacmans().get(i).getPosY(), game.getPacmans().get(i).getRadius(), game.getPacmans().get(i).getRadius());
				if(r1.intersects(game.getPacmans().get(j).getPosX(), game.getPacmans().get(j).getPosY(), game.getPacmans().get(j).getRadius(), game.getPacmans().get(j).getRadius())) {
					game.getPacmans().get(i).setRebounds(game.getPacmans().get(i).getRebounds()+1);
					game.getPacmans().get(j).setRebounds(game.getPacmans().get(j).getRebounds()+1);
					switch(game.getPacmans().get(i).getDir()) {
						case Pacman.DOWN:
							if(!game.getPacmans().get(i).getStopped()) {
								game.getPacmans().get(i).setPosY(game.getPacmans().get(i).getPosY()-30);
							}
							game.getPacmans().get(i).setDir(Pacman.UP);
							break;
						case Pacman.UP:
							if(!game.getPacmans().get(i).getStopped()) {
								game.getPacmans().get(i).setPosY(game.getPacmans().get(i).getPosY()+30);
							}
							game.getPacmans().get(i).setDir(Pacman.DOWN);
							break;
						case Pacman.LEFT:
							if(!game.getPacmans().get(i).getStopped()) {
								game.getPacmans().get(i).setPosX(game.getPacmans().get(i).getPosX()+30);
							}
							game.getPacmans().get(i).setDir(Pacman.RIGHT);
							break;
						case Pacman.RIGHT:
							if(!game.getPacmans().get(i).getStopped()) {
								game.getPacmans().get(i).setPosX(game.getPacmans().get(i).getPosX()-30);
							}
							game.getPacmans().get(i).setDir(Pacman.LEFT);
							break;
					}
					switch(game.getPacmans().get(j).getDir()) {
						case Pacman.DOWN:
							if(!game.getPacmans().get(j).getStopped()) {
								game.getPacmans().get(j).setPosY(game.getPacmans().get(j).getStopped()? 0: game.getPacmans().get(j).getPosY()-30);
							}
							game.getPacmans().get(j).setDir(Pacman.UP);
							break;
						case Pacman.UP:
							if(!game.getPacmans().get(j).getStopped()) {
								game.getPacmans().get(j).setPosY(game.getPacmans().get(j).getPosY()+30);
							}
							game.getPacmans().get(j).setDir(Pacman.DOWN);
							break;
						case Pacman.LEFT:
							if(!game.getPacmans().get(j).getStopped()) {
								game.getPacmans().get(j).setPosX(game.getPacmans().get(j).getPosX()+30);
							}
							game.getPacmans().get(j).setDir(Pacman.RIGHT);
							break;
						case Pacman.RIGHT:
							if(!game.getPacmans().get(j).getStopped()) {
								game.getPacmans().get(j).setPosX(game.getPacmans().get(j).getPosX()-30);
							}
							game.getPacmans().get(j).setDir(Pacman.LEFT);
							break;
					}
				}
			}
		}
	}
	/* Method that allows to know if the game was paused or not. 
	 * @return a boolean that indicates if the game was paused or not.
	 */
	public boolean getOnPauseSelected() {
		return onPauseSelected;
	}
	/* Method that allows to pause the game.
	 * 
	 */
	public void pauseGame() {
		onPauseSelected = true;
	}
	/* Method that allows to continue the game.
	 * 
	 */
	public void continueGame() {
		onPauseSelected = false;
	}
	public class VerifyPacmanCatched implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			for(Pacman pac:game.getPacmans()) {
				if(!pac.getStopped()) {
					if(onPauseSelected) {
						break;
					}
					if(pac.getPosX() <= event.getX() && pac.getPosX()+pac.getRadius() >= event.getX()
							&& pac.getPosY() <= event.getY() && pac.getPosY()+pac.getRadius() >= event.getY()) {
						pac.setStopped(true);
						boolean win = true;
						for(int i = 0; i < game.getPacmans().size() && win; i++) {
							if(!game.getPacmans().get(i).getStopped()) {
								win = false;
							}
						}
						if(win) {
							Alert alert = new Alert(AlertType.INFORMATION, "Your score: " + score);
							alert.setTitle("You won!!!");
							alert.setContentText("Your score: " + score);
							alert.setHeaderText("You've caught all the pacmans, congratulations");
							alert.showAndWait();
							try {
								if(game.fitLeaderBoard(score)) {
									TextInputDialog tid = new TextInputDialog("Put your nickname here");
									tid.setTitle("Welcome to the hall of the fame!!!");
									tid.setHeaderText("Write your nickname to be in the hall of fame");
									tid.showAndWait();
									game.addLeaderBoard(new LeaderBoard(tid.getEditor().getText(), score));
								}
							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	/* Method that allows to obtain an integer that represents the rebounds number as score.
	 * @return an integer that represents the rebounds number as score.
	 */
	public int getScore() {
		return score;
	}
}