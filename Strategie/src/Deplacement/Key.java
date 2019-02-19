/**
 * KeyListener => gére les changments de vue 
 *
 */
package Deplacement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import GameElement.Role;
import GameElement.Unite;
import Main.*;
import Map.*;

public class Key extends KeyAdapter {

	private Content game;

	public Key(Content game) {
		this.game = game;
	}

	/*
	 * Deplacer la partie visible de la carte avec les fleches directionnelles du
	 * clavier.
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// change backgroundStart
		case KeyEvent.VK_RIGHT:
			if (game.getBackgroundStart().getX() > Scene.WIDTH - Content.WIDTH) {
				game.setBackgroundStart(game.getBackgroundStart().getX() - Map.cellSize,
						game.getBackgroundStart().getY());
				game.getMap().updateFramePos(-1, 0);
			}
			break;

		case KeyEvent.VK_LEFT:
			if (game.getBackgroundStart().getX() < 0) {
				game.setBackgroundStart(game.getBackgroundStart().getX() + Map.cellSize,
						game.getBackgroundStart().getY());
				game.getMap().updateFramePos(1, 0);
			}
			break;

		case KeyEvent.VK_UP:
			if (game.getBackgroundStart().getY() < 0) {
				game.setBackgroundStart(game.getBackgroundStart().getX(),
						game.getBackgroundStart().getY() + Map.cellSize);
				game.getMap().updateFramePos(0, 1);
			}
			break;

		case KeyEvent.VK_DOWN:
			if (game.getBackgroundStart().getY() > Scene.HEIGHT - Content.HEIGHT) {
				game.setBackgroundStart(game.getBackgroundStart().getX(),
						game.getBackgroundStart().getY() - Map.cellSize);
				game.getMap().updateFramePos(0, -1);
			}
			break;

		case KeyEvent.VK_1:
		case KeyEvent.VK_2:
		case KeyEvent.VK_3: // 49, 50, 51 = R, S, A
			checkKeyPressed(e.getKeyCode());
			break;
		}
		game.repaint();
	}

	public void checkKeyPressed(int x) {
		Unite u = game.getMap().player1.existSelected();
		if (u == null)
			u = game.getMap().player2.existSelected();
		if (u != null) {
			if (x == 49)
				u.setRole(Role.RECOLTEUR);
			if (x == 50)
				u.setRole(Role.SENTINELLE);
			if (x == 51)
				u.setRole(Role.ATTAQUANT);
			u.Info();
		}
	}
}
