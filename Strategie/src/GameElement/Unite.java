package GameElement;

import Deplacement.Position;
import Main.Content;
import Map.*;

public class Unite {
	private Content content;
	private Map map;
	private Player owner;
	private Role role;
	private int sang;

	// Etat
	private boolean selected;
	private boolean bouge;

	// Positions
	private Position framepos; // Par rapport � la fenetre
	private Position mappos; // Par rapport � la map

	// Constructeur vide.
	public Unite(Player p, Map map, Position pos, Content content) {
		this.sang = 10;
		this.content = content;
		this.owner = p;
		this.map = map;
		this.role = Role.RECOLTEUR;
		this.selected = false;
		this.bouge = false;
		this.mappos = new Position(pos.getX(), pos.getY());
		MapPosToFramePos();
	}

	// Action selon role (state)
	public void action(Content content, int x, int y, Cell cell) {
		owner.deductGold(role.getPrix());
		role.action(owner, x, y, cell, map, this, content);
	}

	// affectation d'un role
	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return this.role;
	}

	public Position getFramePos() {
		return framepos;
	}

	public void setFramePos(Position pos) {
		this.framepos = pos;
	}

	public Position getMapPos() {
		return mappos;
	}

	public void setMapPos(Position mappos) {
		this.mappos = mappos;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isBouge() {
		return bouge;
	}

	public void setBouge(boolean bouge) {
		this.bouge = bouge;
	}

	public Player getOwner() {
		return this.owner;
	}

	// Convertisseurs de position Frame <=> Map
	public void FramePosToMapPos() {
		mappos = new Position(framepos.getX() - content.getBackgroundStart().getX() / Map.cellSize,
				framepos.getY() - content.getBackgroundStart().getY() / Map.cellSize);
	}

	public void MapPosToFramePos() {
		framepos = new Position(mappos.getX() + content.getBackgroundStart().getX() / Map.cellSize,
				mappos.getY() + content.getBackgroundStart().getY() / Map.cellSize);
	}

	public void updateFramePos(int x, int y) {
		setFramePos(new Position(getFramePos().getX() + x, getFramePos().getY() + y));
	}

	public void changePlace(Position pos) {

		setEmpty(true); // partir

		// nouveau place
		int x = pos.getX();
		int y = pos.getY();
		if (map.getType(x - 1, y) == Type.NORMAL)
			mappos = new Position(x - 1, y);
		if (map.getType(x + 1, y) == Type.NORMAL)
			mappos = new Position(x + 1, y);
		if (map.getType(x, y - 1) == Type.NORMAL)
			mappos = new Position(x, y - 1);
		if (map.getType(x, y + 1) == Type.NORMAL)
			mappos = new Position(x, y + 1);

		setEmpty(false); // arriver
		MapPosToFramePos();
		content.repaint();
	}

	public void setEmpty(boolean b) {
		map.setEmpty(mappos.getX(), mappos.getY(), b);
	}

	public void findEnemy() {
		for (int i = mappos.getX() - 3; i < mappos.getX() + 4; i++) {
			for (int j = mappos.getY() - 3; j < mappos.getY() + 4; j++) {
				//System.out.println(i+" "+j);
				if (i >= 0 && i < Map.width && j >= 0 && j < Map.height && map.isUnite(i, j) != null && (i != mappos.getX() || j != mappos.getY())) {
					//System.out.println("findEnemy " + i + " " + j);
					action(content, i, j, new Normal());
				}
			}
		}
	}

	public void defend() {
		die();
		System.out.print(mappos.getX() + " " + mappos.getY() + " defend ");
		this.sang = this.sang - 1;
		System.out.println(" Sang: " + this.sang);
	}

	public void die() {
		if (this.sang <= 0)
			owner.deleteUnite(this);
		content.repaint();
	}
	
	public void Info() {
		System.out.println(role.name()+" selected.");
	}
}
