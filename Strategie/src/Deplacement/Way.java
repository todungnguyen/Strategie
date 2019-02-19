/*
 * Classe Way Modélise une liste de Nodes
 */
package Deplacement;

public class Way {

	private Node first;

	// Getter
	public Node getFirst() {
		return first;
	}

	// Ajoute element à la fin de la liste
	public void enQueue(int x, int y) {
		Node n = new Node(new Position(x,y), null);
		Node c = this.first;
		Node p = null;
		while (c != null) {
			p = c;
			c = c.getNext();
		}
		if (p != null) {
			p.setNext(n);
		} else {
			this.first = n;
		}
	}

	// Ajoute element dans le debut de la liste
	public void push(int x, int y) {
		Node n = new Node(new Position(x,y), this.first);
		this.first = n;
	}

	// Supprime le premier element
	public Node deQueue() {
		if (!this.Empty()) {
			Node n = new Node(new Position(this.first.getPosition().getX(), this.first.getPosition().getY()), null);
			this.first = this.first.getNext();
			return n;
		}
		return null;
	}

	// Retourne si la liste est vide ou non
	public boolean Empty() {
		return (this.first == null);
	}

	// retourne si (x,y) existe dans la liste ou non
	public boolean Exist(int x, int y) {
		Node c = this.first;
		while (c != null) {
			if (c.getPosition().getX() == x && c.getPosition().getY() == y)
				return true;
			c = c.getNext();
		}
		return false;
	}

	// Retourne la taille de la liste
	public int size() {
		Node c = this.first;
		int taille = 0;
		while (c != null) {
			taille++;
			c = c.getNext();
		}
		return taille;
	}

	

}
