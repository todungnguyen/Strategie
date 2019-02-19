package Map;

import javax.swing.ImageIcon;

import GameElement.Unite;

public class Or extends Cell {
	private int capacite;
	
	public Or() {
		super();
		this.capacite = 1000;
		this.type = Type.OR;
		this.imgcon = new ImageIcon(getClass().getResource("/image/gold.gif"));
		this.img = this.imgcon.getImage();
	}

	@Override
	public void action(Unite unite) {
		this.capacite = this.capacite - 10;
		System.out.println(this.capacite);

	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	
	public void clearFromBoard() {
		this.setType(Type.NORMAL);
	}
}
