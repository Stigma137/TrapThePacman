package testModel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Game;
import model.LeaderBoard;
import model.Pacman;

class testGame {
	//Relationship
	private Game game;
	/* Method that indicates a prove scenary in the test with some instances of a Game, Pacman, LeaderBoard and their ArrayList.
	 */
	public void setupScenary1() {
		try {
			game = new Game(Game.SER_PATH);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Pacman pac = new Pacman(15, 500, 200, 15, Pacman.LEFT, 0, false);
		Pacman pac1 = new Pacman(30, 100, 200, 25, Pacman.DOWN, 0, false);
		Pacman pac2 = new Pacman(25, 300, 200, 10, Pacman.RIGHT, 0, false);
		ArrayList<Pacman> pacmans = new ArrayList<Pacman>();
		pacmans.add(pac);
		pacmans.add(pac1);
		pacmans.add(pac2);
		
		LeaderBoard lb = new LeaderBoard("Juanito", 25);
		LeaderBoard lb1 = new LeaderBoard("Pablito", 35);
		LeaderBoard lb2 = new LeaderBoard("Danielito", 10);
		ArrayList<LeaderBoard> lbs = new ArrayList<LeaderBoard>();
		lbs.add(lb);
		lbs.add(lb1);
		lbs.add(lb2);
		
		try {
			game.getLeaders();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* Method that tests the Game method save().
	 */
	@Test
	void testSave() throws IOException {
		setupScenary1();
		File file = new File(Game.SER_PATH);
		game.save();
		assertTrue("The game was saved correctly.", file.exists());
	}
	/* Method that tests the Game method fitLeaderBoard() with one score.
	 */
	@Test
	void testFitLeaderBoard() throws ClassNotFoundException, IOException {
		setupScenary1();
		game.fitLeaderBoard(27);
		assertTrue("The player score was verified.", game.fitLeaderBoard(27) == true);
	}
	/* Method that tests the Game method addLeaderBoard() with one LeaderBoard instance.
	 */
	@Test
	void testAddLeaderBoard() throws ClassNotFoundException, IOException {
		setupScenary1();
		LeaderBoard test = new LeaderBoard("El patron", 12);
		game.addLeaderBoard(test);
		File file = new File(Game.SER_LEADER_BOARD);
		assertTrue("The player was added correctly.", file.exists());
	}
	/* Method that tests the Game method getLeaders() with the three previous instances of LeaderBoard type.
	 */
	@Test
	void testGetLeaders() throws ClassNotFoundException, IOException {
		setupScenary1();
		game.getLeaders();
		assertTrue("The three existing players have been recuperated.", game.getLeaders().size() == 3);
	}
}
