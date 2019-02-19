package Main;

import java.util.List;

import Deplacement.Node;
import Deplacement.Way;
import GameElement.Unite;



//Affichages pour les tests 


public class Affichage {


	// Affiche WAY 
		public void affiche(Way l) {
			if (l.Empty())
				System.out.println("NULL");
			else {
				Node c = l.getFirst();
				while (c != null) {
					System.out.print(c.toString());
					c = c.getNext();
				}
				System.out.println();
			}
		}
		
		
		public void affiche(List<Unite> unites) {
			for (Unite u : unites) {
				System.out.print(u.getMapPos().toString());
			}
			System.out.println();
		}
}
