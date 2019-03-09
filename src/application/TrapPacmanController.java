package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import thread.Thread_Pacman;

public class TrapPacmanController {
	@FXML
	private Arc arc;
	@FXML
	private Circle el;
	@FXML
	public void eMove(ActionEvent event) {
		Thread_Pacman tf = new Thread_Pacman(this);
		tf.start();
	}
	public void moveFig() {
		arc.setLayoutX(arc.getLayoutX()+3);
		el.setLayoutX(el.getLayoutX()-3);
	}
	public double getXArc() {
		return arc.getLayoutX();
	}
	public double getXEl() {
		return el.getLayoutX();
	}
}
