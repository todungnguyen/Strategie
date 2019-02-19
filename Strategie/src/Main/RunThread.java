
/**
 * Class RunThead => Décrit le daplacement en runnable 
 */

package Main;

import Deplacement.Node;
import Deplacement.Position;
import Deplacement.Way;
import GameElement.Unite;
import Map.Cell;
import Map.Map;

public class RunThread implements Runnable {
	private Thread t;
	private Content content;
	private Map map;
	private Way way;
	private Unite unite;
	private RunThread thread; // marche quand this fini

	public RunThread(Content content, Map map, Way way, Unite unite, RunThread thread) {
		this.content = content;
		this.map = map;
		this.way = way;
		this.unite = unite;
		this.thread = thread;
	}

	public void run() {
		unite.setBouge(true);

		Node n = null;

		while (!way.Empty() && unite.isBouge()) {

			// avance une case
			n = way.deQueue();

			Position pos = unite.getMapPos();
			
			unite.setMapPos(new Position(n.getPosition().getX(), n.getPosition().getY()));
			unite.MapPosToFramePos();

			//Si le cas qu'on sorti ne contient qu'un unite, empty => true
			if(map.isUnite(pos.getX(), pos.getY()) == null) map.setEmpty(pos.getX(), pos.getY(),true);
			map.setEmpty(n.getPosition().getX(), n.getPosition().getY(), false); // arriver

			// repaint
			content.repaint();
			content.paintImmediately(0, 0, content.getSize().width, content.getSize().height);

			// attends
			map.getCell(n.getPosition().getX(), n.getPosition().getY()).action(unite);

		}

		if (unite.isBouge()) {
			if (thread != null) {
				waitt(Cell.DELAY);
				thread.start();
			} else {
				content.getMap().colector(n.getPosition().getX(), n.getPosition().getY(), unite);
			}
		}

		unite.setBouge(false);
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public static void waitt(int delay) {
		long t0 = System.nanoTime();
		long t1 = t0;
		while (t1 - t0 < delay) {
			t1 = System.nanoTime();
		}
	}

}
