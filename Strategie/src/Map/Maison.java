package Map;

import GameElement.Unite;

public class Maison extends Cell {
	
	private int player;
	
	public Maison(int p) {
		super();
		player = p;
		this.type = Type.MAISON;
	}

	@Override
	public void action(Unite unite) {
	}

	public int getPlayer() {
		return player;
	}
	

}
