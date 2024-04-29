package modele;

import controler.Controle;
import outils.connexion.Connection;
/**
 * Informations et m�thodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {

	/**
	 * pour communiquer avec le controleur
	 */
	protected Controle controle;
	/**
	 * R�ception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connexion(Connection connection) ;
	
	/**
	 * R�ception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object info) ;
	
	/**
	 * D�connexion de l'ordinateur distant
	 */
	public abstract void deconnexion() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi(Connection connection, Object info) {
		this.controle.envoi(connection, info);
	}
	
}
