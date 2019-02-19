package GameElement;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import Deplacement.Position;
import Main.Content;
import Map.Map;

public class Player {
	private Content content;
	private String name;
	private Map map;
	private Position houseStart;

	// Taille
	private final int sizeX = Map.cellSize;
	private final int sizeY = Map.cellSize;

	// Image
	private Image img;
	private ImageIcon imgcon;

	private int gold;

	// Liste d'unites d'un joueur
	List<Unite> unites;

	// Constructeur
	public Player(String s/* image */, Position pos, Map map, Position houseStart, Content content) {
		this.content = content;
		this.gold = 1000;
		this.houseStart = houseStart;
		this.name = null;
		this.unites = new ArrayList<>();
		this.unites.add(new Unite(this, map, pos, content));
		this.map = map;
		this.map.setEmpty(pos.getX(), pos.getY(), false);
		this.imgcon = new ImageIcon(getClass().getResource(s));
		this.img = this.imgcon.getImage();

	}

	// Creation d'une unite
	public boolean createUnite() {
		if (gold >= 50) {
			for (int i = houseStart.getX() - 1; i < houseStart.getX() + 3; i++) {
				for (int j = houseStart.getY() - 1; j < houseStart.getY() + 3; j++) {
					if (map.getCell(i, j).isEmpty()) {
						unites.add(new Unite(this, map, new Position(i, j), content));
						map.setEmpty(i, j, false);
						deductGold(50);
						return true;
					}
				}
			}
			System.out.println("Maximum unite: move unit");
			return false;
		}
		System.out.println("Pas assez d'or");
		return false;
	}

	// Op sur gold
	public void incGold() {
		this.gold += 10;
	}

	public void deductGold(int n) {
		this.gold -= n;
	}
	//

	// V�rifie s'il la case contient une unit�
	public Unite isUnite(int x, int y) {
		for (Unite u : unites) {
			if (u.getMapPos().getX() == x && u.getMapPos().getY() == y) {
				return u;
			}
		}
		return null;
	}

	// Operations sur tableau Unite
	public void updateFramePos(int x, int y) {
		for (Unite u : unites) {
			u.updateFramePos(x, y);
		}
	}

	public void unSelect() {
		for (Unite u : unites) {
			u.setSelected(false);
		}
	}

	public Unite existSelected() {
		for (Unite u : unites) {
			if (u.isSelected()) {
				return u;
			}
		}
		return null;
	}

	public void deleteUnite(Unite u) {
		map.setEmpty(u.getMapPos().getX(), u.getMapPos().getY(), true);
		unites.remove(u);
	}

	public void findEnemy() {
		for (Unite u : unites) {
			if (u.getRole() == Role.ATTAQUANT)
				u.findEnemy();
		}
	}

	// Getters and setters
	public Image getImg() {
		return img;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public Position getHouseStart() {
		return houseStart;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGold() {
		return gold;
	}

	public List<Unite> getUnites() {
		return unites;
	}
}
