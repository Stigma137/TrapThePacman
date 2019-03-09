package thread;

import application.TrapPacmanController;

public class Thread_Pacman extends Thread{
	private TrapPacmanController arc;
	public Thread_Pacman(TrapPacmanController arc) {
		this.arc = arc;
	}
	@Override
	public void run() {
		while (arc.getXArc() != arc.getXEl()) {
			try {
				arc.moveFig();
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
