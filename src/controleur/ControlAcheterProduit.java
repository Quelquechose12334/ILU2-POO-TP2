package controleur;

import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean appartientVillage(String gaulois) {
		return controlVerifierIdentite.verifierIdentite(gaulois);
	}

	public boolean rechercherProduit(String produit) {
		return village.rechercherVendeursProduit(produit) != null;
	}
	
	public String[] etatMarche() {
		return village.donnerEtatMarche();
	}
	
	public void acheterProduit(String nomVendeur, int quantiteAcheter) {
		controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).acheterProduit(quantiteAcheter);
	}
}
