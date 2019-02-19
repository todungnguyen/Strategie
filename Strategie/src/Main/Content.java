/**
 * Content => Pannel Contenu de la fenetre   works as Players observer 
 *
 */

package Main;

import java.awt.*;
import javax.swing.*;
import Deplacement.*;
import GameElement.Player;
import GameElement.Unite;
import Map.*;

@SuppressWarnings("serial")
public class Content extends JPanel {
	// Content Size
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1022;

	// Background Image
	private ImageIcon background;
	private Image imagefond1;

	// Position de 1ere case de map par rapport à frame
	private Position backgroundStart;
	private Map map;

	// Ajouter des listeners
	private Key keyboard = new Key(this);
	private Mouse mouse = new Mouse(this);

	// Constructeur
	public Content() {
		this.background = new ImageIcon(getClass().getResource("/image/carte.PNG"));
		this.imagefond1 = this.background.getImage();
		this.backgroundStart = new Position(0, 0);
		this.map = new Map(this);
		this.setFocusable(true);
		addKeyListener(keyboard);
		addMouseListener(mouse);
		map.initPlayers();
		AttackThread at = new AttackThread(map);
		at.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// map base
		Graphics g2 = (Graphics2D) g;
		g2.drawImage(imagefond1, backgroundStart.getX(), backgroundStart.getY(), WIDTH, HEIGHT, null);

		// Les caros sur Scene
		drawGrid(g2);

		// map sur Content
		drawCells(g2);
		drawHouses(g2);

		// base
		drawBasePlayer(g, map.player1);
		drawBasePlayer(g, map.player2);

		// players
		drawPlayer(g, map.player2);
		drawPlayer(g, map.player1);
		
		drawInfoPlayer(g2);
	}

	public void drawInfoPlayer(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(map.player1.getName() + " = " + map.player1.getGold(), Map.cellSize, Map.cellSize);
		g.drawString(map.player2.getName() + " = " + map.player2.getGold(), 10 * Map.cellSize, Map.cellSize);
		g.drawString("1 = RECOLTEUR, 2 = SENTINELLE, 3 = ATTAQUANT", 20 * Map.cellSize, Map.cellSize);
	}

	public void drawGrid(Graphics g) {
		g.setColor(new Color(0, 0, 0, 35));
		for (int i = 1; i <= getSize().width / Map.cellSize; i++) {
			g.drawLine(i * Map.cellSize, 0, i * Map.cellSize, getSize().height);
		}
		for (int i = 1; i <= getSize().height / Map.cellSize; i++) {
			g.drawLine(0, i * Map.cellSize, getSize().width, i * Map.cellSize);
		}
	}

	public void drawPlayer(Graphics g, Player player) {
		for (Unite u : player.getUnites()) {
			g.drawImage(player.getImg(), u.getFramePos().getX() * player.getSizeX(),
					u.getFramePos().getY() * player.getSizeY(), player.getSizeX(), player.getSizeY(), null);
		}
	}

	public void drawCell(Graphics g, Cell c, int i, int j) {
		g.drawImage(c.getImage(), i * Map.cellSize + backgroundStart.getX(), j * Map.cellSize + backgroundStart.getY(),
				Map.cellSize, Map.cellSize, null);
	}

	public void drawCells(Graphics g) {
		for (int i = 0; i < WIDTH / Map.cellSize; i++) {
			for (int j = 0; j < HEIGHT / Map.cellSize; j++) {
				if (map.getType(i, j) != Type.NORMAL) {
					drawCell(g, map.getCell(i, j), i, j);
				}
			}
		}
	}

	public void drawBasePlayer(Graphics g, Player player) {
		g.setColor(new Color(102, 178, 255, 200));
		Unite u = player.existSelected();
		if (u != null)
			g.fillRoundRect(u.getFramePos().getX() * player.getSizeX(), u.getFramePos().getY() * player.getSizeY(),
					player.getSizeX(), player.getSizeY(), 10, 10);
	}

	public void drawHouses(Graphics g) {
		g.drawImage(map.getHouse1(), 1 * Map.cellSize + backgroundStart.getX(),
				1 * Map.cellSize + backgroundStart.getY(), 2 * Map.cellSize, 2 * Map.cellSize, null);
		g.drawImage(map.getHouse2(), (Map.width - 3) * Map.cellSize + backgroundStart.getX(),
				(Map.height - 3) * Map.cellSize + backgroundStart.getY(), 2 * Map.cellSize, 2 * Map.cellSize, null);
	}

	
	
	
	
	
	
	
	
	
	

	

	

	
	
	

	// Getters and setters
	public Position getBackgroundStart() {return backgroundStart;}
	public Map getMap() {return map;}
	public void setBackgroundStart(int x, int y) {
		backgroundStart.setX(x);
		backgroundStart.setY(y);
	}
	
	

	
}
