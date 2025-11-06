package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.appartientVillage(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		if (!controlAcheterProduit.rechercherProduit(produit)) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}
		System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
		int i = 0;
		String[] etatMarche = controlAcheterProduit.etatMarche();
		while (i < etatMarche.length) {
			System.out.println(((i/3)+1) + " - " + etatMarche[i]);
			i += 3;
		}
		int choixInt = Clavier.entrerEntier("");
		if (choixInt < 1 || choixInt > etatMarche.length/3) {
			return;
		}
		int etalChoisi = (choixInt - 1)*3;
		String vendeur = etatMarche[etalChoisi];
		int quantite = Integer.parseInt(etatMarche[etalChoisi+1]);
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur);
		System.out.println("Bonjour " + nomAcheteur);
		choixInt = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		if (quantite == 0) {
			System.out.println(nomAcheteur + " veut acheter " + choixInt + " " + produit 
					+ ", malheureusement il n'y en a plus !");
		} else if (quantite < choixInt) {
			controlAcheterProduit.acheterProduit(vendeur, quantite);
			System.out.println(nomAcheteur + " veut acheter " + choixInt + " " + produit 
					+ ", malheureusement " + vendeur + " n'en a plus que " + quantite 
					+ ". " + nomAcheteur + " achète tout le stock de " + vendeur + ".");
		} else {
			controlAcheterProduit.acheterProduit(vendeur, choixInt);
			System.out.println(nomAcheteur + " achète " + choixInt + " " + produit + " à " + vendeur);
		}
	}
}
