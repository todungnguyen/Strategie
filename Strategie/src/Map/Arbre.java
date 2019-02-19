package Map;

import javax.swing.ImageIcon;

import GameElement.Unite;

public class Arbre extends Cell {
	public Arbre() {
		super();
		this.type = Type.ARBRE;
		this.imgcon = new ImageIcon(getClass().getResource("/image/tree.png"));
		this.img = this.imgcon.getImage();
	}

	@Override
	public void action(Unite unite) {
		//System.out.println("Ne peut pas avancer");
	}
	
}
