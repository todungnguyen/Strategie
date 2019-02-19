/**
 * Classe Node: Modélise un Noeud ayant pour info la position (x,y pour l'insant)
 *
 */

package Deplacement;

public class Node {

	private Position pos;
	private Node next;

	// Constructeur
	public Node(Position pos, Node next) {
		this.pos = pos;
		this.next = next;
	}

	// Getters and setters
	public Node getNext() {
		return this.next;
	}

	public void setNext(Node n) {
		this.next = n;
	}

	public Position getPosition() {
		return pos;
	}

	public void setPosition(Position pos) {
		this.pos = pos;
	}

	// toString
	public String toString() {
		return "(" + pos.getX() + "," + pos.getY() + ") ";
	}

}
