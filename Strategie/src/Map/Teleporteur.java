package Map;

import javax.swing.ImageIcon;

import Deplacement.Position;
import GameElement.Unite;

public class Teleporteur extends Cell {
	
	private Position des; 
	
	public Teleporteur() {
		super();
		this.des = null;
		this.type = Type.TELEPORTEUR;
		this.imgcon = new ImageIcon(getClass().getResource("/image/teleporter_active.gif"));
		this.img = this.imgcon.getImage();
	
	}
	
	@Override
	public void action(Unite unite) {
		unite.changePlace(des);
	}
		
	
	public Position getDes() {
		return this.des;
	}

	public void setDes(Position des) {
		this.des = des;
	}
}
