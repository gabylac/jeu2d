package modele;

import controler.Controle;
import outils.connexion.Connection;
/**
 * Informations et méthodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {

	/**
	 * pour communiquer avec le controleur
	 */
	protected Controle controle;
	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 * @param connection
	 */
	public abstract void connexion(Connection connection) ;
	
	/**
	 * Réception d'une information provenant de l'ordinateur distant
	 * @param connection au serveur
	 * @param info recue
	 */
	public abstract void reception(Connection connection, Object info) ;
	
	/**
	 * Déconnexion de l'ordinateur distant
	 */
	public abstract void deconnexion() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 * @param connection vers ordi distant
	 * @param info à envoyer
	 */
	public void envoi(Connection connection, Object info) {
		this.controle.envoi(connection, info);
	}
	
}
