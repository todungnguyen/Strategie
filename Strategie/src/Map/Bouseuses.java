package Map;

import javax.swing.ImageIcon;

import GameElement.Unite;

public class Bouseuses extends Cell {
	public Bouseuses() {
		super();
		this.type = Type.BOUSEUSES;
		
		this.imgcon = new ImageIcon(getClass().getResource("/image/boue.png"));
		this.img = this.imgcon.getImage();
	}
	
	public void action(Unite unite) {
		long t0 = System.nanoTime();
		long t1 = t0;
		while (t1 - t0 < Cell.DELAY*2) {
			t1 = System.nanoTime();
		}
		//unite.action();
	};
}
