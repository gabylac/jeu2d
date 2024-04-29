package controler;

public interface Global {
	/**
	 * numéro du port d'ecoute du serveur
	 */
	int PORT = 6666;
	/**
	 * saisie du pseudo
	 */
	String PSEUDO = "pseudo";
	/**
	 * separateur
	 */
	String STRINGSEPARE = "~";
	/**
	 * nombre de personnage possible
	 */
	int NBPERSO = 3;
	/**
	 * message connexion
	 */
	String CONNEXION = "connexion";
	/**
	 * message reception
	 */
	String RECEPTION = "reception";
	/**
	 * message deconnexion
	 */
	String DECONNEXION = "deconnexion";
	/**
	 * vie de départ pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ; 
	/**
	 * separateur dans un chemin
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * chemin du dossier de fonds
	 */
	String CHEMINFONDS = "fonds"+ CHEMINSEPARATOR;
	/**
	 * chemin du fond de l arene
	 */
	String FONDARENE = CHEMINFONDS + "fondarene.jpg";
	/**
	 * chemin du fond choix joueur
	 */
	String FONDCHOIX = CHEMINFONDS + "fondchoix.jpg";
	
}
