package Main;

import Map.Map;

public class AttackThread implements Runnable {
	private Thread t;
	private Map map;

	public AttackThread(Map map) {
		this.map = map;
	}

	public void run() {
		while (!map.getEndGame()) {
			//System.out.println("Wait");
			RunThread.waitt(1000000000);
			map.findEnemy();
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}
