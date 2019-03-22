package thread;

import application.GameZone;
import model.Pacman;

public class Thread_Pacman extends Thread{
	//Constant
	public final static int DELTA = 6;
	//Relationships
	private Pacman pac;
	private GameZone game;
	
	/* Constructor method that allows to create an instance of Thread_Pacman.
	 * @param a GameZone object that represents the canvas for the game.
	 * @param a Pacman object that represents one object inside the canvas.
	 */
	public Thread_Pacman(GameZone game, Pacman pac) {
	    this.game = game;
	    this.pac = pac;
	}
	/* Method from Thread that allows to run the GameZone functions and pacmans motion.
	 */
	@Override
	public void run() {
		while(!pac.getStopped()) {
			if(!game.getOnPauseSelected()) {
				moveForward();
			}
			try {
				sleep(pac.getWaiting());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/* Method that start the pacman motion inside the canvas.
	 * 
	 */
	private void moveForward() {
		switch(pac.getDir()) {
		case Pacman.UP:
			pac.setPosY(pac.getPosY() - DELTA);
			if(pac.getPosY() <= 0) {
				pac.setRebounds(pac.getRebounds()+1);
				pac.setDir(Pacman.DOWN);
			}
			break;
		case Pacman.DOWN:
			pac.setPosY(pac.getPosY()+DELTA);
			if(pac.getPosY()+pac.getRadius() >= game.getHeight()) {
				pac.setRebounds(pac.getRebounds()+1);
				pac.setDir(Pacman.UP);
			}
			break;
		case Pacman.LEFT:
			pac.setPosX(pac.getPosX()-DELTA);
			if(pac.getPosX() <= 0) {
				pac.setRebounds(pac.getRebounds()+1);
				pac.setDir(Pacman.RIGHT);
			}
			break;
		case Pacman.RIGHT:
			pac.setPosX(pac.getPosX()+DELTA);
			if(pac.getPosX()+pac.getRadius() >= game.getWidth()) {
				pac.setRebounds(pac.getRebounds()+1);
				pac.setDir(Pacman.LEFT);
			}
			break;
		}
	}
	/* Method that allow to obtain a Pacman object.
	 * @return an object of Pacman type.
	 */
	public Pacman getPacman() {
		return pac;
	}
	/* Method that allows to set an object of Pacman type to another variable. 
	 *  @param an object of Pacman type.
	 */
	public void setPacman(Pacman pac) {
		this.pac = pac;
	}
}
	
