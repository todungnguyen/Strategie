/**
 * 
 * Calcul du plus cours chemin (Parcours en largeur) 
 */

package Deplacement;

import Main.*;
import Map.*;

public class GetWay {
	
	private Way list;
	private Way visited;
	private Map map;
	
	// Contient longeur de chemin de (x,y) à (i,j)
	private int[][] l;
	
	// predecesseur de chaque case
	private int[][] px;
	private int[][] py;

	public Way parcour(int x, int y, int x1, int y1, Map map) {

		// initialisation
		initial(x, y);
		this.map = map;
		list.enQueue(x, y);

		// parcour des noeuds
		while (!list.Empty()) {
			Node n = list.deQueue();
			addList(n.getPosition().getX(), n.getPosition().getY());
		}

		// get chemin
		Way chemin = new Way();
		chemin = getChemin(x, y, x1, y1, chemin);
		chemin.push(x, y);
		return chemin;
	}

	// Initialisation des structures Si Case source => 0 Sinon => Infini -1
	public void initial(int x, int y) {

		// Instanciation
		list = new Way();
		visited = new Way();
		l = new int[Content.WIDTH / Map.cellSize][Content.HEIGHT / Map.cellSize];
		px = new int[Content.WIDTH / Map.cellSize][Content.HEIGHT / Map.cellSize];
		py = new int[Content.WIDTH / Map.cellSize][Content.HEIGHT / Map.cellSize];

		// Initialisation
		for (int i = 0; i < Content.WIDTH / Map.cellSize; i++) {
			for (int j = 0; j < Content.HEIGHT / Map.cellSize; j++) {
				l[i][j] = -1;
				px[i][j] = 0;
				py[i][j] = 0;
			}
		}

		// case source
		l[x][y] = 0;
		px[x][y] = -1;
		py[x][y] = -1;
	}

	// Calcule si le point (non visite) est au plus court chemin
	public void calcul(int x, int y, int i, int j) {
		if (l[i][j] == -1 || (l[i][j] != -1 && l[i][j] > l[x][y] + 1)) {
			l[i][j] = l[x][y] + 1;
			px[i][j] = x;
			py[i][j] = y;
		}
	}

	//affiche le changement de l et p || Juste pour le test
	public void affiche() {
		System.out.println("l: ");
		for (int i = 0; i < Content.WIDTH / Map.cellSize; i++) {
			for (int j = 0; j < Content.HEIGHT / Map.cellSize; j++) {
				System.out.print(l[i][j] + "/");
			}
			System.out.println();
		}
		System.out.print("p: ");
		for (int i = 0; i < Content.WIDTH / Map.cellSize; i++) {
			for (int j = 0; j < Content.HEIGHT / Map.cellSize; j++) {
				System.out.print("(" + px[i][j] + "," + py[i][j] + ") ");
			}
			System.out.println();
		}

	}

	
	//ajoute (x,y) dans list et calcul l,p
	public void addList(int x, int y) {
		visited.enQueue(x, y);
		for (int i = 0; i < Content.WIDTH / Map.cellSize; i++) {
			for (int j = 0; j < Content.HEIGHT / Map.cellSize; j++) {
				if (Voisin(x, y, i, j) && !list.Exist(i, j) && !visited.Exist(i, j)) {
					list.enQueue(i, j);
					calcul(x, y, i, j);
				}
			}
		}
	}

	
	// renvoie si (i,j) est un voisin de (x,y) hors arbre/or/maison 
	public boolean Voisin(int x, int y, int i, int j) {
		if (map.getType(i, j) != Type.ARBRE && map.getType(i, j) != Type.MAISON && map.getType(i, j) != Type.OR) {
			if (x + 1 == i && y == j)
				return true;
			if (x == i && y + 1 == j)
				return true;
			if (x - 1 == i && y == j)
				return true;
			if (x == i && y - 1 == j)
				return true;
		}
		return false;
	}

	//retourne le chemin de (x,y) à (x1,y1) (sans (x,y))
	public Way getChemin(int x, int y, int x1, int y1, Way chemin) {
		if (x != x1 || y != y1) {
			chemin.push(x1, y1);
			getChemin(x, y, px[x1][y1], py[x1][y1], chemin);
		}
		return chemin;
	}
}
