package model;

import java.io.Serializable;

public class LeaderBoard implements Serializable {
	//Constant ID
	private static final long serialVersionUID = 1L;
	//Attributes
	private String nickname;
	private int score;
	
	/* Method that allows to create an instance of an object of LeaderBoard type.
	 * @param a String that indicates the nickname of the player.
	 * @param an integer that represents the player score.
	 */
	public LeaderBoard(String nickname, int score) {
			this.nickname = nickname;
			this.score = score;
		}
	/* Method that allows to obtain a String that indicates the nickname of the player.
	 * @return a String that indicates the nickname of the player.
	 */
	public String getNickname() {
		return nickname;
	}
	/* Method that allows to set a String that indicates the nickname of the player.
	 * @param a String that indicates the nickname of the player.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/* Method that allows to obtain an integer that represents the player score.
	 * @return an integer that represents the player score.
	 */
	public int getScore() {
		return score;
	}
	/* Method that allows to set an integer that represents the player score.
	 * @param an integer that represents the player score.
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
