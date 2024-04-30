package modele;

import controler.Controle;
import outils.connexion.Connection;
import javax.swing.JPanel;
import controler.Global;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	/**
	 * declaration objet de type connection pour communiquer avec le serveur
	 */
	private Connection connection;
	/**
	 * propriété qui initialise vrai quand les murs ont été créés la première fois 
	 */
	private boolean mursOK = false;
	/**
	 * Controleur
	 * @param controle
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if (!this.mursOK) {
				this.controle.evenementJeuClient(AJOUTPANELMURS, info);
				this.mursOK = true;
			}else {
				this.controle.evenementJeuClient(MODIFPANELJEU, info);
			}
		}
	}
	
	@Override
	public void deconnexion() {
		
	}
	
	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 * @param info
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}

}
