package Map;

import java.awt.Image;

import javax.swing.ImageIcon;

import GameElement.Unite;

public abstract class Cell {

	public final static int DELAY = 200000000;

	protected Type type;
	public boolean empty; // s'il contient déjà un unité ou non

	// Image
	protected Image img;
	protected ImageIcon imgcon;

	public abstract void action(Unite unite);

	public Cell() {
		this.empty = true;

	}

	// Getters and setters.
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public Image getImage() {
		return img;
	}

}
