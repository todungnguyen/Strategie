package Map;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Deplacement.Position;
import GameElement.Player;
import GameElement.Role;
import GameElement.Unite;
import Main.*;

public class Map {

	public final static int cellSize = 25;
	private Cell Carte[][];
	public static int width = Content.WIDTH / cellSize;
	public static int height = Content.HEIGHT / cellSize;
	
	private Content content; 

	// Players
	public Player player1;
	public Player player2;
	
	private boolean endgame = false;
	
	//houses
	private ImageIcon imgcon = new ImageIcon(getClass().getResource("/image/house1.gif"));
	private ImageIcon imgcon2 = new ImageIcon(getClass().getResource("/image/house2.gif"));
	private Image house1 = imgcon.getImage();
	private Image house2 = imgcon2.getImage();

	public Map(Content content) {
		initMap(); 
		player1 = new Player("/perso2/face0.png", new Position(3, 3), this, new Position(1, 1), content);
		player2 = new Player("/perso8/face0.png",
				new Position(Content.HEIGHT / cellSize - 4, Content.HEIGHT / cellSize - 4), this,
				new Position(Content.WIDTH / cellSize - 3, Content.HEIGHT / cellSize - 3), content);
		
		maisons();

		randTele(32, 18, 18, 32);
		randTele(29, 4, 4, 29);
		randTele(8, 31, 31, 8);
		randTele(19, 10, 10, 19);
		randTele(26, 32, 32, 26);

		random(new Arbre(), 20);
		random(new Or(), 15);
		random(new Bouseuses(), 10);
		
	 this.content=content; 

	}

	// maison
	public void maisons() {
		// Player 1
		for (int i = player1.getHouseStart().getX(); i < player1.getHouseStart().getX() + 2; i++) {
			for (int j = player1.getHouseStart().getY(); j < player1.getHouseStart().getX() + 2; j++) {
				Carte[i][j] = new Maison(1);
				Carte[i][j].setEmpty(false);
			}
		}

		// Playyer 2
		for (int i = player2.getHouseStart().getX(); i < player2.getHouseStart().getX() + 2; i++) {
			for (int j = player2.getHouseStart().getY(); j < player2.getHouseStart().getX() + 2; j++) {
				Carte[i][j] = new Maison(2);
				Carte[i][j].setEmpty(false);
			}
		}
	}

	// initialisations
	public void initMap() {
		Carte = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Carte[i][j] = new Normal();
			}
		}
	}

	public void initPlayers() {
		String p = JOptionPane.showInputDialog("Nom de player 1: ");
		player1.setName(p);
		p = JOptionPane.showInputDialog("Nom de player 2: ");
		player2.setName(p);
	}

	// Generer un map al�atoirement.
	public void random(Cell cell, int cpt) {
		while (cpt > 0) {
			Random rand = new Random();
			int i = 4 + rand.nextInt(width - 8); // 4 à 35
			int j = 4 + rand.nextInt(height - 8);
			if (Carte[i][j].getType() == Type.NORMAL) {
				Carte[i][j] = cell;
				Carte[i][j].setEmpty(false);
				cpt--;
			}
		}
	}

	public void randTele(int i, int j, int x, int y) {
		Carte[i][j] = new Teleporteur();
		Carte[i][j].setEmpty(false);
		Carte[x][y] = new Teleporteur();
		Carte[x][y].setEmpty(false);

		Teleporteur s1 = (Teleporteur) Carte[i][j];
		s1.setDes(new Position(x, y));
		s1 = (Teleporteur) Carte[x][y];
		s1.setDes(new Position(i, j));
	}

	public void setEmpty(int i, int j, boolean b) {
		Carte[i][j].setEmpty(b);
	}

	public Type getType(int i, int j) {
		return Carte[i][j].getType();
	}

	public Cell getCell(int i, int j) {
		return Carte[i][j];
	}
	
	public Image getHouse1() {
		return house1; 
		
	}
	public Image getHouse2() {
		return house2; 
	}
	
	//si un RECOLTEUR se trouve a cote d'une pile OR
	public Cell checkOr(int x, int y) {
		if(x + 1 < Map.width && getType(x+1, y) == Type.OR) return getCell(x+1, y);
		if(x - 1 >= 0 && getType(x-1, y) == Type.OR) return getCell(x-1, y);
		if(y + 1 < Map.height && getType(x, y+1) == Type.OR) return getCell(x, y+1);
		if(y - 1 >= 0 && getType(x, y-1) == Type.OR) return getCell(x, y-1);
		return null;
		
	}
	
	public boolean isMaison(Cell cell) {
		if (cell.getType() == Type.MAISON) {
			Maison c = (Maison) cell;
			if (c.getPlayer() == 1)
				player1.createUnite();
			else
				player2.createUnite();
			return true;
		}
		return false;
	}

	public Unite isUnite(int x, int y) {
		Unite u = player1.isUnite(x, y);
		if (u != null)
			return u;
		u = player2.isUnite(x, y);
		return u;
	}

	public boolean isEmpty(int i, int j) {
		return Carte[i][j].isEmpty();
	}
	
	
	public void findEnemy() {
		player1.findEnemy();
		player2.findEnemy();
	}
	
	public void updateFramePos(int x, int y) {
		player1.updateFramePos(x, y);
		player2.updateFramePos(x, y);
	}
	
	public void colector(int x, int y, Unite u) {
		if (checkOr(x, y) != null && u.getRole() == Role.RECOLTEUR) {
			//System.out.println("Or detected.");
			u.action(content, x, y, checkOr(x, y));
		}
	}
	
	public Content getContent() {
		return content; 
	}
	
	
	public void winner() {
		if (player1.getGold() > player2.getGold()) {
			JOptionPane.showMessageDialog(null, player1.getName() + " win.");
		} else if (player1.getGold() < player2.getGold()) {
			JOptionPane.showMessageDialog(null, player2.getName() + " win.");
		} else {
			JOptionPane.showMessageDialog(null, "Equal");
		}
		endgame = true;
		System.exit(0);
	}
	
	public boolean getEndGame() {
		return endgame;
	}
	
	
}
