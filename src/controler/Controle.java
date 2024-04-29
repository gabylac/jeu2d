package controler;

import vue.entreeJeu;
import vue.Arene;
import vue.ChoixJoueur;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import outils.connexion.ClientSocket;
import modele.Jeu;
import modele.JeuServeur;
import modele.JeuClient;
import javax.swing.JPanel;

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {

	private entreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;
		
	/**
	 * M�thode de d�marrage
	 * @param args non utilis�
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
		if (info.equals(SERVEUR)) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}
		else {
			new ClientSocket(this, info, PORT);
		}
	}
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+numPerso);
	}
	public void evenementJeuServeur(String unOrdre, Object info) {
		switch (unOrdre){
		case AJOUTMUR:
			frmArene.ajoutMur(info);
			break;
		case AJOUTPANELMURS:
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnMurs());
			break;
		}
	}
	public void evenementJeuClient(String unOrdre, Object info) {
		switch (unOrdre) {
		case AJOUTPANELMURS:
			this.frmArene.setJpnMurs((JPanel)info);
			break;
		}
	}
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch (ordre) {
		case CONNEXION:
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
			else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION:
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION:
			break;
		}
	}
	
}
