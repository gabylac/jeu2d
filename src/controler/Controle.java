package controler;

import vue.entreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import outils.connexion.ClientSocket;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	private entreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private String typeJeu;
	private ChoixJoueur frmChoixJoueur;
	private static final int PORT = 6666;

	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new entreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	public void evenementEntreeJeu(String info) {
		if (info.equals("serveur")) {
			this.typeJeu = "serveur";
			new ServeurSocket(this, PORT);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		}
		else {
			this.typeJeu = "client";
			new ClientSocket(this, info, PORT);
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch (ordre) {
		case ("connexion"):
			if (this.typeJeu.equals("client")) {
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur();
				this.frmChoixJoueur.setVisible(true);
			}
			break;
		case ("reception"):
			break;
		case ("deconnexion"):
			break;
		}
	}
}
