package GameElement;

import Deplacement.GetWay;
import Deplacement.Way;
import Main.Content;
import Main.RunThread;
import Map.Cell;
import Map.Map;
import Map.Or;

public enum Role {

	SENTINELLE {
		@Override
		public void action(Player owner, int x, int y, Cell cell, Map m, Unite u, Content content) {
			// TODO Auto-generated method stub

		}

		public int getPrix() {
			return 20;
		}
	},
	ATTAQUANT {


		@Override
		//besoin que x,y,m,u, u <-> attack
		public void action(Player owner, int x, int y, Cell cell, Map m, Unite u, Content content) {
			//System.out.println(" action: Attack "+x+" "+y);
			Unite unite = m.player1.isUnite(x,y); //defend
			if(unite == null) {
				unite = m.player2.isUnite(x,y);
			}
			if(unite != null)  {
				if(unite.getOwner() != u.getOwner()) {
					unite.defend();
				}
			}
		}

		public int getPrix() {
			return 30;
		}
	},
	RECOLTEUR {
		@Override
		public void action(Player owner, int x, int y, Cell cell, Map m, Unite u, Content content) {
			Or or = (Or) cell;
			GetWay getway = new GetWay();
			Way way = getway.parcour(x, y, owner.getHouseStart().getX() + 2, owner.getHouseStart().getY() + 2, m);
			Way way1 = getway.parcour(owner.getHouseStart().getX() + 2, owner.getHouseStart().getY() + 2, x, y, m);
			
			if (or.getCapacite() > 0) {
				or.action(u);
				owner.incGold();
				RunThread thread1 = new RunThread(content, m, way1, u, null);
				RunThread thread = new RunThread(content, m, way, u, thread1);
				thread.start();
			} else {
				m.winner();
			}
		}
		
		public int getPrix() {
			return 0;
		}
	};

	public abstract void action(Player owner, int x, int y, Cell cell, Map m, Unite u, Content content);
	public abstract int getPrix();

}
