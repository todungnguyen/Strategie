
/**
 * MouseListener => gére les ordre de déplacement
 *
 */
package Deplacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GameElement.Unite;
import Main.*;
import Map.*;

public class Mouse extends MouseAdapter {

	private Content game;

	public Mouse(Content content) {
		this.game = content;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (e.getButton() == MouseEvent.BUTTON1/* Left */) {

			// Mappos
			x = (x - game.getBackgroundStart().getX()) / Map.cellSize;
			y = (y - game.getBackgroundStart().getY()) / Map.cellSize;
			checkMouseClicked(x, y);
			game.repaint();
		}
	}

	public boolean checkMouseClicked(int x, int y) {
		Cell cell = game.getMap().getCell(x, y);

		// Maison
		if (game.getMap().isMaison(cell)) {
			return true;
		}

		Unite u = game.getMap().isUnite(x, y);

		// Select unite
		if (u != null) {
			game.getMap().player1.unSelect(); // pour qu'on a un seul unite selected
			game.getMap().player2.unSelect();
			u.setSelected(true);
			u.Info();
			return true;
		}

		// Way = Boubeuses, Normal, Teleporteur, on ne peut qu'aller à côté Arbre et Or.
		if (cell.getType() != Type.ARBRE && cell.getType() != Type.OR) {
			GetWay getway = new GetWay();
			u = game.getMap().player1.existSelected();
			if (u == null)
				u = game.getMap().player2.existSelected();
			if (u != null) {
				Way way = getway.parcour(u.getMapPos().getX(), u.getMapPos().getY(), x, y, game.getMap());
				if (u.isBouge()) {
					u.setBouge(false);

					RunThread.waitt(Cell.DELAY);
					System.out.println("Change chemin de player");
				}

				RunThread thread = new RunThread(game, game.getMap(), way, u, null);
				thread.start();
			}
			return true;
		}
		return false;
	}
}
