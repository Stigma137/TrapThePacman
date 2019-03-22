package model;

import java.io.Serializable;

public class Pacman implements Serializable{
	//Constant ID
	private static final long serialVersionUID = 1L;
	//Constants
	public final static String UP = "UP";
	public final static String DOWN = "DOWN";
	public final static String LEFT = "LEFT";
	public final static String RIGHT = "RIGHT";
	//Attributes
	private double radius;
	private int posX;
	private int posY;
	private long waiting;
	private String dir;
	private int rebounds;
	private boolean stopped;
	private int startAngle;
	private int length;
	
	/* Method that allows to create an instance of an object of Pacman type.
	 * @param an integer that represents the pacman radius.
	 * @param an integer that represents the position of the pacman in the axis X.
	 * @param an integer that represents the position of the pacman in the axis Y.
	 * @param an long type variable that indicates the velocity of the pacman.
	 * @param a String that indicates the pacman direction.
	 * @param an integer that indicates the pacman rebounds number.
	 * @param a boolean type variable that represents the pacman motion state.
	 */
	public Pacman(int radius, int posX, int posY, long waiting, String dir, int rebounds, boolean stopped) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waiting = waiting;
		this.dir = dir;
		this.rebounds = rebounds;
		this.stopped = stopped;
		startAngle = 45;
		length = 270;
	}
	/* Method that allows to obtain an integer that represents the pacman radius.
	 * @return an integer that represents the pacman radius.
	 */
	public double getRadius() {
		return radius;
	}
	/* Method that allows to set an integer that represents the pacman radius.
	 * @param an integer that represents the pacman radius.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/* Method that allows to obtain an integer that represents the position of the pacman in the axis X.
	 * @return an integer that represents the position of the pacman in the axis X.
	 */
	public int getPosX() {
		return posX;
	}
	/* Method that allows to set an integer that represents the position of the pacman in the axis X.
	 * @param an integer that represents the position of the pacman in the axis X.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/* Method that allows to obtain an integer that represents the position of the pacman in the axis Y.
	 * @return an integer that represents the position of the pacman in the axis Y.
	 */
	public int getPosY() {
		return posY;
	}
	/* Method that allows to set an integer that represents the position of the pacman in the axis Y.
	 * @param an integer that represents the position of the pacman in the axis Y.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/* Method that allows to obtain an long type variable that indicates the velocity of the pacman.
	 * @return an long type variable that indicates the velocity of the pacman.
	 */
	public long getWaiting() {
		return waiting;
	}
	/* Method that allows to set an long type variable that indicates the velocity of the pacman.
	 * @param an long type variable that indicates the velocity of the pacman.
	 */
	public void setWaiting(long waiting) {
		this.waiting = waiting;
	}
	/* Method that allows to obtain a String that indicates the pacman direction.
	 * @return a String that indicates the pacman direction.
	 */
	public String getDir() {
		return dir;
	}
	/* Method that allows to set a String that indicates the pacman direction.
	 * @param a String that indicates the pacman direction.
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	/* Method that allows to obtain an integer that indicates the pacman rebounds number.
	 * @return an integer that indicates the pacman rebounds number.
	 */
	public int getRebounds() {
		return rebounds;
	}
	/* Method that allows to set an integer that indicates the pacman rebounds number.
	 * @param an integer that indicates the pacman rebounds number.
	 */
	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}
	/* Method that allows to obtain a boolean type variable that represents the pacman motion state.
	 * @return a boolean type variable that represents the pacman motion state.
	 */
	public boolean getStopped() {
		return stopped;
	}
	/* Method that allows to set a boolean type variable that represents the pacman motion state.
	 * @param a boolean type variable that represents the pacman motion state.
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	/* Method that allows to obtain an integer that represents the start angle for the pacman mouth.
	 * @return an integer that represents the start angle for the pacman mouth
	 */
	public int getStartAngle() {
		return startAngle;
	}
	/* Method that allows to set an integer that represents the start angle for the pacman mouth.
	 * @param an integer that represents the start angle for the pacman mouth
	 */
	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}
	/* Method that allows to obtain an integer that indicates the pacman length to draw in the canvas.
	 * @return an integer that indicates the pacman length to draw in the canvas.
	 */
	public int getLength() {
		return length;
	}
	/* Method that allows to set an integer that indicates the pacman length to draw in the canvas.
	 * @param an integer that indicates the pacman length to draw in the canvas.
	 */
	public void setLength(int length) {
		this.length = length;
	}
}
