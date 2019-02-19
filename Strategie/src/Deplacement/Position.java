/**
 * Classe Position: modélise une position (axe x, axe y) 
 *
 */

package Deplacement;

public class Position {
	private int x;
	private int y;

	// Constructeur
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	
}
