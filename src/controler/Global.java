package controler;

public interface Global {
	/**
	 * num�ro du port d'ecoute du serveur
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
	 * vie de d�part pour tous les joueurs
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
	/**
	 * nombre de murs
	 */
	int NBMUR = 20;
	/**
	 * largeur mur
	 */
	int LARGEURMUR = 34;
	/**
	 * hauteur mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur arene
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur arene
	 */
	int HAUTEURARENE = 600;
	/**
	 * chemin du dossier mur
	 */
	String CHEMINMUR = "murs"+ CHEMINSEPARATOR;
	/**
	 * chemin de recup image du mur
	 */
	String MUR = CHEMINMUR + "mur.gif";
	/**
	 * ajouter un mur dans arene serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * ajout du panel mur au client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	
}