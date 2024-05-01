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
	/**
	 * largeur de l'image personnage
	 */
	int LARGEURPERSO = 39;
	/**
	 * hauteur de l'image du personnage
	 */
	int HAUTEURPERSO = 44;
	/**
	 * hauteur du message sous le personnage
	 */
	int HAUTEURMESSAGE = 8;
	/**
	 * ajout d'un jlabel jeu
	 */
	String AJOUTJLABELJEU = "ajout jLabel jeu";
	/**
	 * etat joueur marche
	 */
	String MARCHE = "marche";
	/**
	 * etat joueur mort
	 */
	String MORT = "mort";
	/**
	 * etat joueur touche
	 */
	String TOUCHE = "touche";
	/**
	 * orientation joueur droite
	 */
	int DROITE = 1;
	/**
	 * orientation joueur gauche
	 */
	int GAUCHE = 0;
	/**
	 * modification du panel jeu dans l'arene client
	 */
	String MODIFPANELJEU = "modification du panel jeu";
	/**
	 * ordre tchat
	 */
	String TCHAT = "tchat";
	/**
	 * ajout de phrase
	 */
	String AJOUTPHRASE = "ajout phrase";
	/**
	 * modif du tchat
	 */
	String MODIFTCHAT = "modif du tchat";
	/**
	 * jeu client
	 */
	String CLIENT = "client";
}
