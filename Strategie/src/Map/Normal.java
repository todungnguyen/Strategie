package Map;

import GameElement.Unite;

public class Normal extends Cell {
	
	public Normal() {
		super();
		this.type = Type.NORMAL;
	}
	
	public void action(Unite unite) {
		long t0 = System.nanoTime();
		long t1 = t0;
		while (t1 - t0 < Cell.DELAY) {
			t1 = System.nanoTime();
		}
		//unite.action();
	};
}
