package testModel;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import model.Pacman;

class testPacman {
	//Relationship
	private Pacman pac;
	/* Method that indicates a empty prove scenary in the test.
	*/
	public void setupScenary1() {
	}
	/* Method that indicates a prove scenary in the test with a Pacman instance.
	 */
	public void setupScenary2() {
		pac = new Pacman(15, 500, 200, 15, Pacman.LEFT, 0, false);
	}
	/* Method that tests the Pacman constructor test with a random Pacman instance.
	 */
	@Test
	void testPacman1() {
		setupScenary1();
		int radius = (int) (Math.random() * 10) +1;
		int posX = (int) (Math.random() * 800) +1;
		int posY = (int) (Math.random() * 600) +1;
		long waiting = (long) (Math.random() * 50) +1;
		String dir = Pacman.RIGHT;
		int rebounds = (int) (Math.random() * 100) +1;
		boolean stopped = false;
		Pacman pacTest = new Pacman(radius, posX, posY, waiting, dir, rebounds, stopped);
		assertNotNull("The object pacTest of type Pacman has been created correctly.", pacTest);
	}
	/* Method that tests the Pacman constructor test with a random Pacman instance and a previous instance from Scenary2.
	 */
	@Test
	void testPacman2() {
		setupScenary2();
		int radius = (int) (Math.random() * 10) +1;
		int posX = (int) (Math.random() * 800) +1;
		int posY = (int) (Math.random() * 600) +1;
		long waiting = (long) (Math.random() * 50) +1;
		String dir = Pacman.DOWN;
		int rebounds = (int) (Math.random() * 100) +1;
		boolean stopped = false;
		Pacman pacTest2 = new Pacman(radius, posX, posY, waiting, dir, rebounds, stopped);
		assertNotNull("The object pacTest2 of type Pacman has been created correctly.", pacTest2);
	}
}
