package testModel;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import model.LeaderBoard;

class testLeaderBoard {
	//Relationship
	private LeaderBoard lb;
	/* Method that indicates a empty prove scenary in the test.
	*/
	public void setupScenary1() {
	}
	/* Method that indicates a prove scenary in the test with a LeaderBoard instance.
	 */
	public void setupScenary2() {
		lb = new LeaderBoard("Juanito", 15);
	}
	/* Method that tests the LeaderBoard constructor test with a random LeaderBoard instance.
	 */
	@Test
	void testLeaderBoard1() {
		setupScenary1();
		String nickname = "Pablito";
		int score = (int) (Math.random() * 10) +1;
		LeaderBoard lbTest = new LeaderBoard(nickname, score);
		assertNotNull("The object lbTest of type LeaderBoard has been created correctly.", lbTest);
	}
	/* Method that tests the LeaderBoard constructor test with a random LeaderBoard instance and previous instance from Scenary2.
	 */
	@Test
	void testLeaderBoard2() {
		setupScenary2();
		String nickname = "Juanito";
		int score = (int) (Math.random() * 15) +1;
		LeaderBoard lbTest2 = new LeaderBoard(nickname, score);
		assertNotNull("The object lbTest2 of type LeaderBoard has been created correctly.", lbTest2);
	}
}
