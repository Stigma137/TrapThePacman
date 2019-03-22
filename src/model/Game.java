package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Game implements Serializable{
	//Constants
	public final static String SER_LEADER_BOARD = "sources/board.dot";
	public final static String SER_PATH = "sources/game.dot";
	public final static String LEVEL1 = "sources/level1.txt";
	public final static String LEVEL2 = "sources/level2.txt";
	public final static String LEVEL3 = "sources/level3.txt";
	//Constant ID
	private static final long serialVersionUID = 1L;
	//Relationships
	private ArrayList<Pacman> pacmans;
	
	/* Method that allows to create an instance of an object of Game type.
	 * @param a String that represents the level path.
	 */
	public Game(String archivePath) throws ClassNotFoundException, IOException {
		File file = new File(archivePath); 
		if(!archivePath.equals(SER_PATH)) {
			startNewGameFromScratch(file);
		}
		else {
			if(file.exists()) {
				load();
			}
			else {
				pacmans = new ArrayList<>();
			}
		}
	}
	/* Method that allows to save the game state in a determined moment when the player specifies this option.
	 * 
	 */
	public void save() throws IOException {
		File file = new File(SER_PATH);
		FileOutputStream out = new FileOutputStream(file);
		ObjectOutputStream outs = new ObjectOutputStream(out);
		outs.writeObject(pacmans);
		outs.close();
		out.close();
	}
	/* Method that allows to obtain an ArrayList of Pacman type.
	 * @return an ArrayList of Pacman type.
	 */
	public ArrayList<Pacman> getPacmans() {
		return pacmans;
	}
	/* Method that allows to load a previous game state in a determined moment when the player specifies this option.
	 * 
	 */
	private void load() throws IOException, ClassNotFoundException {
		File file = new File(SER_PATH);
		FileInputStream in = new FileInputStream(file);
		ObjectInputStream ins = new ObjectInputStream(in);
		pacmans = (ArrayList)ins.readObject();
		ins.close();
		in.close();
	}
	/* Method that allows to start a new game from scratch (zero) through the file level reading.
	 * @param a String that represents the level path to start for.
	 */
	private void startNewGameFromScratch(File level) throws IOException {
		pacmans = new ArrayList<>();
		FileReader fr = new FileReader(level);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null) {
			if(line.trim().length() > 1 && !line.startsWith("#")) {
				String[] input = line.split("\t");
				int radius = Integer.parseInt(input[0]);
				int posX = Integer.parseInt(input[1]);
				int posY = Integer.parseInt(input[2]);
				int waiting = Integer.parseInt(input[3]);
				String dir = input[4];
				int rebounds = Integer.parseInt(input[5]);
				boolean stopped = Boolean.parseBoolean(input[6]);
				Pacman pac = new Pacman(radius, posX, posY, waiting, dir, rebounds, stopped);
				pacmans.add(pac);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
	/* Method that helps to determinate if a player score is suitable to be added in the leader board.
	 * @param an integer that represents the player score.
	 * @return a boolean type variable that represents if a player score is suitable to be added in the leader board.
	 */
	public boolean fitLeaderBoard(int score) throws IOException, ClassNotFoundException {
		boolean fit = true;
		File file = new File(SER_LEADER_BOARD);
		if(file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream ins = new ObjectInputStream(in);
			
			ArrayList<LeaderBoard> leaders = (ArrayList<LeaderBoard>)ins.readObject();
			ArrayList<LeaderBoard> test = (ArrayList<LeaderBoard>) leaders.clone();
			LeaderBoard leaderTest = new LeaderBoard("testHallMember", score);
			test.add(leaderTest);
			test.sort(new Comparator<LeaderBoard>() {
				@Override
				public int compare(LeaderBoard l1, LeaderBoard l2) {
					if(l1.getScore() > l2.getScore()) {
						return 1;
					}
					else if(l1.getScore() < l2.getScore()){
						return -1;
					}
					else {
						return 0;
					}
				}
			});
			fit = (leaders.size() < 10) || (test.size() == 11 && !test.get(test.size()-1).equals(leaderTest));
			ins.close();
			in.close();
		}
		return fit;
	}
	/* Method that allows to add a player in the leader board trough the creation of a serializable file and a comparator to sort the scores.
	 * @param an object of LeaderBoard type.
	 */
	public void addLeaderBoard(LeaderBoard lb) throws IOException, ClassNotFoundException {
		File file = new File(SER_LEADER_BOARD);
		ArrayList<LeaderBoard> leaders = new ArrayList<>();
		if(file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream ins = new ObjectInputStream(in);
			
			leaders = (ArrayList)ins.readObject();
			ins.close();
			in.close();
		}
		
		leaders.add(lb);
		leaders.sort(new Comparator<LeaderBoard>() {
			@Override
			public int compare(LeaderBoard l1, LeaderBoard l2) {
				if(l1.getScore() > l2.getScore()) {
					return 1;
				}
				else if(l1.getScore() < l2.getScore()){
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		System.out.println(leaders.size());
		if(leaders.size() == 11) {
			leaders.remove(10);
		}
		
		FileOutputStream out = new FileOutputStream(file);
		ObjectOutputStream outs = new ObjectOutputStream(out);
		outs.writeObject(leaders);
		outs.close();
		out.close();
	}
	/* Method that allow to obtain an ArrayList of LeaderBoard type through the reading of a serializable file.
	 * @return an ArrayList of LeaderBoard type.
	 */
	public ArrayList<LeaderBoard> getLeaders() throws IOException, ClassNotFoundException {
		File file = new File(SER_LEADER_BOARD);
		ArrayList<LeaderBoard> leaders = null;
		if(file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream ins = new ObjectInputStream(in);
			
			leaders = (ArrayList)ins.readObject();
			ins.close();
			in.close();
		}
		return leaders;
	}
}